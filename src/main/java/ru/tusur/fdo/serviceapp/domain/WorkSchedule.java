package ru.tusur.fdo.serviceapp.domain;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Created by schna_000 on 16.03.14.
 * version 1.0
 * Initial creation
 */
public class WorkSchedule {

    public static final LocalTime DEFAULT_START_TIME = LocalTime.of(8, 00);

    public static final LocalTime DEFAULT_END_TIME = LocalTime.of(17,30);

    private int businessCode;

    private Set<WorkingDay> workingDays;

    private String name;

    public WorkSchedule(Person employee) {
        workingDays = new HashSet<WorkingDay>();
    }

    public WorkSchedule() {
        workingDays = new HashSet<WorkingDay>();
    }

    public int getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(int code) {
        this.businessCode = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addWorkingDay(LocalDate day) {
        addWorkingDay(day, DEFAULT_START_TIME, DEFAULT_END_TIME);
    }

    public void updateWorkingDays(Collection<LocalDate> dates) {
        for (LocalDate date : dates){
            if (!workingDates().contains(date)) addWorkingDay(date);
        }
        for (LocalDate workingDay : workingDates()){
            if (workingDates().contains(workingDay) && !dates.contains(workingDay))
                removeWorkingDate(workingDay);
        }
    }

    public void removeWorkingDate(LocalDate day){
        WorkingDay workingDay = workingDays.stream()
                .filter(p -> p.getDay().isEqual(day))
                .findFirst()
                .get();
        workingDays.remove(workingDay);
    }

    public void addWorkingDay(LocalDate day, LocalTime timeFrom, LocalTime timeTo) {
        workingDays.add(new WorkingDay(day, timeFrom, timeTo));
    }

    public boolean isWorkingDay(LocalDate day) {
        return workingDays
                .stream()
                .anyMatch(d -> d.getDay().isEqual(day));
    }

    public boolean isWorkingTime(LocalDate day, LocalTime time) {
        return workingDays
                .stream()
                .anyMatch(
                        d -> d.getDay().isEqual(day)
                        && d.getStart().isBefore(time)
                        && d.getEnd().isAfter(time)
                );
    }

    public Set<LocalDate> workingDates() {
        Set<LocalDate> dates = new TreeSet<LocalDate>();
        workingDays.forEach(d -> dates.add(d.getDay()));
        return dates;
    }

    private class WorkingDay {

        private LocalDate day;

        private LocalTime start;

        private LocalTime end;

        private WorkingDay(LocalDate day, LocalTime start, LocalTime end) {
            Objects.requireNonNull(day);
            Objects.requireNonNull(start);
            Objects.requireNonNull(end);
            this.day = day;
            this.start = start;
            this.end = end;
        }

        public LocalDate getDay() {
            return day;
        }

        public LocalTime getStart() {
            return start;
        }

        public LocalTime getEnd() {
            return end;
        }
    }

}
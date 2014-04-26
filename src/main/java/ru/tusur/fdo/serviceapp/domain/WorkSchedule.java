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

    private Set<WorkingDay> workingDays;

    public WorkSchedule(Person employee) {
        workingDays = new HashSet<WorkingDay>();
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
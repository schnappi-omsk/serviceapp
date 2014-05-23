package ru.tusur.fdo.serviceapp.view.controller.pagebean;

import ru.tusur.fdo.serviceapp.domain.Person;
import ru.tusur.fdo.serviceapp.domain.WorkSchedule;

import java.time.LocalDate;

/**
 * serviceapp
 * ru.tusur.fdo.serviceapp.view.controller.pagebean
 * by Oleg Alekseev
 * 21.05.14.
 */
public class ScheduleBean {

    private WorkSchedule schedule;

    private boolean persisted;

    public ScheduleBean() {}

    public ScheduleBean(Person person, String name) {
        schedule = new WorkSchedule();
        schedule.setName(name);
        person.addSchedule(schedule);
    }

    public boolean isPersisted() {
        return persisted;
    }

    public void setPersisted(boolean persisted) {
        this.persisted = persisted;
    }

    public WorkSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(WorkSchedule schedule) {
        this.schedule = schedule;
    }

    public void addDateToSchedule(LocalDate date){
        schedule.addWorkingDay(date);
    }

}

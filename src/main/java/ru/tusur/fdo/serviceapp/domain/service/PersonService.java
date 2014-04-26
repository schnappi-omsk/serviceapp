package ru.tusur.fdo.serviceapp.domain.service;

import ru.tusur.fdo.serviceapp.domain.Person;

import java.util.Date;
import java.util.List;

/**
 * serviceapp
 * ru.tusur.fdo.serviceapp.domain
 * by Oleg Alekseev
 * 01.04.14.
 */
public class PersonService {

    private ScheduleService scheduleService;

    public void setScheduleService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    public List<Person> allEmployees() {
        return null;
    }

    public Person getById(String id) {
        return null;
    }

    public boolean isAvailableOn(Person employee, Date date) {
        return false;
    }

    public void save(Person employee) {

    }

    public void remove(Person employee) {

    }

}

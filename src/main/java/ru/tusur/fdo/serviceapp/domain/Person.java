package ru.tusur.fdo.serviceapp.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

/**
 * Created by schna_000 on 16.03.14.
 * version 1.0
 * Initial creation
 */
public class Person {

    private String id;

    private String name;

    private Role role;

    private Set<WorkSchedule> workSchedules;

    private List<Contact> contacts;

    public Person() {
        workSchedules = new HashSet<WorkSchedule>();
        contacts = new ArrayList<Contact>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Contact> contactList() {
        return Collections.unmodifiableList(contacts);
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void addSchedule(WorkSchedule schedule) {
        workSchedules.add(schedule);
    }

    public boolean isFreeOn(LocalDate date) {
        for (WorkSchedule schedule : workSchedules) {
            if (schedule.isWorkingDay(date)) return true;
        }
        return false;
    }

    public boolean isFreeOn(LocalDate date, LocalTime time) {
        if (isFreeOn(date)) {
            for (WorkSchedule schedule : workSchedules) {
                if (schedule.isWorkingTime(date, time)) return true;
            }
        }
        return false;
    }

}

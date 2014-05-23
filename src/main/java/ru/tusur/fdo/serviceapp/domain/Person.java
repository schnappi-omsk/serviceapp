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

    private int id;

    private String firstName;

    private String middleName;

    private String lastName;

    private Role role;

    private Set<WorkSchedule> workSchedules;

    private String email;

    private List<Contact> contacts;

    public Person() {
        workSchedules = new HashSet<WorkSchedule>();
        contacts = new ArrayList<Contact>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Contact> getContacts() {
        return Collections.unmodifiableList(contacts);
    }

    public Set<WorkSchedule> getWorkSchedules() {
        return Collections.unmodifiableSet(workSchedules);
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

package ru.tusur.fdo.serviceapp.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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

    private List<Contact> contacts = new ArrayList<Contact>();

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

}

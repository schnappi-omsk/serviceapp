package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by schna_000 on 16.03.14.
 * version 1.0
 * Initial creation
 */
public class Customer {

    private String name;

    private List<Contact> contacts = new ArrayList<Contact>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Contact> contactList() {
        return Collections.unmodifiableList(contacts);
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

}

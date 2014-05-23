package ru.tusur.fdo.serviceapp.view.controller.pagebean;

import ru.tusur.fdo.serviceapp.domain.Contact;

/**
 * serviceapp
 * ru.tusur.fdo.serviceapp.view.controller.pagebean
 * by Oleg Alekseev
 * 20.05.14.
 */
public class ContactBean {

    private Contact contact;

    private boolean persisted;

    public ContactBean() {}

    public ContactBean(String type, String value) {
        contact = new Contact();
        contact.setType(type);
        contact.setValue(value);
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public boolean isPersisted() {
        return persisted;
    }

    public void setPersisted(boolean persisted) {
        this.persisted = persisted;
    }
}

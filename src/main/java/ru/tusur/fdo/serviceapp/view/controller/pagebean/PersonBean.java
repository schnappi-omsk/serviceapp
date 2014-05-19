package ru.tusur.fdo.serviceapp.view.controller.pagebean;

import ru.tusur.fdo.serviceapp.domain.Person;

/**
 * serviceapp
 * ru.tusur.fdo.serviceapp.view.controller.pagebean
 * by Oleg Alekseev
 * 19.05.14.
 */
public class PersonBean {

    private Person person;

    private boolean persisted;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public boolean isPersisted() {
        return persisted;
    }

    public void setPersisted(boolean persisted) {
        this.persisted = persisted;
    }
}

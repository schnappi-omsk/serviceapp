package ru.tusur.fdo.serviceapp.view.controller.pagebean;

import ru.tusur.fdo.serviceapp.domain.Person;
import ru.tusur.fdo.serviceapp.domain.Request;

import java.util.Collection;

/**
 * serviceapp
 * ru.tusur.fdo.serviceapp.view.controller.pagebean
 * by Oleg Alekseev
 * 26.05.14.
 */
public class RequestBean {

    private Request request;

    private boolean persisted;

    private String targetDate;

    private String dueDate;

    private boolean closed;

    private int assignee;

    private Collection<Person> freeEmployees;

    public int getAssignee() {
        return assignee;
    }

    public void setAssignee(int assignee) {
        this.assignee = assignee;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public String getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(String targetDate) {
        this.targetDate = targetDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public boolean isPersisted() {
        return persisted;
    }

    public void setPersisted(boolean persisted) {
        this.persisted = persisted;
    }

    public Collection<Person> getFreeEmployees() {
        return freeEmployees;
    }

    public void setFreeEmployees(Collection<Person> freeEmployees) {
        this.freeEmployees = freeEmployees;
    }
}

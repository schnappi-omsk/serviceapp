package ru.tusur.fdo.serviceapp.view.controller.pagebean;

import ru.tusur.fdo.serviceapp.domain.Request;

/**
 * serviceapp
 * ru.tusur.fdo.serviceapp.view.controller.pagebean
 * by Oleg Alekseev
 * 26.05.14.
 */
public class RequestBean {

    private Request request;

    private boolean persisted;

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

}

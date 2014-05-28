package ru.tusur.fdo.serviceapp.domain;

/**
 * Created by schna_000 on 16.03.14.
 * version 1.0
 * Initial creation
 */
public enum RequestStatus {

    NEW("Новая"), ASSIGNED("Назначена"), REJECTED("Отказ"), CLOSED("Закрыта"), COMPLETED("Выполнена");

    private String text;

    public String getText() {
        return text;
    }

    RequestStatus(String text) {
        this.text = text;
    }
}

package ru.tusur.fdo.serviceapp.domain;

import java.time.LocalDate;

/**
 * Created by schna_000 on 16.03.14.
 * version 1.0
 * Initial creation
 */
public class Comment {

    private int id;

    private String text;

    private String author;

    private LocalDate date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

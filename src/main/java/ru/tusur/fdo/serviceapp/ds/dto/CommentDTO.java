package ru.tusur.fdo.serviceapp.ds.dto;

import javax.persistence.*;
import java.sql.Date;

/**
 * serviceapp
 * ru.tusur.fdo.serviceapp.ds.dto
 * by Oleg Alekseev
 * 03.06.14.
 */
@Entity
@Table(name = "comment")
public class CommentDTO {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private PersonDTO author;

    private java.sql.Date date;

    private String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PersonDTO getAuthor() {
        return author;
    }

    public void setAuthor(PersonDTO author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

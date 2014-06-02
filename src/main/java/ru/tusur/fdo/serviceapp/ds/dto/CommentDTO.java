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
@Table(name = "request_comment")
public class CommentDTO {

    @Id
    @GeneratedValue
    private int id;

    private String author;

    @ManyToOne
    private RequestDTO request;

    @Column(name = "comment_date")
    private java.sql.Date date;

    @Column(name = "comment_text")
    private String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RequestDTO getRequest() {
        return request;
    }

    public void setRequest(RequestDTO request) {
        this.request = request;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
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

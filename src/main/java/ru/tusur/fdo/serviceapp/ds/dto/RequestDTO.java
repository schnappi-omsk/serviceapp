package ru.tusur.fdo.serviceapp.ds.dto;

import javax.persistence.*;
import java.sql.Date;

/**
 * serviceapp
 * ru.tusur.fdo.serviceapp.ds.dto
 * by Oleg Alekseev
 * 25.05.14.
 */
@Entity
@Table(name = "Request")
public class RequestDTO {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private PersonDTO assignee;

    private String title;

    private String description;

    private Date creationDate;

    private Date dueDate;

    private Date targetDate;

    private String address;

    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PersonDTO getAssignee() {
        return assignee;
    }

    public void setAssignee(PersonDTO assignee) {
        this.assignee = assignee;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

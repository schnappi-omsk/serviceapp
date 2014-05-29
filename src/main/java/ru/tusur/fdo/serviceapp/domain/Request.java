package ru.tusur.fdo.serviceapp.domain;

import java.time.LocalDate;
import java.util.*;

/**
 * Created by schna_000 on 16.03.14.
 * version 1.0
 * Initial creation
 */
public class Request {

    private int id;

    private String title;

    private String description;

    private Person assignee;

    private List<Comment> comments = new ArrayList<Comment>();

    private List<Attachment> attachments = new ArrayList<Attachment>();

    private LocalDate creationDate;

    private LocalDate dueDate;

    private LocalDate targetDate;

    private RequestStatus status;

    private Customer customer;

    public Request() {
        creationDate = LocalDate.now();
        status = RequestStatus.NEW;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RequestStatus getStatus() {
        return status;
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

    public Person getAssignee() {
        return assignee;
    }

    public void setAssignee(Person assignee) {
        this.assignee = assignee;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void addAttachment(Attachment attachment) {
        attachments.add(attachment);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public List<Attachment> getAttachments() {
        return Collections.unmodifiableList(attachments);
    }

    public List<Comment> getComments() {
        return Collections.unmodifiableList(comments);
    }

    public void assignTo(Person assignee) {
        setAssignee(assignee);
        this.status = RequestStatus.ASSIGNED;
    }

    public void resolve() {
        this.status = RequestStatus.COMPLETED;
    }

    public void reopen() {
        this.status = RequestStatus.ASSIGNED;
    }

    public void reject() {
        this.status = RequestStatus.REJECTED;
    }

    public boolean closed() {
        return status == RequestStatus.CLOSED || status == RequestStatus.COMPLETED || status == RequestStatus.REJECTED;
    }

    public boolean inWork() {
        return status == RequestStatus.NEW || status == RequestStatus.ASSIGNED;
    }

    public void close() {
        this.status = RequestStatus.CLOSED;
    }
}

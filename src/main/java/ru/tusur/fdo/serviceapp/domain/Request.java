package ru.tusur.fdo.serviceapp.domain;

import java.time.LocalDate;
import java.util.*;

/**
 * Created by schna_000 on 16.03.14.
 * version 1.0
 * Initial creation
 */
public class Request {

    private Person requestor;

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

    }

    public void findFreeEmployees() {

    }

}

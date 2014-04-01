package domain;

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

    private Date creationDate;

    private Date dueDate;

    private RequestStatus status;

    private Customer customer;

    public Person getRequestor() {
        return requestor;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public Person getAssignee() {
        return assignee;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setRequestor(Person requestor) {
        this.requestor = requestor;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAssignee(Person assignee) {
        this.assignee = assignee;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
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

    public void autoAssign() {}

}

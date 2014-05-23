package ru.tusur.fdo.serviceapp.ds.dto;

import javax.persistence.*;

/**
 * serviceapp
 * ru.tusur.fdo.serviceapp.ds.dto
 * by Oleg Alekseev
 * 20.05.14.
 */
@Entity
@Table(name = "contact")
public class ContactDTO {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private PersonDTO person;

    private String type;

    private String value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}

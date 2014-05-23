package ru.tusur.fdo.serviceapp.ds.dto;

import javax.persistence.*;

/**
 * serviceapp
 * ru.tusur.fdo.serviceapp.ds.dto
 * by Oleg Alekseev
 * 18.05.14.
 */
@Entity
@Table(name = "person")
public class PersonDTO {

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = false)
    private String firstName;

    private String middleName;

    private String lastName;

    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

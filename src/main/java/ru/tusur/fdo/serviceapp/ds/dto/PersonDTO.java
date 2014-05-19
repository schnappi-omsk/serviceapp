package ru.tusur.fdo.serviceapp.ds.dto;

import javax.persistence.*;
import java.util.Set;

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
    private String name;

    private String businessCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

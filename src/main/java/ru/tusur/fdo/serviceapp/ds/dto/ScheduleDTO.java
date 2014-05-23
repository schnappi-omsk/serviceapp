package ru.tusur.fdo.serviceapp.ds.dto;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

/**
 * serviceapp
 * ru.tusur.fdo.serviceapp.ds.dto
 * by Oleg Alekseev
 * 18.05.14.
 */
@Entity
@Table(name = "schedule")
public class ScheduleDTO {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Date> workingDays;

    @ManyToOne
    private PersonDTO employee;

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

    public Set<Date> getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(Set<Date> workingDays) {
        this.workingDays = workingDays;
    }

    public PersonDTO getEmployee() {
        return employee;
    }

    public void setEmployee(PersonDTO employee) {
        this.employee = employee;
    }
}

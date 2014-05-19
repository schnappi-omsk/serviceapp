package ru.tusur.fdo.serviceapp.ds.dto;

import javax.persistence.*;
import java.time.LocalDate;
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

    @ElementCollection
    private Set<LocalDate> workingDays;

    @ManyToOne
    private PersonDTO employee;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<LocalDate> getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(Set<LocalDate> workingDays) {
        this.workingDays = workingDays;
    }

    public PersonDTO getEmployee() {
        return employee;
    }

    public void setEmployee(PersonDTO employee) {
        this.employee = employee;
    }
}

package ru.tusur.fdo.serviceapp.domain.service;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tusur.fdo.serviceapp.domain.Contact;
import ru.tusur.fdo.serviceapp.domain.Person;
import ru.tusur.fdo.serviceapp.domain.Role;
import ru.tusur.fdo.serviceapp.domain.WorkSchedule;
import ru.tusur.fdo.serviceapp.ds.dto.PersonDTO;
import ru.tusur.fdo.serviceapp.ds.repo.PersonRepository;
import ru.tusur.fdo.serviceapp.util.PasswordUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * serviceapp
 * ru.tusur.fdo.serviceapp.domain
 * by Oleg Alekseev
 * 01.04.14.
 */
@Service
public class PersonService {

    @Autowired
    private Mapper mapper;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private PersonRepository repository;

    public void setScheduleService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    public List<Person> allEmployees() {
        List<PersonDTO> storedEmployees = repository.findAll();
        List<Person> mappedEmployees = new ArrayList<>();
        storedEmployees.forEach(e -> mappedEmployees.add(mapper.map(e, Person.class)));
        return mappedEmployees;
    }

    public Person getById(int id) {
        PersonDTO dto = repository.findOne(id);
        Person employee = mapper.map(dto, Person.class);
        employee.setRole(Role.valueOf(dto.getRole()));
        List<Contact> contacts = contactService.employeeContacts(employee);
        contacts.forEach(employee::addContact);
        Collection<WorkSchedule> schedules = scheduleService.getEmployeeSchedules(employee);
        schedules.forEach(employee::addSchedule);
        return employee;
    }

    public boolean isAvailableOn(Person employee, LocalDate date) {
        Collection<WorkSchedule> schedules = scheduleService.getEmployeeSchedules(employee);
        return employee.isFreeOn(date);
    }

    public Person save(Person employee) {
        if (employee.getId() != 0) {
            Person stored = getById(employee.getId());
            if (employee.getPassword() == null || employee.getPassword().isEmpty()) {
                employee.setPassword(stored.getPassword());
            } else {
                if (!stored.getPassword().equals(employee.getPassword()))
                    employee.setPassword(PasswordUtils.md5(employee.getPassword()));
            }
        } else employee.setPassword(PasswordUtils.md5(employee.getPassword()));
        return mapper.map(repository.save(mapper.map(employee, PersonDTO.class)), Person.class);
    }

    public void remove(Person employee) {
        repository.delete(mapper.map(employee, PersonDTO.class));
    }

}

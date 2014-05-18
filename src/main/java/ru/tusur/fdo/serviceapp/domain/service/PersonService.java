package ru.tusur.fdo.serviceapp.domain.service;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tusur.fdo.serviceapp.domain.Person;
import ru.tusur.fdo.serviceapp.domain.WorkSchedule;
import ru.tusur.fdo.serviceapp.ds.dto.PersonDTO;
import ru.tusur.fdo.serviceapp.ds.repo.PersonRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * serviceapp
 * ru.tusur.fdo.serviceapp.domain
 * by Oleg Alekseev
 * 01.04.14.
 */
@Service
public class PersonService {

    private DozerBeanMapper mapper = new DozerBeanMapper();

    @Autowired
    private ScheduleService scheduleService;

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
        return mapper.map(repository.findOne(id), Person.class);
    }

    public boolean isAvailableOn(Person employee, LocalDate date) {
        Collection<WorkSchedule> schedules = scheduleService.getEmployeeSchedules(employee);
        return employee.isFreeOn(date);
    }

    public void save(Person employee) {
        repository.save(mapper.map(employee, PersonDTO.class));
    }

    public void remove(Person employee) {
        repository.delete(mapper.map(employee, PersonDTO.class));
    }

}

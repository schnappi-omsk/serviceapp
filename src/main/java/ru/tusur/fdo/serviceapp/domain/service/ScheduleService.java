package ru.tusur.fdo.serviceapp.domain.service;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tusur.fdo.serviceapp.domain.Person;
import ru.tusur.fdo.serviceapp.domain.WorkSchedule;
import ru.tusur.fdo.serviceapp.ds.dto.PersonDTO;
import ru.tusur.fdo.serviceapp.ds.dto.ScheduleDTO;
import ru.tusur.fdo.serviceapp.ds.repo.ScheduleRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * serviceapp
 * ru.tusur.fdo.serviceapp.domain
 * by Oleg Alekseev
 * 02.04.14.
 */
@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository repository;

    @Autowired
    private Mapper mapper;

    public Collection<WorkSchedule> getEmployeeSchedules(Person employee) {
        List<ScheduleDTO> storedSchedules = repository.getByEmployee_Id(employee.getId());
        storedSchedules.forEach(s -> employee.addSchedule(mapSchedule(s)));
        return employee.getWorkSchedules();
    }

    private WorkSchedule mapSchedule(ScheduleDTO dto){
        WorkSchedule schedule = new WorkSchedule();
        schedule.setBusinessCode(dto.getId());
        schedule.setName(dto.getName());
        for (Date date : dto.getWorkingDays()) {
            schedule.addWorkingDay(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        return schedule;
    }

    public void addWorkingDay(LocalDate day, WorkSchedule schedule, Person employee) {
        schedule.addWorkingDay(day);
        save(schedule, employee);
    }

    public WorkSchedule getOne(int id) {
        return mapSchedule(repository.findOne(id));
    }

    public WorkSchedule save(WorkSchedule schedule, Person employee) {
        ScheduleDTO dto = new ScheduleDTO();
        Set<Date> dates = new HashSet<>();
        for (LocalDate date : schedule.workingDates()) {
            Date converted = new Date(date.toEpochDay());
            dates.add(converted);
        }
        dto.setName(schedule.getName());
        dto.setEmployee(mapper.map(employee, PersonDTO.class));
        dto.setWorkingDays(dates);
        return mapSchedule(repository.save(dto));
    }

}

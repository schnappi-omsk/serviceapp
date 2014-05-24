package ru.tusur.fdo.serviceapp.domain.service;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tusur.fdo.serviceapp.domain.Person;
import ru.tusur.fdo.serviceapp.domain.WorkSchedule;
import ru.tusur.fdo.serviceapp.ds.dto.PersonDTO;
import ru.tusur.fdo.serviceapp.ds.dto.ScheduleDTO;
import ru.tusur.fdo.serviceapp.ds.repo.ScheduleRepository;
import ru.tusur.fdo.serviceapp.util.DateUtils;

import java.time.Instant;
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
        ScheduleDTO dto = schedule.getBusinessCode() == 0 ? new ScheduleDTO() : repository.findOne(schedule.getBusinessCode());
        dto.setId(schedule.getBusinessCode());
        dto.setName(schedule.getName());
        dto.setEmployee(mapper.map(employee, PersonDTO.class));
        for (LocalDate date : schedule.workingDates()) {
            Instant instant = date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
            Date converted = Date.from(instant);
            boolean added = !dto.getWorkingDays().contains(converted);
            if (added) dto.getWorkingDays().add(converted);
        }
        Set<Date> deletedDates = new HashSet<>();
        for (Date date : dto.getWorkingDays()) {
            boolean deleted = !schedule.isWorkingDay(DateUtils.toLocalDate(date));
            if (deleted) deletedDates.add(date);
        }
        deletedDates.stream().forEach(dto.getWorkingDays()::remove);
        return mapSchedule(repository.save(dto));
    }


}

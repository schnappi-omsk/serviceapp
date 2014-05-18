package ru.tusur.fdo.serviceapp.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tusur.fdo.serviceapp.domain.Person;
import ru.tusur.fdo.serviceapp.domain.WorkSchedule;
import ru.tusur.fdo.serviceapp.ds.dto.ScheduleDTO;
import ru.tusur.fdo.serviceapp.ds.repo.ScheduleRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

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

    public Collection<WorkSchedule> getEmployeeSchedules(Person employee) {
        List<ScheduleDTO> storedSchedules = repository.getByEmployee_Id(employee.getId());
        storedSchedules.forEach(s -> employee.addSchedule(mapSchedule(s)));
        return employee.schedules();
    }

    private WorkSchedule mapSchedule(ScheduleDTO dto){
        WorkSchedule schedule = new WorkSchedule();
        for (LocalDate date : dto.getWorkingDays()) {
            schedule.addWorkingDay(date);
        }
        return schedule;
    }

}

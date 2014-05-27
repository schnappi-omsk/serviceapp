package ru.tusur.fdo.serviceapp.ds.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.tusur.fdo.serviceapp.ds.dto.PersonDTO;
import ru.tusur.fdo.serviceapp.ds.dto.ScheduleDTO;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * serviceapp
 * ru.tusur.fdo.serviceapp.ds.repo
 * by Oleg Alekseev
 * 18.05.14.
 */
public interface ScheduleRepository extends JpaRepository<ScheduleDTO, Integer> {

    List<ScheduleDTO> getByEmployee_Id(int id);

}
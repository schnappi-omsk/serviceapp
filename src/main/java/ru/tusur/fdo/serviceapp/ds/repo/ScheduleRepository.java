package ru.tusur.fdo.serviceapp.ds.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tusur.fdo.serviceapp.ds.dto.ScheduleDTO;

import java.util.List;

/**
 * serviceapp
 * ru.tusur.fdo.serviceapp.ds.repo
 * by Oleg Alekseev
 * 18.05.14.
 */
public interface ScheduleRepository extends JpaRepository<ScheduleDTO, Integer> {

    List<ScheduleDTO> getByEmployee_Id(int id);

}
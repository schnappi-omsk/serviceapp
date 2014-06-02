package ru.tusur.fdo.serviceapp.ds.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tusur.fdo.serviceapp.ds.dto.RequestDTO;

import java.sql.Date;
import java.util.List;

/**
 * serviceapp
 * ru.tusur.fdo.serviceapp.ds.repo
 * by Oleg Alekseev
 * 25.05.14.
 */
public interface RequestRepository extends JpaRepository<RequestDTO, Integer> {

    List<RequestDTO> findByAssignee_IdAndTargetDateBetween(int assigneeId, Date from, Date to);

    List<RequestDTO> findByTargetDateBetween(Date from, Date to);

    List<RequestDTO> findByStatus(String status);

    List<RequestDTO> findByDueDateAndStatus(Date dueDate, String status);

}
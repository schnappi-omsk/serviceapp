package ru.tusur.fdo.serviceapp.ds.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tusur.fdo.serviceapp.ds.dto.CommentDTO;

import java.util.List;

/**
 * serviceapp
 * ru.tusur.fdo.serviceapp.ds.repo
 * by Oleg Alekseev
 * 03.06.14.
 */
public interface CommentRepository extends JpaRepository<CommentDTO, Integer> {

    List<CommentDTO> findByRequest_Id(int id);

}

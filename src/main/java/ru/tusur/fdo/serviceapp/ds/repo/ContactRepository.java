package ru.tusur.fdo.serviceapp.ds.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tusur.fdo.serviceapp.ds.dto.ContactDTO;

import java.util.List;

/**
 * serviceapp
 * ru.tusur.fdo.serviceapp.ds.repo
 * by Oleg Alekseev
 * 20.05.14.
 */
public interface ContactRepository extends JpaRepository<ContactDTO, Integer> {

    List<ContactDTO> findByPerson_Id(int id);

}

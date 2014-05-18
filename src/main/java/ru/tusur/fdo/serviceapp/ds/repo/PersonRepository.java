package ru.tusur.fdo.serviceapp.ds.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tusur.fdo.serviceapp.ds.dto.PersonDTO;

/**
 * serviceapp
 * ru.tusur.fdo.serviceapp.ds.repo
 * by Oleg Alekseev
 * 18.05.14.
 */
public interface PersonRepository extends JpaRepository<PersonDTO, Integer> {
}

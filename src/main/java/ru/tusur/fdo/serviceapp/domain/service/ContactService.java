package ru.tusur.fdo.serviceapp.domain.service;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tusur.fdo.serviceapp.domain.Contact;
import ru.tusur.fdo.serviceapp.domain.Person;
import ru.tusur.fdo.serviceapp.ds.dto.ContactDTO;
import ru.tusur.fdo.serviceapp.ds.dto.PersonDTO;
import ru.tusur.fdo.serviceapp.ds.repo.ContactRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * serviceapp
 * ru.tusur.fdo.serviceapp.domain.service
 * by Oleg Alekseev
 * 20.05.14.
 */
@Service
public class ContactService {

    @Autowired
    private ContactRepository repository;

    @Autowired
    private Mapper mapper;

    public List<Contact> employeeContacts(Person person) {
        List<ContactDTO> storedContacts = repository.findByPerson_Id(person.getId());
        List<Contact> result = new ArrayList<>();
        storedContacts.forEach(s -> result.add(mapper.map(s, Contact.class)));
        return result;
    }

    public void saveContact(Person person, Contact contact) {
        ContactDTO record = mapper.map(contact, ContactDTO.class);
        record.setPerson(mapper.map(person, PersonDTO.class));
        repository.save(record);
    }

    public void saveContacts(Person person, List<Contact> contacts) {
        contacts.forEach(c -> saveContact(person, c));
    }

    public void removeContact(Person person, Contact contact) {
        ContactDTO record = repository.getOne(contact.getId());
        repository.delete(record);
    }

}

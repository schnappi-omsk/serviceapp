package ru.tusur.fdo.serviceapp.domain.service;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tusur.fdo.serviceapp.domain.Person;
import ru.tusur.fdo.serviceapp.domain.Request;
import ru.tusur.fdo.serviceapp.ds.dto.PersonDTO;
import ru.tusur.fdo.serviceapp.ds.dto.RequestDTO;
import ru.tusur.fdo.serviceapp.ds.repo.RequestRepository;
import ru.tusur.fdo.serviceapp.util.DateUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * serviceapp
 * ru.tusur.fdo.serviceapp.domain
 * by Oleg Alekseev
 * 01.04.14.
 */
@Service
public class RequestService {

    @Autowired
    private RequestRepository repository;

    @Autowired
    private PersonService personService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private Mapper mapper;

    public List<Request> allRequests() {
        List<RequestDTO> storedRequests = repository.findAll();
        List<Request> result = new ArrayList<>();
        storedRequests.forEach(r -> result.add(mapRequest(r)));
        return result;
    }

    public Request getById(int id) {
        return mapRequest(repository.findOne(id));
    }

    public Request save(Request request) {
        boolean newRecord = request.getId() == 0;
        RequestDTO dto = newRecord ? new RequestDTO() : repository.findOne(request.getId());
        dto.setTitle(request.getTitle());
        dto.setDescription(request.getDescription());
        dto.setCreationDate(DateUtils.sqlDateFromLocal(request.getCreationDate()));
        dto.setTargetDate(DateUtils.sqlDateFromLocal(request.getTargetDate()));
        dto.setDueDate(DateUtils.sqlDateFromLocal(request.getDueDate()));
//        dto.setAssignee(mapper.map(request.getAssignee(), PersonDTO.class));
//        dto.setStatus(request.getStatus().getText());
        return mapRequest(repository.save(dto));
    }

    public void remove(Request request) {
        repository.delete(request.getId());
    }

    public Collection<Person> findFreeEmployees(LocalDate date) {
        return scheduleService.freeEmployees(date);
    }

    private Request mapRequest(RequestDTO dto) {
        Request dest = new Request();
        dest.setId(dto.getId());
        dest.setCreationDate(DateUtils.localFromSqlDate(dto.getCreationDate()));
        dest.setTargetDate(DateUtils.localFromSqlDate(dto.getTargetDate()));
        dest.setDueDate(DateUtils.toLocalDate(dto.getDueDate()));
        dest.setTitle(dto.getTitle());
        dest.setDescription(dto.getDescription());
//        Person employee = personService.getById(dto.getAssignee().getId());
//        dest.assignTo(employee);
        return dest;
    }

}

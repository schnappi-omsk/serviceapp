package ru.tusur.fdo.serviceapp.domain.service;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tusur.fdo.serviceapp.domain.Person;
import ru.tusur.fdo.serviceapp.domain.Request;
import ru.tusur.fdo.serviceapp.domain.RequestStatus;
import ru.tusur.fdo.serviceapp.domain.service.notification.Notifier;
import ru.tusur.fdo.serviceapp.ds.dto.PersonDTO;
import ru.tusur.fdo.serviceapp.ds.dto.RequestDTO;
import ru.tusur.fdo.serviceapp.ds.repo.RequestRepository;
import ru.tusur.fdo.serviceapp.util.DateUtils;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * serviceapp
 * ru.tusur.fdo.serviceapp.domain
 * by Oleg Alekseev
 * 01.04.14.
 */
@Service
public class RequestService {

    private static final String MAIL_FROM = "noreply@tusur-server.ru";

    private static final String MAIL_SUBJECT = "На вас назначена заявка №%d";

    private static final String MAIL_BODY = "Уважаемый %s %s %s! <br /> <br />" +
            "На вас назначили заявку \"%s\". <br />" +
            "Детали заявки: <br /> <br /> %s <br /> <br />" +
            "Проехать по адресу %s <br />" +
            "Желаемая дата исполнения - %s <br /> " +
            "Крайний срок исполнения - %s <br /> " +
            "<br />" +
            "Просмотреть детали, а также закрыть заявку или отказать в обслуживании " +
            "Вы можете, зайдя в систему под своим логином. <br />" +
            "<br />" +
            "Спасибо!";

    @Autowired
    private RequestRepository repository;

    @Autowired
    private PersonService personService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private Notifier notifier;

    @Autowired
    private Mapper mapper;

    public List<Request> allRequests() {
        List<RequestDTO> storedRequests = repository.findAll();
        List<Request> result = new ArrayList<>();
        storedRequests.forEach(r -> result.add(mapRequest(r)));
        return result;
    }

    public List<Request> requestsInRange(LocalDate from, LocalDate to) {
        java.sql.Date storedFrom = DateUtils.sqlDateFromLocal(from);
        java.sql.Date storedTo = DateUtils.sqlDateFromLocal(to);
        List<RequestDTO> queryResult = repository.findByTargetDateBetween(storedFrom, storedTo);
        List<Request> result = new ArrayList<>();
        queryResult.forEach(r -> result.add(mapRequest(r)));
        return Collections.unmodifiableList(result);
    }

    public List<Request> requestsInRangeByEmployee(LocalDate from, LocalDate to, Person employee) {
        List<Request> allRequestsInRange = requestsInRange(from, to);
        return allRequestsInRange.stream()
                .filter(r -> r.getAssignee().getId() == employee.getId())
                .collect(Collectors.toList());
    }

    public List<Request> requestsByStatus(RequestStatus status) {
        List<RequestDTO> storedRequests = repository.findByStatus(status.name());
        List<Request> result = new ArrayList<>();
        storedRequests.forEach(r -> result.add(mapRequest(r)));
        return Collections.unmodifiableList(result);
    }

    public List<Request> overdueRequests() {
        List<Request> assignedRequests = requestsByStatus(RequestStatus.ASSIGNED);
        return assignedRequests.stream()
                .filter(r -> r.getDueDate().isBefore(LocalDate.now())).collect(Collectors.toList());
    }

    public Request getById(int id) {
        return mapRequest(repository.findOne(id));
    }

    public void closeRequest(Request request) {
        request.close();
    }

    public Request save(Request request) {
        boolean newRecord = request.getId() == 0;
        RequestDTO dto = newRecord ? new RequestDTO() : repository.findOne(request.getId());
        dto.setTitle(request.getTitle());
        dto.setDescription(request.getDescription());
        dto.setAddress(request.getAddress());
        dto.setCreationDate(DateUtils.sqlDateFromLocal(request.getCreationDate()));
        dto.setTargetDate(DateUtils.sqlDateFromLocal(request.getTargetDate()));
        dto.setDueDate(DateUtils.sqlDateFromLocal(request.getDueDate()));
        dto.setAssignee(mapper.map(request.getAssignee(), PersonDTO.class));
        dto.setStatus(request.getStatus().getText());
        Request result = mapRequest(repository.save(dto));
        String subj = String.format(MAIL_SUBJECT, result.getId());
        String body = String.format(MAIL_BODY,
                result.getAssignee().getLastName(),
                result.getAssignee().getFirstName(),
                result.getAssignee().getMiddleName(),
                result.getTitle(),
                request.getDescription(),
                request.getAddress(),
                DateUtils.stringFromLocalDate(result.getTargetDate()),
                DateUtils.stringFromLocalDate(result.getDueDate())
                );
        String to = result.getAssignee().getEmail();
        notifier.sendNotification(MAIL_FROM, to, subj, body);
        return result;
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
        dest.setAddress(dto.getAddress());
        dest.setTargetDate(DateUtils.localFromSqlDate(dto.getTargetDate()));
        dest.setDueDate(DateUtils.toLocalDate(dto.getDueDate()));
        dest.setTitle(dto.getTitle());
        dest.setDescription(dto.getDescription());
        Person employee = personService.getById(dto.getAssignee().getId());
        dest.assignTo(employee);
        return dest;
    }

}

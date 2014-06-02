package ru.tusur.fdo.serviceapp.domain.service;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tusur.fdo.serviceapp.domain.Comment;
import ru.tusur.fdo.serviceapp.domain.Request;
import ru.tusur.fdo.serviceapp.ds.dto.CommentDTO;
import ru.tusur.fdo.serviceapp.ds.dto.PersonDTO;
import ru.tusur.fdo.serviceapp.ds.dto.RequestDTO;
import ru.tusur.fdo.serviceapp.ds.repo.CommentRepository;
import ru.tusur.fdo.serviceapp.ds.repo.RequestRepository;
import ru.tusur.fdo.serviceapp.util.DateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * serviceapp
 * ru.tusur.fdo.serviceapp.domain.service
 * by Oleg Alekseev
 * 03.06.14.
 */
@Service
public class CommentService {

    @Autowired
    private CommentRepository repository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    Mapper mapper;

    public List<Comment> commentsByRequest(Request request) {
        List<CommentDTO> stored = repository.findByRequest_Id(request.getId());
        List<Comment> result = new ArrayList<>();
        stored.forEach(c -> result.add(mapComment(c)));
        result.forEach(request::addComment);
        return result;
    }

    public void addComment(Comment comment, Request request) {
        CommentDTO dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setAuthor(comment.getAuthor());
        dto.setText(comment.getText());
        RequestDTO requestDTO = requestRepository.findOne(request.getId());
        dto.setRequest(requestDTO);
        dto.setDate(DateUtils.sqlDateFromLocal(comment.getDate()));
        repository.save(dto);
    }

    private Comment mapComment(CommentDTO dto) {
        Comment result = new Comment();
        result.setId(dto.getId());
        result.setAuthor(dto.getAuthor());
        result.setDate(DateUtils.localFromSqlDate(dto.getDate()));
        result.setText(dto.getText());
        return result;
    }

}

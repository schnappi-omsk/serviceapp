package ru.tusur.fdo.serviceapp.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.tusur.fdo.serviceapp.domain.service.PersonService;
import ru.tusur.fdo.serviceapp.domain.service.RequestService;
import ru.tusur.fdo.serviceapp.util.DateUtils;
import ru.tusur.fdo.serviceapp.view.controller.pagebean.PersonBean;
import ru.tusur.fdo.serviceapp.view.controller.pagebean.RequestBean;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

/**
 * serviceapp
 * ru.tusur.fdo.serviceapp.view.controller
 * by Oleg Alekseev
 * 25.05.14.
 */
@Controller
@RequestMapping("request")
public class RequestController {

    @Autowired
    private RequestService service;

    @Autowired
    private PersonService personService;

    @RequestMapping("/")
    public ModelAndView allRequests() {
        return new ModelAndView("requestList", "requests", service.allRequests());
    }

    @RequestMapping("/new/")
    public ModelAndView newRequest(@ModelAttribute RequestBean bean){
        return editRequest(0, bean);
    }

    @RequestMapping("/{id}/")
    public ModelAndView editRequest(@PathVariable int id, @ModelAttribute RequestBean bean) {
        boolean newRequest = id == 0;
        bean.setPersisted(!newRequest);
        if (bean.isPersisted()){
            bean.setRequest(service.getById(id));
            bean.setTargetDate(DateUtils.stringFromLocalDate(bean.getRequest().getTargetDate()));
            bean.setDueDate(DateUtils.stringFromLocalDate(bean.getRequest().getDueDate()));
        } else {
            bean.setTargetDate(DateUtils.stringFromLocalDate(LocalDate.now()));
            bean.setDueDate(DateUtils.stringFromLocalDate(LocalDate.now()));
        }
        return new ModelAndView("requestEdit", "requestBean", bean);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView saveRequest(@ModelAttribute RequestBean bean, HttpServletRequest req) {
        bean.getRequest().setTargetDate(DateUtils.localFromString(req.getParameter("targetDate")));
        bean.getRequest().setDueDate(DateUtils.localFromString(req.getParameter("dueDate")));
        int employeeId = Integer.parseInt(req.getParameter("employee_id"));
        bean.getRequest().assignTo(personService.getById(employeeId));
        bean.setRequest(service.save(bean.getRequest()));
        return new ModelAndView("requestEdit");
    }

    @RequestMapping("/**/free_employees/")
    public ModelAndView freeEmployees(HttpServletRequest req,
                                      @ModelAttribute RequestBean bean) {
        LocalDate targetDate = DateUtils.localFromString(req.getParameter("targetDate"));
        bean.setFreeEmployees(service.findFreeEmployees(targetDate));
        return new ModelAndView("freeEmployees", "employeesList", bean.getFreeEmployees());
    }

    @RequestMapping(value = "/**/get_assignee/", method = RequestMethod.POST)
    @ResponseBody
    public PersonBean getEmployee(HttpServletRequest req,
                                  @ModelAttribute RequestBean bean) {
        int employeeId = Integer.parseInt(req.getParameter("employee_id"));
        PersonBean result = new PersonBean();
        result.setPerson(personService.getById(employeeId));
        result.setPersisted(true);
        return result;
    }

}

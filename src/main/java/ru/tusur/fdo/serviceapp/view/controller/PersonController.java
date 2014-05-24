package ru.tusur.fdo.serviceapp.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.tusur.fdo.serviceapp.domain.service.ContactService;
import ru.tusur.fdo.serviceapp.domain.service.PersonService;
import ru.tusur.fdo.serviceapp.domain.service.ScheduleService;
import ru.tusur.fdo.serviceapp.util.DateUtils;
import ru.tusur.fdo.serviceapp.view.controller.pagebean.ContactBean;
import ru.tusur.fdo.serviceapp.view.controller.pagebean.PersonBean;
import ru.tusur.fdo.serviceapp.view.controller.pagebean.ScheduleBean;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * serviceapp
 * ru.tusur.fdo.serviceapp.view.controller
 * by Oleg Alekseev
 * 19.05.14.
 */
@Controller
@RequestMapping("employee")
public class PersonController {

    @Autowired
    private PersonService service;

    @Autowired
    private ContactService contactService;

    @Autowired
    private ScheduleService scheduleService;

    @RequestMapping("/")
    public ModelAndView allEmployees() {
        return new ModelAndView("employeesList", "employees",  service.allEmployees());
    }

    @RequestMapping("/edit/{id}/")
    public ModelAndView editEmployee(@PathVariable String id, @ModelAttribute PersonBean bean) {
        boolean newEmployee = id.equals("new");
        int personId = 0;
        try {
            personId = Integer.parseInt(id);
        } catch (Exception e) {
            //TODO: implement logging
        }
        newEmployee &= personId == 0;
        bean.setPersisted(!newEmployee);
        if (bean.isPersisted()) {
            bean.setPerson(service.getById(personId));
        }
        return new ModelAndView("employeeEdit", "personBean", bean);
    }

    @RequestMapping(value = "/edit/{id}/addContact", method = RequestMethod.POST)
    @ResponseBody
    public ContactBean addContact(@ModelAttribute PersonBean personBean,
                                  HttpServletRequest request,
                                  @PathVariable int id) {
        personBean.setPerson(service.getById(id));
        ContactBean result = new ContactBean(request.getParameter("contactType"), request.getParameter("contactValue"));
        personBean.getPerson().addContact(result.getContact());
        contactService.saveContact(personBean.getPerson(), result.getContact());
        return result;
    }

    @RequestMapping(value = "/edit/{id}/addSchedule", method = RequestMethod.POST)
    @ResponseBody
    public ScheduleBean addSchedule(@ModelAttribute PersonBean bean,
                                    HttpServletRequest request,
                                    @PathVariable int id) {
        bean.setPerson(service.getById(id));
        ScheduleBean result = new ScheduleBean(bean.getPerson(), request.getParameter("scheduleName"));
        result.setSchedule(scheduleService.save(result.getSchedule(), bean.getPerson()));
        return result;
    }

    @RequestMapping("/edit/{id}/schedule/new")
    public ModelAndView editSchedule(@PathVariable int id, @ModelAttribute ScheduleBean bean) {
        return editSchedule(id, 0, bean);
    }

    @RequestMapping("/edit/{id}/schedule/{scheduleId}/")
    public ModelAndView editSchedule(@PathVariable int id,
                                     @PathVariable int scheduleId,
                                     @ModelAttribute ScheduleBean bean) {
        boolean newSchedule = scheduleId == 0;
        bean.setPersisted(!newSchedule);
        if (bean.isPersisted()) {
            bean.setSchedule(scheduleService.getOne(scheduleId));
        }
        return new ModelAndView("scheduleEdit", "scheduleBean", bean);
    }

    @RequestMapping(value = "/save/", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute PersonBean bean) {
        bean.setPerson(service.save(bean.getPerson()));
        return new ModelAndView(String.format("redirect:/employee/edit/%d/", bean.getPerson().getId()));
    }

    @RequestMapping(value = "/edit/{id}/schedule/{scheduleId}/add_dates", method = RequestMethod.POST)
    @ResponseBody
    public ScheduleBean updateDates(@PathVariable int id,
                                 @PathVariable int scheduleId,
                                 @ModelAttribute ScheduleBean bean,
                                 HttpServletRequest request){
        String[] dates = request.getParameterValues("dates[]");
        bean.setSchedule(scheduleService.getOne(scheduleId));
        Collection<LocalDate> datesToUpdate = new HashSet<>();
        for (String date : dates) {
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            datesToUpdate.add(localDate);
        }
        bean.getSchedule().updateWorkingDays(datesToUpdate);
        scheduleService.save(bean.getSchedule(), service.getById(id));
        return bean;
    }

    @RequestMapping(value = "/edit/{id}/schedule/{scheduleId}/get_dates", method = RequestMethod.GET)
    @ResponseBody
    public Object[] getDates(@PathVariable int id,
                                 @PathVariable int scheduleId,
                                 @ModelAttribute ScheduleBean bean) {
        bean.setSchedule(scheduleService.getOne(scheduleId));
        List<String> dateList = new ArrayList<String>();
        bean.getSchedule()
                .workingDates()
                .forEach(d -> dateList.add(DateUtils.stringFromLocalDate(d)));
        return dateList.toArray();
    }

}

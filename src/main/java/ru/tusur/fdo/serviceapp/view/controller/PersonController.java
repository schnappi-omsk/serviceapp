package ru.tusur.fdo.serviceapp.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.tusur.fdo.serviceapp.domain.Person;
import ru.tusur.fdo.serviceapp.domain.service.PersonService;
import ru.tusur.fdo.serviceapp.view.controller.pagebean.PersonBean;

import java.util.Collection;
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

    @RequestMapping("/")
    public ModelAndView allEmployees() {
        List<Person> employees = service.allEmployees();
        return new ModelAndView("employeesList", "employees", employees);
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
        bean.setPersisted(newEmployee);
        if (!newEmployee) {
            bean.setPerson(service.getById(personId));
        }
        return new ModelAndView("employeeEdit", "personBean", bean);
    }

    @RequestMapping(value = "/save/", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute PersonBean bean) {
        service.save(bean.getPerson());
        return new ModelAndView("redirect:/employee/");
    }

}

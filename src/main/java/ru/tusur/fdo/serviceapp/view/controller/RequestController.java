package ru.tusur.fdo.serviceapp.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.tusur.fdo.serviceapp.domain.service.RequestService;
import ru.tusur.fdo.serviceapp.view.controller.pagebean.RequestBean;

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
        }
        return new ModelAndView("requestEdit");
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView saveRequest(@ModelAttribute RequestBean bean) {
        return new ModelAndView("requestEdit");
    }

}

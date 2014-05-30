package ru.tusur.fdo.serviceapp.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * serviceapp
 * ru.tusur.fdo.serviceapp.view.controller
 * by Oleg Alekseev
 * 31.05.14.
 */
@Controller
@RequestMapping("login")
public class LoginController {

    @RequestMapping("/")
    public String login() {
        return "login";
    }

}

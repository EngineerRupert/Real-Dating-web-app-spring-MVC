package ru.realdating.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.realdating.project.service.UserSession;

@Controller
@RequestMapping(path = "/")
@SessionAttributes("userSession")
public class MainPageController {

    @GetMapping("/")
    public String index(UserSession userSession) {
        return "index";
    }

    @ModelAttribute("userSession")
    public UserSession createUserSession() {
        return new UserSession();
    }

}

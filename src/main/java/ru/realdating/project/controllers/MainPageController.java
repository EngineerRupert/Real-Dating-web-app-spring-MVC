package ru.realdating.project.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static java.util.Objects.*;

@Controller
@RequestMapping(path = "/")
public class MainPageController {

    @GetMapping("/")
    public String index(
            Model model,
            Authentication authentication) {
        boolean loggedIn = nonNull(authentication) && authentication.isAuthenticated();
        model.addAttribute("loggedIn", loggedIn);
        return "index";
    }

}

package ru.realdating.project.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.realdating.project.service.UserSession;

@Controller
@RequestMapping(path = "/menu")
//@SessionAttributes("userSession")
public class MenuController {

    @GetMapping("/user-menu")
    public String userMainMenu(
            Model model,
            Authentication authentication) {
        boolean loggedIn = authentication != null && authentication.isAuthenticated();
        String userName = "";
        if (loggedIn) {
            userName = authentication.getName();
        }
        model.addAttribute("loggedIn", loggedIn);
        model.addAttribute("getLogin", userName);
        return "/menu/user_main_menu";
    }

    @GetMapping("/logout")
    public String logout() {
//        userSession.clearSession();
        return "redirect:/";
    }
}

package ru.realdating.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.realdating.project.dao.UserCardDao;
import ru.realdating.project.dao.UserDao;
import ru.realdating.project.model.User;
import ru.realdating.project.model.UserCard;

@Controller
@RequestMapping(path = "/usercard")
public class AvatarController {

    @Autowired
    private UserCardDao userCardDao;

    @Autowired
    private UserCard userCard;

    @Autowired
    private UserDao userDao;

    @GetMapping("/upload-avatar")
    public String uploadAvatar(
            Authentication authentication,
            Model model
    ) {
        model.addAttribute("getLogin", authentication.getName());
        return "/usercard/upload_avatar";
    }

    @PostMapping("/upload-avatar")
    public String handleUploadAvatar(
            @RequestParam MultipartFile file,
            Authentication authentication
    ) throws Exception {

        String userName = authentication.getName();
        User user = userDao.findUserByLogin(userName);

        userCard = userCardDao.findUserCard(user.getId());
        userCard.setFoto(file.getBytes());
        userCardDao.refreshMainInfoUserCard(userCard);
        return "redirect:/menu/user-menu";
    }

}

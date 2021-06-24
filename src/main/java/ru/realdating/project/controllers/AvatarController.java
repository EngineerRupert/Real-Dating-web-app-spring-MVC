package ru.realdating.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.realdating.project.dao.UserCardDao;
import ru.realdating.project.model.UserCard;
import ru.realdating.project.service.UserSession;

@Controller
@RequestMapping(path = "/usercard")
@SessionAttributes("userSession")
public class AvatarController {

    @Autowired
    private UserCardDao userCardDao;

    @Autowired
    private UserCard userCard;

    @GetMapping("/upload-avatar")
    public String uploadAvatar() {
        return "/usercard/upload_avatar";
    }

    @PostMapping("/upload-avatar")
    public String handleUploadAvatar(
            @RequestParam MultipartFile file,
            UserSession userSession
    ) throws Exception {
        userCard = userCardDao.findUserCard(userSession.getId());
        userCard.setFoto(file.getBytes());
        userCardDao.refreshMainInfoUserCard(userCard);
        return "redirect:/menu/user-menu";
    }

}

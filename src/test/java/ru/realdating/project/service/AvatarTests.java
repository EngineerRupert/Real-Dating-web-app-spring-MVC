package ru.realdating.project.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.multipart.MultipartFile;
import ru.realdating.project.TestConfig;
import ru.realdating.project.dao.UserCardDao;
import ru.realdating.project.dao.UserDao;
import ru.realdating.project.model.User;
import ru.realdating.project.model.UserCard;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AvatarTests {

    @Autowired
    private UserCardDao userCardDao;

    @Autowired
    private UserDao userDao;

    @Test
    public void checkAvatarTestFull() throws IOException {
        MultipartFile multipartFile = new MultipartFile() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getOriginalFilename() {
                return null;
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return new byte[0];
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public void transferTo(File file) throws IOException, IllegalStateException {

            }
        };

        User user = userDao.createUser("admin", "admin");
        UserCard userCard = userCardDao.createUserCard(user);
        userCard.setFoto(multipartFile.getBytes());

        assertNotNull(userCard.getFoto());

        AvatarService avatarService = new AvatarService();
        assertNotNull(avatarService.checkAvatar(userCard));
        assertTrue(avatarService.checkAvatar(userCard));
    }

    @Test
    public void checkAvatarTestEmpty() {
        User user = userDao.createUser("admin", "admin");
        UserCard userCard = userCardDao.createUserCard(user);

        assertNull(userCard.getFoto());

        AvatarService avatarService = new AvatarService();
        assertFalse(avatarService.checkAvatar(userCard));
    }

}
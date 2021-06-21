package ru.realdating.project.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.realdating.project.config.TestConfigWithOutHibernate;
import ru.realdating.project.dao.UserCardDao;
import ru.realdating.project.dao.UserDao;
import ru.realdating.project.model.User;
import ru.realdating.project.model.UserCard;
import ru.realdating.project.service.UserSession;

@SpringBootTest
@ContextConfiguration(classes = TestConfigWithOutHibernate.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    public UserDao userDao;

    @MockBean
    public UserCardDao userCardDao;

    @MockBean
    public UserCard userCard;

    @Test
    public void testLogIn() throws Exception {
        User user = new User("user","password");
        user.setId(1);

        Mockito.when(userDao.findUserByLoginAndPassword("user","password"))
                .thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/user/log-in")
                .param("login", "user")
                .param("password", "password")
        ).andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.request().sessionAttribute("userSession", new UserSession(1,"user")));
    }

}

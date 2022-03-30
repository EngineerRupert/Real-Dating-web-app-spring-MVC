package ru.realdating.project.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.realdating.project.TestConfig;
import ru.realdating.project.config.SecurityConfig;
import ru.realdating.project.dao.FindUserCardsDao;
import ru.realdating.project.dao.UserCardDao;
import ru.realdating.project.dao.UserDao;
import ru.realdating.project.model.User;
import ru.realdating.project.model.UserCard;
import ru.realdating.project.service.DetailsService;

@SpringBootTest
@AutoConfigureMockMvc
@Import(SecurityConfig.class)
@ContextConfiguration(classes = TestConfig.class)
@ComponentScan(basePackages = "ru.realdating.project.controllers")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserCardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDao userDao;

    @MockBean
    private UserCardDao userCardDao;

    @MockBean
    private FindUserCardsDao findUserCardsDao;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private DetailsService detailsService;

    @MockBean
    private UserCard userCard;

    @Test
    @WithMockUser
    public void editUserCardTest() throws Exception {
        User createdUser = new User("user", "password");
        createdUser.setId(1);

        mockMvc.perform(MockMvcRequestBuilders.get("/usercard/edit-usercard"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("getLogin", "user"));
    }

}

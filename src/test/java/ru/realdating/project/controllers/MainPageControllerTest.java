package ru.realdating.project.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.realdating.project.TestConfig;
import ru.realdating.project.config.TestConfigWithOutHibernate;
import ru.realdating.project.service.UserSession;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = TestConfig.class)
class MainPageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void indexTest() throws Exception {
        UserSession userSession = new UserSession(1, "TestUser");

        mockMvc.perform(
                MockMvcRequestBuilders.get("/").sessionAttr("userSession", userSession)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.request().sessionAttribute("userSession", userSession));
    }

}
package ru.realdating.project.config;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import ru.realdating.project.App;

import javax.persistence.EntityManager;

@Configuration
@ComponentScan(basePackages = {"ru.realdating.project.config", "ru.realdating.project.controllers"},
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = {ProdJpaConfig.class, App.class, AppConfig.class}))
public class TestConfigWithOutHibernate {

    @MockBean
    public EntityManager entityManager;

}

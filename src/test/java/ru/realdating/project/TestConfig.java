package ru.realdating.project;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import ru.realdating.project.config.AppConfig;
import ru.realdating.project.config.ProdJpaConfig;
import ru.realdating.project.config.TestConfigWithOutHibernate;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@ComponentScan(
        basePackages = "ru.realdating.project",
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = {ProdJpaConfig.class, App.class, AppConfig.class, TestConfigWithOutHibernate.class}
        )
)
@EnableAutoConfiguration
public class TestConfig {

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory("TestPersistenceUnit");
    }

}

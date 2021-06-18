package ru.realdating.project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import ru.realdating.project.config.AppConfig;
import ru.realdating.project.config.ProdJpaConfig;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@ComponentScan(
        basePackages = "ru.realdating.project",
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = {ProdJpaConfig.class, App.class, AppConfig.class}
        )
)
public class TestConfig {

    @Bean
    public EntityManager manager (EntityManagerFactory factory) {
        return factory.createEntityManager();
    }

    @Bean
    public EntityManagerFactory factory() {
        return Persistence.createEntityManagerFactory("TestPersistenceUnit");
    }

}

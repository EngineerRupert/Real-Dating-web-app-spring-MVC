package ru.realdating.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@ComponentScan(basePackages = "ru.realdating.project")
public class AppConfig {

    @Bean
    public EntityManager manager (EntityManagerFactory factory) {
        return factory.createEntityManager();
    }

}

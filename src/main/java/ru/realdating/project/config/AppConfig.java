package ru.realdating.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Configuration
@ComponentScan(basePackages = "ru.realdating.project")
public class AppConfig {

//    @Bean
//    public EntityManager manager (EntityManagerFactory factory) {
//        return factory.createEntityManager();
//    }

}

package ru.realdating.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
public class ProdJpaConfig {

    @Bean
    public EntityManagerFactory factory() {
        return Persistence.createEntityManagerFactory("ProdPersistenceUnit");
    }

}

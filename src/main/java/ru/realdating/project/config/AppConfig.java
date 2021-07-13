package ru.realdating.project.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "ru.realdating.project")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "ru.realdating.project.dao")
public class AppConfig {

}

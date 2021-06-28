package ru.realdating.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.realdating.project.config.AppConfig;

@SpringBootApplication
@EnableTransactionManagement
public class App {

    public static void main(String[] args) {

        SpringApplication.run(AppConfig.class, args);

    }

}

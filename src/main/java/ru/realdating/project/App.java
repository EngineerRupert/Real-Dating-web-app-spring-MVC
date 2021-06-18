package ru.realdating.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.realdating.project.config.AppConfig;

@SpringBootApplication
public class App {

    public static void main(String[] args) {

        SpringApplication.run(AppConfig.class, args);

    }

}

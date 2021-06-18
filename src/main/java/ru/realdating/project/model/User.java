package ru.realdating.project.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table (name = "Users")
public class User {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, unique = true, length = 60)
    private String login;

    @Column (nullable = false, length = 100)
    private String password;

    @ManyToOne
    private Group group;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}

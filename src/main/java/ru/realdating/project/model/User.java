package ru.realdating.project.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;

@Component
@Entity
@Table (name = "Users")
public class User {

    @Id
    @GeneratedValue
    private int id;

    @Column(length = 60)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && login.equals(user.login) && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}

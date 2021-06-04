package ru.realdating.project.model;

import javax.persistence.*;

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
        super();
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}

package ru.realdating.project.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "Groups")
public class Group {

    @Id
    @GeneratedValue
    private int id;

    @Column (nullable = false)
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @OneToMany (mappedBy = "group")
    private List<User> users;

}

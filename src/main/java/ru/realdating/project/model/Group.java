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

    @OneToMany (mappedBy = "group")
    private List<User> listUsers;

}

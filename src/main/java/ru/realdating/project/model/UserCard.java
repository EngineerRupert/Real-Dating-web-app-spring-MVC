package ru.realdating.project.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
public class UserCard {
    // это карточка-анкета пользователя
    @Id
    @GeneratedValue
    private int id;

    // лайк, которые пользователи ставят карточке
    @Column
    @ColumnDefault(value = "0")
    private int like;

    @OneToOne
    private User user_id;

}

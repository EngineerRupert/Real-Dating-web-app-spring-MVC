package ru.realdating.project.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Blob;

@Entity
public class UserCard {

    // это карточка-анкета пользователя
    @Id
    @GeneratedValue
    private int id;

    // загрузка картинки для аватар (правда не умею с Blob работать)
    @Column
    private Blob avatar;

    @Column (columnDefinition="TEXT")
    private String aboutMe;

    @Column (columnDefinition="TEXT")
    private String interests;

    @Column (length = 10, nullable = false)
    private int age;

    @Column (length = 20, nullable = false)
    private String gender;

    @Column (length = 50)
    private String status;

    // лайк, которые пользователи ставят карточке
    @Column
    @ColumnDefault(value = "0")
    private int likeUserCard;

    @OneToOne
    private User userId;

}

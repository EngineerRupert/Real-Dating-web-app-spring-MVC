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

    @Column
    private int age;

    @Column (length = 20)
    private String gender;

    @Column (length = 50)
    private String status;

    // лайк, которые пользователи ставят карточке
    @Column
    @ColumnDefault(value = "0")
    private int likeUserCard;

    @OneToOne
    private User userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getLikeUserCard() {
        return likeUserCard;
    }

    public void setLikeUserCard(int likeUserCard) {
        this.likeUserCard = likeUserCard;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

}

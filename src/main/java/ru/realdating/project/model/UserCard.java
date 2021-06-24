package ru.realdating.project.model;

import org.hibernate.annotations.ColumnDefault;

import org.springframework.stereotype.Component;
import ru.realdating.project.dao.UserCardDao;
import ru.realdating.project.service.UserSession;

import javax.persistence.*;
import java.sql.Blob;

@Component
@Entity
public class UserCard {

    // это карточка-анкета пользователя

    @Id
    @GeneratedValue
    private int id;

    // загрузка картинки для аватар
    @Lob
    @Column(length = 100000)
    private byte[] foto;

    @Column (columnDefinition="TEXT")
    private String aboutMe;

    @Column (columnDefinition="TEXT")
    private String interests;

    @Column (length = 2)
    private String age;

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

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
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

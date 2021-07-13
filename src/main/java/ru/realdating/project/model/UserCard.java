package ru.realdating.project.model;

import org.hibernate.annotations.ColumnDefault;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Component
@Entity
public class UserCard {

    // это карточка-анкета пользователя

    @Id
    @GeneratedValue
    private int id;

    // загрузка картинки для аватар
    @Lob
    @Column(length = 10000)
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

    // лайк, которые пользователи ставят карточке пользователя
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCard userCard = (UserCard) o;
        return id == userCard.id && likeUserCard == userCard.likeUserCard && Arrays.equals(foto, userCard.foto) && Objects.equals(aboutMe, userCard.aboutMe) && Objects.equals(interests, userCard.interests) && Objects.equals(age, userCard.age) && Objects.equals(gender, userCard.gender) && Objects.equals(status, userCard.status) && Objects.equals(userId, userCard.userId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, aboutMe, interests, age, gender, status, likeUserCard, userId);
        result = 31 * result + Arrays.hashCode(foto);
        return result;
    }
}

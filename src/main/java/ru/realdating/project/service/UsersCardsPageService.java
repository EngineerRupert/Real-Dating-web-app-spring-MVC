package ru.realdating.project.service;

public class UsersCardsPageService {

    // класс для где берем нужные нам поля из 2 таблиц, для правильного показа список карточек пользователей.

    private String login;
    private String age;
    private String gender;
    private String status;
    private int id;

    public UsersCardsPageService() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

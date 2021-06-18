package ru.realdating.project.service;

public class UserSession {

    private int user_id;
    private String login;

    public UserSession() {

    }

    public UserSession(int user_id, String login) {
        this.user_id = user_id;
        this.login = login;
    }

    public int getId() {
        return user_id;
    }

    public void setId(int id) {
        this.user_id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean loggedIn() {
        return user_id > 0;
    }

    public void clearSession() {
        user_id = 0;
        login = null;
    }
}

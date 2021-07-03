package ru.realdating.project.service;

import java.util.Objects;

@Deprecated
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSession that = (UserSession) o;
        return user_id == that.user_id && Objects.equals(login, that.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, login);
    }
}

package ru.realdating.project.service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegistrationForm {

    @NotBlank
    @NotEmpty
    @Size(min = 4, max = 10)
    @Pattern(regexp = "[a-zA-Z0-9]{4,10}",
            message = "Login must consist of letters, numbers and from 4 to 10 chars")
    private String login;

    @NotBlank
    @NotEmpty
    @Size(min = 4, max = 10)
    @Pattern(regexp = "[a-zA-Z0-9]{4,10}",
            message = "Password must consist of letters, numbers and from 4 to 10 chars")
    private String password;

    @NotBlank
    @NotEmpty
    @Size(min = 4, max = 10)
    @Pattern(regexp = "[a-zA-Z0-9]{4,10}",
            message = "Password must consist of letters, numbers and from 4 to 10 chars")
    private String passwordConfirmation;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }
}

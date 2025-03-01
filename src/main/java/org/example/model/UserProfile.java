package org.example.model;

public class UserProfile {
    private String login;
    private String email;
    private String password;

    public UserProfile(String login, String password, String email) {
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
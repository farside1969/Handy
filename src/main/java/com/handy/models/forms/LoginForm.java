package com.handy.models.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginForm {

//login username field requirements with spring error message
    @NotNull
    @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters")
    private String username;

//login password field requirements with spring error message
    @NotNull
    @Size(min = 3, max =30, message = "Password must have 3-20 characters")
    private String password;

//default login constuctor
    public LoginForm() {}

//getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

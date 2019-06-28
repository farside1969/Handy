package com.handy.models.forms;

import javax.validation.constraints.NotNull;

//TODO add notes
public class RegisterForm extends LoginForm {

//login verify password requirements with spring error message
    @NotNull(message = "Passwords to not match")
    private String verifyPassword;

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
        checkPasswordForRegistration();
    }

//getter and setter
    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
        checkPasswordForRegistration();
    }

    private void checkPasswordForRegistration() {
        if (!getPassword().equals(verifyPassword)) {
            verifyPassword = null;
        }
    }
}

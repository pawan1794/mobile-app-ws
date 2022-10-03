package com.appsdeveloperblog.app.ws.model.request;

/**
 * Created by Pawan on 01/10/22
 */
public class LoginRequestModal {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

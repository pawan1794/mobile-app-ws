package com.appsdeveloperblog.app.ws.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Pawan on 06/10/22
 */
public class LoginRest {

    private String userId;
    private String username;
    private String token;
    private String expireBy;
    private Collection<String> roles = new ArrayList<>();

    public LoginRest() {}

    public LoginRest(String username, String userId) {
        this.username = username;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpireBy() {
        return expireBy;
    }

    public void setExpireBy(String expireBy) {
        this.expireBy = expireBy;
    }

    public Collection<String> getRoles() {
        return roles;
    }

    public void setRoles(Collection<String> roles) {
        this.roles = roles;
    }
}

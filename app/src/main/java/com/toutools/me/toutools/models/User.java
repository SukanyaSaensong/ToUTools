package com.toutools.me.toutools.models;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by User on 24/2/2561.
 */
@IgnoreExtraProperties
public class User {

    public String  username;
    public String  email;

    public User() {
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

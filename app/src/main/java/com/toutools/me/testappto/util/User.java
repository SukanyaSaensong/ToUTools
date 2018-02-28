package com.toutools.me.testappto.util;

/**
 * Created by User on 24/2/2561.
 */

public class User {
    private int id;
    private String firstname,lastname,username,email,phone_number,gender;

    public User(int id, String firstname, String lastname, String username, String email, String phone_number,String gender) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.phone_number = phone_number;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getGender() {
        return gender;
    }
}

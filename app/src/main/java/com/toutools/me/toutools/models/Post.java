package com.toutools.me.toutools.models;

/**
 * Created by User on 15/3/2561.
 */

public class Post {
    public String userId;
    public String imagePrice;
    public String imageName;
    public String imageURL;
    public String category;
    public String discrip;
    public String location;
    public String phoneNumber;
    private String key;

    public Post() {

    }

    public Post(String userId, String imagePrice, String imageName, String imageURL, String category, String discrip, String location, String phoneNumber) {
        this.userId = userId;
        this.imagePrice = imagePrice;
        this.imageName = imageName;
        this.imageURL = imageURL;
        this.category = category;
        this.discrip = discrip;
        this.location = location;
        this.phoneNumber = phoneNumber;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getImagePrice() {
        return imagePrice;
    }

    public void setImagePrice(String imagePrice) {
        this.imagePrice = imagePrice;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDiscrip() {
        return discrip;
    }

    public void setDiscrip(String discrip) {
        this.discrip = discrip;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getKey() {
        return key;
    }
}
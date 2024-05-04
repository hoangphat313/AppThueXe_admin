package com.example.adminapp.model;

public class Admin {
    private String email;
    private String password;
    private String phonenumber;
    private String username;
    private String image;

    public Admin(String email, String password, String phonenumber, String username, String image) {
        this.email = email;
        this.password = password;
        this.phonenumber = phonenumber;
        this.username = username;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
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

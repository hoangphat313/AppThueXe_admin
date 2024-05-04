package com.example.adminapp.model;

public class User {
    private String userID;
    private String username;
    private String numberphone;
    private String address;
    private String giaypheplaixe;
    private String dateofbirth;
    private String gioitinh;
    private String useravatar;

    public User(String userID, String username, String numberphone, String address, String giaypheplaixe, String dateofbirth, String gioitinh, String useravatar) {
        this.userID = userID;
        this.username = username;
        this.numberphone = numberphone;
        this.address = address;
        this.giaypheplaixe = giaypheplaixe;
        this.dateofbirth = dateofbirth;
        this.gioitinh = gioitinh;
        this.useravatar = useravatar;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNumberphone() {
        return numberphone;
    }

    public void setNumberphone(String numberphone) {
        this.numberphone = numberphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGiaypheplaixe() {
        return giaypheplaixe;
    }

    public void setGiaypheplaixe(String giaypheplaixe) {
        this.giaypheplaixe = giaypheplaixe;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getUseravatar() {
        return useravatar;
    }

    public void setUseravatar(String useravatar) {
        this.useravatar = useravatar;
    }
}

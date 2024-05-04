package com.example.adminapp.model;

import java.io.Serializable;

public class Car implements Serializable{
    private String carID;
    private String namecar;
    private double pricecar;
    private String typecar;
    private double seats;
    private String statuscar;
    private String descriptioncar;
    private String image1;
    private String image2;
    private String image3;

    public double getSeats() {
        return seats;
    }

    public void setSeats(double seats) {
        this.seats = seats;
    }

    public Car() {
    }
    public String getCarID() {
        return carID;
    }
    public void setCarID(String carID) {
        this.carID = carID;
    }
    public String getNamecar() {
        return namecar;
    }

    public void setNamecar(String namecar) {
        this.namecar = namecar;
    }


    public String getTypecar() {
        return typecar;
    }

    public void setTypecar(String typecar) {
        this.typecar = typecar;
    }

    public String getStatuscar() {
        return statuscar;
    }

    public void setStatuscar(String statuscar) {
        this.statuscar = statuscar;
    }

    public String getDescriptioncar() {
        return descriptioncar;
    }

    public double getPricecar() {
        return pricecar;
    }

    public void setPricecar(double pricecar) {
        this.pricecar = pricecar;
    }

    public void setDescriptioncar(String descriptioncar) {
        this.descriptioncar = descriptioncar;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public Car(String carID, String namecar, double pricecar, String typecar,double seats, String statuscar, String descriptioncar, String image1, String image2, String image3) {
        this.carID = carID;
        this.namecar = namecar;
        this.pricecar = pricecar;
        this.typecar = typecar;
        this.seats = seats;
        this.statuscar = statuscar;
        this.descriptioncar = descriptioncar;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
    }
}

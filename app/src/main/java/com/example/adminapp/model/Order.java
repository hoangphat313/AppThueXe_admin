package com.example.adminapp.model;

import java.io.Serializable;

public class Order implements Serializable {
    private String orderID;
    private String userID;
    private String orderDate;
    private String orderStatus;
    private String rentDate;
    private String returnDate;
    private String renterName;
    private String renterPhone;
    private String carID;
    private String carName;
    private double carPrice;
    private String carType;
    private String paymentMethod;
    private double totalprice;

    public Order(String orderID, String userID, String orderDate, String orderStatus, String rentDate, String returnDate, String renterName, String renterPhone, String carID, String carName, double carPrice, String carType, String paymentMethod, double totalprice) {
        this.orderID = orderID;
        this.userID = userID;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.renterName = renterName;
        this.renterPhone = renterPhone;
        this.carID = carID;
        this.carName = carName;
        this.carPrice = carPrice;
        this.carType = carType;
        this.paymentMethod = paymentMethod;
        this.totalprice = totalprice;
    }

    public Order(String userID, String orderDate, String orderStatus, String rentDate, String returnDate, String renterName, String renterPhone, String carID, String carName, double carPrice, String carType, String paymentMethod, double totalprice) {
        this.userID = userID;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.renterName = renterName;
        this.renterPhone = renterPhone;
        this.carID = carID;
        this.carName = carName;
        this.carPrice = carPrice;
        this.carType = carType;
        this.paymentMethod = paymentMethod;
        this.totalprice = totalprice;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getRentDate() {
        return rentDate;
    }

    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getRenterName() {
        return renterName;
    }

    public void setRenterName(String renterName) {
        this.renterName = renterName;
    }

    public String getRenterPhone() {
        return renterPhone;
    }

    public void setRenterPhone(String renterPhone) {
        this.renterPhone = renterPhone;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public double getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(double carPrice) {
        this.carPrice = carPrice;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }
}

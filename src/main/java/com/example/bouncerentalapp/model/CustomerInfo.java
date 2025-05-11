package com.example.bouncerentalapp.model;

import java.time.LocalDate;

public class CustomerInfo
{
    private int orderID;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDate startDate;

    public CustomerInfo(int orderID, String street, String city, String state, String zipCode,
                        String firstName, String lastName, String phoneNumber, LocalDate startDate) {
        this.orderID = orderID;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.startDate = startDate;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}

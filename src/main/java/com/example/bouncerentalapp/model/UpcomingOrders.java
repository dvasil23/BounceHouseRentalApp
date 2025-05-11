package com.example.bouncerentalapp.model;

import java.time.LocalDate;

public class UpcomingOrders
{
    private LocalDate orderDate;
    private LocalDate startDate;
    private int productID;
    private String productName;
    private int quantityTaken;
    private float orderTotal;

    public UpcomingOrders(LocalDate orderDate, LocalDate startDate, int productID, String productName, int quantityTaken, float orderTotal){
        this.orderDate = orderDate;
        this.startDate = startDate;
        this.productID = productID;
        this.productName = productName;
        this.quantityTaken = quantityTaken;
        this.orderTotal = orderTotal;

    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantityTaken() {
        return quantityTaken;
    }

    public void setQuantityTaken(int quantityTaken) {
        this.quantityTaken = quantityTaken;
    }

    public float getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(float orderTotal) {
        this.orderTotal = orderTotal;
    }
}

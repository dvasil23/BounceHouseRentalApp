package com.example.bouncerentalapp.model;

import java.time.LocalDate;

public class OrderSummary
{
    private LocalDate orderDate;
    private int productID;
    private String productName;
    private int quantityTaken;
    private float orderTotal;

    public OrderSummary(LocalDate orderDate, int productID, String productName, int quantityTaken, float orderTotal) {
        this.orderDate = orderDate;
        this.productID = productID;
        this.productName = productName;
        this.quantityTaken = quantityTaken;
        this.orderTotal = orderTotal;
    }

    // Getters
    public LocalDate getOrderDate() {
        return orderDate;
    }

    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantityTaken() {
        return quantityTaken;
    }

    public float getOrderTotal() {
        return orderTotal;
    }

    // Setters
    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setQuantityTaken(int quantityTaken) {
        this.quantityTaken = quantityTaken;
    }

    public void setOrderTotal(float orderTotal) {
        this.orderTotal = orderTotal;
    }

}

package com.example.bouncerentalapp.model;

import java.time.LocalDate;

public class OrderAudit
{
    private int orderID;
    private int productID;
    private float orderTotal;
    private String actionType;
    private LocalDate actionDate;

    public OrderAudit(int orderID,int productID,float orderTotal, String actionType, LocalDate actionDate){
        this.orderID = orderID;
        this.productID = productID;
        this.orderTotal = orderTotal;
        this.actionType = actionType;
        this.actionDate = actionDate;
    }
    // Getters and Setters
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public float getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(float orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public LocalDate getActionDate() {
        return actionDate;
    }

    public void setActionDate(LocalDate actionDate) {
        this.actionDate = actionDate;
    }
}

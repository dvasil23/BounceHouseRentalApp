package com.example.bouncerentalapp.model;

public class OrderProduct
{
    private int orderProductID;
    private int productID;
    private int orderID;
    private int quantityTaken;


    public OrderProduct(int orderProductID, int productID, int orderID, int quantityTaken) {
        this.orderProductID = orderProductID;
        this.productID = productID;
        this.orderID = orderID;
        this.quantityTaken = quantityTaken;
    }

    // Getters and Setters
    public int getOrderProductID() {
        return orderProductID;
    }

    public void setOrderProductID(int orderProductID) {
        this.orderProductID = orderProductID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getQuantityTaken() {
        return quantityTaken;
    }

    public void setQuantityTaken(int quantityTaken) {
        this.quantityTaken = quantityTaken;
    }
}

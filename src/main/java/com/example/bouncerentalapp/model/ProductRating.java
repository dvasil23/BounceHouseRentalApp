package com.example.bouncerentalapp.model;

public class ProductRating
{
    private float averageRating;
    private int productID;
    private String productName;

    // Constructor
    public ProductRating(float averageRating, int productID, String productName) {
        this.averageRating = averageRating;
        this.productID = productID;
        this.productName = productName;
    }

    // Getters
    public float getAverageRating() {
        return averageRating;
    }

    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    // Setters
    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}

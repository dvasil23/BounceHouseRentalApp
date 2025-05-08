package com.example.bouncerentalapp.model;

public class ProductRating
{
    private float averageRating;
    private int productID;
    private String productName;

    private String reviewText;

    // Constructor
    public ProductRating(String reviewText, Float averageRating, int productID, String productName) {
        this.setReviewText(reviewText);
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

    public String getReviewText()
    {
        return reviewText;
    }

    public void setReviewText(String reviewText)
    {
        this.reviewText = reviewText;
    }
}

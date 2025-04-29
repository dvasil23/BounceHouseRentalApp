package com.example.bouncerentalapp.model;

public class RentalReview
{
    private int reviewID;
    private int orderID;
    private int customerID;
    private String reviewText;
    private int rating;

    private int productID;

    public RentalReview(int reviewID, int orderID, int customerID, String reviewText, int rating, int productID){
        this.setReviewID(reviewID);
        this.setOrderID(orderID);
        this.setCustomerID(customerID);
        this.setReviewText(reviewText);
        this.setRating(rating);
        this.setProductID(productID);
    }

    public String getReviewText()
    {
        return reviewText;
    }

    public void setReviewText(String reviewText)
    {
        this.reviewText = reviewText;
    }

    public int getRating()
    {
        return rating;
    }

    public void setRating(int rating)
    {
        this.rating = rating;
    }

    public int getReviewID()
    {
        return reviewID;
    }

    public void setReviewID(int reviewID)
    {
        this.reviewID = reviewID;
    }

    public int getOrderID()
    {
        return orderID;
    }

    public void setOrderID(int orderID)
    {
        this.orderID = orderID;
    }

    public int getCustomerID()
    {
        return customerID;
    }

    public void setCustomerID(int customerID)
    {
        this.customerID = customerID;
    }

    public int getProductID()
    {
        return productID;
    }

    public void setProductID(int productID)
    {
        this.productID = productID;
    }
}

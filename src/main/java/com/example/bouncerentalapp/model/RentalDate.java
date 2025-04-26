package com.example.bouncerentalapp.model;

public class RentalDate
{
    private int rentalDateID;
    private int productID;
    private String startDate;
    private String endDate;

    public RentalDate(int rentalDateID, int productID, String startDate, String endDate){
        this.setRentalDateID(rentalDateID);
        this.setProductID(productID);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
    }

    public String getStartDate()
    {
        return startDate;
    }

    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }

    public int getRentalDateID()
    {
        return rentalDateID;
    }

    public void setRentalDateID(int rentalDateID)
    {
        this.rentalDateID = rentalDateID;
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

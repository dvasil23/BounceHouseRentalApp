package com.example.bouncerentalapp.model;

public class RentalDate
{
    private int rentalDateID;
    private int productID;
    private String startDate;
    private String endDate;

    private int orderProductID;

    private int orderID;

    public RentalDate(int rentalDateID, int productID, String startDate, String endDate, int orderProductID, int orderID){
        this.setRentalDateID(rentalDateID);
        this.setProductID(productID);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
        this.setOrderProductID(orderProductID);
        this.setOrderID(orderID);
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

    public int getOrderProductID()
    {
        return orderProductID;
    }

    public void setOrderProductID(int orderProductID)
    {
        this.orderProductID = orderProductID;
    }

    public int getOrderID()
    {
        return orderID;
    }

    public void setOrderID(int orderID)
    {
        this.orderID = orderID;
    }
}

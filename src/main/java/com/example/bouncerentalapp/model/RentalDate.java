package com.example.bouncerentalapp.model;

import java.time.LocalDate;

public class RentalDate
{
    private int rentalDateID;
    private int productID;
    private LocalDate startDate;
    private LocalDate endDate;

    private int orderProductID;

    private int orderID;

    public RentalDate(int rentalDateID, int productID, LocalDate startDate, LocalDate endDate, int orderProductID, int orderID){
        this.setRentalDateID(rentalDateID);
        this.setProductID(productID);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
        this.setOrderProductID(orderProductID);
        this.setOrderID(orderID);
    }

    public LocalDate getStartDate()
    {
        return startDate;
    }

    public void setStartDate(LocalDate startDate)
    {
        this.startDate = startDate;
    }

    public LocalDate getEndDate()
    {
        return endDate;
    }

    public void setEndDate(LocalDate endDate)
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

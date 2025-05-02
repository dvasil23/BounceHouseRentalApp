package com.example.bouncerentalapp.model;

import java.time.LocalDate;

public class Order
{
    private int orderID;
    private int productID;
    private int customerID;

    private LocalDate orderDate;
    private int rentalDaysAmount;
    private float orderTotal;


    public Order(int orderID, int productID, int customerID, LocalDate orderDate, int rentalDaysAmount, float orderTotal){
        this.setOrderID(orderID);
        this.setProductID(productID);
        this.setCustomerID(customerID);
        this.setOrderDate(orderDate);
        this.setRentalDaysAmount(rentalDaysAmount);
        this.setOrderTotal(orderTotal);
    }

    public LocalDate getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate)
    {
        this.orderDate = orderDate;
    }

    public int getRentalDaysAmount()
    {
        return rentalDaysAmount;
    }

    public void setRentalDaysAmount(int rentalDaysAmount)
    {
        this.rentalDaysAmount = rentalDaysAmount;
    }

    public float getOrderTotal()
    {
        return orderTotal;
    }

    public void setOrderTotal(float orderTotal)
    {
        this.orderTotal = orderTotal;
    }

    public int getOrderID()
    {
        return orderID;
    }

    public void setOrderID(int orderID)
    {
        this.orderID = orderID;
    }

    public int getProductID()
    {
        return productID;
    }

    public void setProductID(int productID)
    {
        this.productID = productID;
    }

    public int getCustomerID()
    {
        return customerID;
    }

    public void setCustomerID(int customerID)
    {
        this.customerID = customerID;
    }
}

package com.example.bouncerentalapp.model;

public class Order
{
    private int orderID;
    private int productID;
    private int customerID;

    private String orderDate;
    private int rentalDaysAmount;
    private float totalPrice;


    public Order(int orderID, int productID, int customerID, String orderDate, int rentalDaysAmount, float totalPrice){
        this.setOrderID(orderID);
        this.setProductID(productID);
        this.setCustomerID(customerID);
        this.setOrderDate(orderDate);
        this.setRentalDaysAmount(rentalDaysAmount);
        this.setTotalPrice(totalPrice);
    }

    public String getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate(String orderDate)
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

    public float getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice)
    {
        this.totalPrice = totalPrice;
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

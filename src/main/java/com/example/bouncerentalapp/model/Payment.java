package com.example.bouncerentalapp.model;

public class Payment
{
    private int paymentID;
    private int orderID;
    private String cardType;
    private String cardNumber;
    private String cardExpires;
    private String billingAddress;

    public Payment(int paymentID, int orderID, String cardType, String cardNumber,
                   String cardExpires, String billingAddress){
        this.setPaymentID(paymentID);
        this.setOrderID(orderID);
        this.setCardType(cardType);
        this.setCardNumber(cardNumber);
        this.setCardExpires(cardExpires);
        this.setBillingAddress(billingAddress);


    }

    public String getCardType()
    {
        return cardType;
    }

    public void setCardType(String cardType)
    {
        this.cardType = cardType;
    }

    public String getCardNumber()
    {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber)
    {
        this.cardNumber = cardNumber;
    }

    public String getCardExpires()
    {
        return cardExpires;
    }

    public void setCardExpires(String cardExpires)
    {
        this.cardExpires = cardExpires;
    }

    public String getBillingAddress()
    {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress)
    {
        this.billingAddress = billingAddress;
    }

    public int getPaymentID()
    {
        return paymentID;
    }

    public void setPaymentID(int paymentID)
    {
        this.paymentID = paymentID;
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

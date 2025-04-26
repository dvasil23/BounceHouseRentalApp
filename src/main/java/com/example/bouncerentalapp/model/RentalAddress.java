package com.example.bouncerentalapp.model;

public class RentalAddress
{
    private int addrID;
    private int customerID;
    private String city;
    private String state;
    private String zipCode;


    public RentalAddress(int addrID, int customerID, String city, String state, String zipCode){
        this.setAddrID(addrID);
        this.setCustomerID(customerID);
        this.setCity(city);
        this.setState(state);
        this.setZipCode(zipCode);

    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getZipCode()
    {
        return zipCode;
    }

    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }

    public int getAddrID()
    {
        return addrID;
    }

    public void setAddrID(int addrID)
    {
        this.addrID = addrID;
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

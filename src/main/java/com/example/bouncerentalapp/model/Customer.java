package com.example.bouncerentalapp.model;

public class Customer
{
    private int customer_id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String passsword;

    public Customer(int customer_id,String firstName, String lastName,
                    String phoneNumber, String password){
        this.setCustomer_id(customer_id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setPhoneNumber(phoneNumber);
        this.setPasssword(password);

    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getPasssword()
    {
        return passsword;
    }

    public void setPasssword(String passsword)
    {
        this.passsword = passsword;
    }

    public int getCustomer_id()
    {
        return customer_id;
    }

    public void setCustomer_id(int customer_id)
    {
        this.customer_id = customer_id;
    }
}

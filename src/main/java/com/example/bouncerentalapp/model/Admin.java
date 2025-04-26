package com.example.bouncerentalapp.model;

public class Admin
{
    private int adminID;
    private String firstName;
    private String lastName;
    private String password;

    public Admin(int adminID,String firstName, String lastName, String password){
        this.setAdminID(adminID);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setPassword(password);

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

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public int getAdminID()
    {
        return adminID;
    }

    public void setAdminID(int adminID)
    {
        this.adminID = adminID;
    }
}


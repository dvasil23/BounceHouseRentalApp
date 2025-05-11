package com.example.bouncerentalapp.model;

public class Admin
{
    private int adminID;
    private String username;
    private String password;
    public Admin(int adminID, String username, String password){
        this.setAdminID(adminID);
        this.setUsername(username);
        this.setPassword(password);
    }

    public int getAdminID()
    {
        return adminID;
    }

    public void setAdminID(int adminID)
    {
        this.adminID = adminID;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}

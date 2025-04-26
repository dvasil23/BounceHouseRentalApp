package com.example.bouncerentalapp.model;

public class ProductInventory
{
    //store rental product objects in a list
    private int inventoryID;
    private int productID;

    private boolean productAvailability = true;

    private String status;

    public ProductInventory(){
    //dont make DAO version yet
    }

    public boolean isProductAvailability()
    {
        return productAvailability;
    }

    public void setProductAvailability(boolean productAvailability)
    {
        this.productAvailability = productAvailability;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public int getInventoryID()
    {
        return inventoryID;
    }

    public void setInventoryID(int inventoryID)
    {
        this.inventoryID = inventoryID;
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

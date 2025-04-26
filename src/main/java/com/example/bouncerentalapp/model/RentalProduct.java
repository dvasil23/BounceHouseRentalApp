package com.example.bouncerentalapp.model;

public class RentalProduct
{
    private int productID;
    private int categoryID;
    private String dimensions;
    private int price;

    public RentalProduct(int productID, int categoryID,String dimensions, int price){
        this.setProductID(productID);
        this.setCategoryID(categoryID);
        this.setDimensions(dimensions);
        this.setPrice(price);
    }

    public String getDimensions()
    {
        return dimensions;
    }

    public void setDimensions(String dimensions)
    {
        this.dimensions = dimensions;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public int getProductID()
    {
        return productID;
    }

    public void setProductID(int productID)
    {
        this.productID = productID;
    }

    public int getCategoryID()
    {
        return categoryID;
    }

    public void setCategoryID(int categoryID)
    {
        this.categoryID = categoryID;
    }
}

package com.example.bouncerentalapp.model;


public class ProductImage
{
    private int productID;
    String imagePath;

    public ProductImage(int productID, String imagePath){
        this.setProductID(productID);
        this.imagePath = imagePath;
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

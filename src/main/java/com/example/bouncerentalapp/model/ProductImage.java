package com.example.bouncerentalapp.model;


public class ProductImage
{
    private int productID;
    private String imagePath;

    public ProductImage(int productID, String imagePath){
        this.setProductID(productID);
        this.setImagePath(imagePath);
    }


    public int getProductID()
    {
        return productID;
    }

    public void setProductID(int productID)
    {
        this.productID = productID;
    }

    public String getImagePath()
    {
        return imagePath;
    }

    public void setImagePath(String imagePath)
    {
        this.imagePath = imagePath;
    }
}

package com.example.bouncerentalapp.model;

public class RentalProduct
{
    private int productID;
    private int categoryID;

    private String productName;
    private String dimensions;
    private float price;

    private int quantity;

    private int quantitySelected = 1;

    private String imageURL;

    public RentalProduct(int productID, int categoryID,String productName, String dimensions, float price, int quantity){
        this.setProductID(productID);
        this.setCategoryID(categoryID);
        this.setProductName(productName);
        this.setDimensions(dimensions);
        this.setPrice(price);
        this.setQuantity(quantity);
    }

    public RentalProduct(int productID, int categoryID,String productName, String dimensions, float price, int quantity, String imageURL){
        this(productID, categoryID, productName, dimensions, price, quantity);
        this.imageURL = imageURL;
    }



    public String getDimensions()
    {
        return dimensions;
    }

    public void setDimensions(String dimensions)
    {
        this.dimensions = dimensions;
    }

    public float getPrice()
    {
        return price;
    }

    public void setPrice(float price)
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

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public int getQuantitySelected()
    {
        return quantitySelected;
    }

    public void setQuantitySelected(int quantitySelected)
    {
        this.quantitySelected = quantitySelected;
    }

    public String getImageURL()
    {
        return imageURL;
    }

    public void setImageURL(String imageURL)
    {
        this.imageURL = imageURL;
    }
}

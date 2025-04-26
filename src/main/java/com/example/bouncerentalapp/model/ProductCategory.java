package com.example.bouncerentalapp.model;

public class ProductCategory
{
    private int categoryID;
    private String categoryType;

    public ProductCategory(int categoryID, String categoryType){
        this.setCategoryID(categoryID);
        this.setCategoryType(categoryType);
    }

    public String getCategoryType()
    {
        return categoryType;
    }

    public void setCategoryType(String categoryType)
    {
        this.categoryType = categoryType;
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

package com.example.bouncerentalapp.model;

public class ProductName
{
    private String productName;
    private String categoryType;

    public ProductName(String productName, String categoryType) {
        this.productName = productName;
        this.categoryType = categoryType;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

}

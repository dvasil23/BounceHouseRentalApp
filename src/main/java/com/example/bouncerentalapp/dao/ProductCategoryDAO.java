package com.example.bouncerentalapp.dao;
import com.example.bouncerentalapp.MyJDBC;
import com.example.bouncerentalapp.model.ProductCategory;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDAO
{
    public static List<ProductCategory> getProductCategories() {
        List<ProductCategory> productImages = new ArrayList<>();

        try (Connection conn = MyJDBC.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCT_CATEGORIES")) {

            while (rs.next()) {
                productImages.add(new ProductCategory(
                        rs.getInt("category_id"),
                        rs.getString("category_type")

                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productImages;
}
}


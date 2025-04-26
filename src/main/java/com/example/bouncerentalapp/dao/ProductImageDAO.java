package com.example.bouncerentalapp.dao;
import com.example.bouncerentalapp.MyJDBC;
import com.example.bouncerentalapp.model.ProductImage;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductImageDAO
{
    public static List<ProductImage> getProductImages() {
        List<ProductImage> productImages = new ArrayList<>();

        try (Connection conn = MyJDBC.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCT_IMAGES")) {

            while (rs.next()) {
                productImages.add(new ProductImage(
                        rs.getInt("product_id"),
                        rs.getString("image")

                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productImages;
    }
}

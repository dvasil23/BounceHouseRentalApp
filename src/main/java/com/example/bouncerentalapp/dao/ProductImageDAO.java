package com.example.bouncerentalapp.dao;
import com.example.bouncerentalapp.MyJDBC;
import com.example.bouncerentalapp.model.ProductImage;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductImageDAO
{
    public static String getImageUrlByProductId(int productId) {
        String imageUrl = null;

        String sql = "SELECT image_url FROM product_images WHERE product_id = ?";

        try (Connection conn = MyJDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                imageUrl = rs.getString("image_url");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return imageUrl;
    }
}

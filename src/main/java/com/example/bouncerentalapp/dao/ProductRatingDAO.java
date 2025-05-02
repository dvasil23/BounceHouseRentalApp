package com.example.bouncerentalapp.dao;

import com.example.bouncerentalapp.MyJDBC;
import com.example.bouncerentalapp.model.ProductRating;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//VIEW
public class ProductRatingDAO
{
    public static List<ProductRating> getAllRatings() {
        List<ProductRating> ratings = new ArrayList<>();

        String sql = "SELECT * FROM product_ratings";

        try (Connection conn = MyJDBC.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                float avgRating = rs.getFloat("average_rating");
                int productId = rs.getInt("product_id");
                String productName = rs.getString("product_name");

                // Optionally check for nulls or 0 rating
                ratings.add(new ProductRating(avgRating, productId, productName));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ratings;
    }
}

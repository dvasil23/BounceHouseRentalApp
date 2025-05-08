package com.example.bouncerentalapp.dao;

import com.example.bouncerentalapp.MyJDBC;
import com.example.bouncerentalapp.model.ProductRating;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//VIEW
public class ProductRatingDAO
{
    public static List<ProductRating> getAllRatings() {
        List<ProductRating> ratings = new ArrayList<>();

        String sql = "SELECT * FROM product_ratings";

        try (Connection conn = MyJDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);

             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ratings.add(new ProductRating(
                        rs.getString("review_text"),
                        rs.getFloat("average_rating"),
                        rs.getInt("product_id"),
                        rs.getString("product_name")

                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ratings;
    }
}

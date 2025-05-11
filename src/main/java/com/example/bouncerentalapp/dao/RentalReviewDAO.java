package com.example.bouncerentalapp.dao;
import com.example.bouncerentalapp.MyJDBC;
import com.example.bouncerentalapp.model.RentalReview;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RentalReviewDAO
{
    public static List<RentalReview> getRentalReviews() {
        List<RentalReview> rentalReviews = new ArrayList<>();

        String sql = "SELECT * FROM rental_reviews";

        try (Connection conn = MyJDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                rentalReviews.add(new RentalReview(
                        rs.getInt("review_id"),
                        rs.getInt("order_id"),
                        rs.getInt("customer_id"),
                        rs.getString("text"),
                        rs.getInt("rating"),
                        rs.getInt("product_id")

                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rentalReviews;
    }

    public static void insertReview(int orderId, int customerId, String reviewText, int rating, int productId){

        String sql = "INSERT INTO rental_reviews (order_id, customer_id, product_id, review_text, rating) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = MyJDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, orderId);
            stmt.setInt(2, customerId);
            stmt.setInt(3, productId);
            stmt.setString(4, reviewText);
            stmt.setInt(5,rating);

            stmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}

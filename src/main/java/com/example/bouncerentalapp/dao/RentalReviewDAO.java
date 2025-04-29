package com.example.bouncerentalapp.dao;
import com.example.bouncerentalapp.MyJDBC;
import com.example.bouncerentalapp.model.RentalReview;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RentalReviewDAO
{
    public static List<RentalReview> getRentalReviews() {
        List<RentalReview> rentalReviews = new ArrayList<>();

        try (Connection conn = MyJDBC.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM RENTAL_REVIEWS")) {

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
}

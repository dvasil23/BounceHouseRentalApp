package com.example.bouncerentalapp.dao;
import com.example.bouncerentalapp.MyJDBC;
import com.example.bouncerentalapp.model.RentalDate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RentalDateDAO
{
    public static List<RentalDate> getRentalDates() {
        List<RentalDate> rentalDates = new ArrayList<>();

        try (Connection conn = MyJDBC.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM RENTAL_DATES")) {

            while (rs.next()) {
                rentalDates.add(new RentalDate(
                        rs.getInt("rent_date_id"),
                        rs.getInt("product_id"),
                        rs.getString("start_date"),
                        rs.getString("end_date"),
                        rs.getInt("order_product_id"),
                        rs.getInt("order_id")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rentalDates;
    }
}

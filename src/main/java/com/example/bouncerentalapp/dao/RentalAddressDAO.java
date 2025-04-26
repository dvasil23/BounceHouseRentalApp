package com.example.bouncerentalapp.dao;
import com.example.bouncerentalapp.MyJDBC;
import com.example.bouncerentalapp.model.RentalAddress;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RentalAddressDAO
{
    public static List<RentalAddress> getRentalAddresses() {
        List<RentalAddress> rentalAddresses = new ArrayList<>();

        try (Connection conn = MyJDBC.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM RENTAL_ADDRESSES")) {

            while (rs.next()) {
                rentalAddresses.add(new RentalAddress(
                        rs.getInt("addr_id"),
                        rs.getInt("customer_id"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getString("zip_code")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rentalAddresses;
    }
}

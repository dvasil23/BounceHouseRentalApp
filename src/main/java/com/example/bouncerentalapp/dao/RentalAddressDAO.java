package com.example.bouncerentalapp.dao;
import com.example.bouncerentalapp.MyJDBC;
import com.example.bouncerentalapp.model.RentalAddress;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RentalAddressDAO
{
    public static boolean insertAddress(RentalAddress address, String street)
    {

        String sql =  "INSERT INTO rental_addresses (customer_id, city, state, zip_code, street) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = MyJDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, address.getCustomerID());
            stmt.setString(2, address.getCity());
            stmt.setString(3, address.getState());
            stmt.setString(4, address.getZipCode());
            stmt.setString(5, street);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
}

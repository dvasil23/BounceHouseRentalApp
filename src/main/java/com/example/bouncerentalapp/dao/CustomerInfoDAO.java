package com.example.bouncerentalapp.dao;

import com.example.bouncerentalapp.MyJDBC;
import com.example.bouncerentalapp.model.CustomerInfo;
import com.example.bouncerentalapp.model.UpcomingOrders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerInfoDAO
{
    public static List<CustomerInfo> getCustomerInfo(){
        List<CustomerInfo> customers = new ArrayList<>();

        String sql = "SELECT * FROM customer_info";

        try (Connection conn = MyJDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);

             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                customers.add(new CustomerInfo(
                        rs.getInt("order_id"),
                        rs.getString("street"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getString("zip_code"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("phone_number"),
                        rs.getDate("start_date").toLocalDate()

                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }
}

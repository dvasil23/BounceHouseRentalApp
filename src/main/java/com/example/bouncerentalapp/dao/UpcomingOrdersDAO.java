package com.example.bouncerentalapp.dao;

import com.example.bouncerentalapp.MyJDBC;
import com.example.bouncerentalapp.model.UpcomingOrders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UpcomingOrdersDAO
{
    public static List<UpcomingOrders> getAllUpcomingOrders(){
        List<UpcomingOrders> orders = new ArrayList<>();

        String sql = "SELECT * FROM upcoming_orders";

        try (Connection conn = MyJDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);

             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                orders.add(new UpcomingOrders(
                        rs.getDate("order_date").toLocalDate(),
                        rs.getDate("start_date").toLocalDate(),
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getInt("quantity_taken"),
                        rs.getFloat("order_total")

                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }
}

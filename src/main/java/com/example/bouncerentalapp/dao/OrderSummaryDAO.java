package com.example.bouncerentalapp.dao;

import com.example.bouncerentalapp.MyJDBC;
import com.example.bouncerentalapp.model.OrderSummary;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//VIEW
public class OrderSummaryDAO
{
    public static List<OrderSummary> getAllSummaries() {
        List<OrderSummary> summaries = new ArrayList<>();

        String sql = "SELECT * FROM order_summary";

        try (Connection conn = MyJDBC.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                summaries.add(new OrderSummary(
                        rs.getDate("order_date").toLocalDate(),
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getInt("quantity_taken"),
                        rs.getFloat("order_total")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return summaries;
    }
}

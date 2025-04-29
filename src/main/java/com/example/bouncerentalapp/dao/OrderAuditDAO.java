package com.example.bouncerentalapp.dao;

import com.example.bouncerentalapp.MyJDBC;
import com.example.bouncerentalapp.model.OrderAudit;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderAuditDAO
{
    public static
    List<OrderAudit> getOrderAudits() {
        List<OrderAudit> orderAudits = new ArrayList<>();

        try (Connection conn = MyJDBC.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM ORDER_AUDIT")) {

            while (rs.next()) {
                orderAudits.add(new OrderAudit(
                        rs.getInt("order_id"),
                        rs.getInt("product_id"),
                        rs.getFloat("order_total"),
                        rs.getString("action_type"),
                        rs.getString("action_date")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderAudits;
    }
}

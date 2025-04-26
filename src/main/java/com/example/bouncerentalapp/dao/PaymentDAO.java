package com.example.bouncerentalapp.dao;
import com.example.bouncerentalapp.MyJDBC;
import com.example.bouncerentalapp.model.Payment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO
{
    public static List<Payment> getPayments() {
        List<Payment> payments = new ArrayList<>();

        try (Connection conn = MyJDBC.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM PAYMENTS")) {

            while (rs.next()) {
                payments.add(new Payment(
                        rs.getInt("payment_id"),
                        rs.getInt("order_id"),
                        rs.getString("card_type"),
                        rs.getString("card_number"),
                        rs.getString("card_expires"),
                        rs.getString("billing_address")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payments;
    }
}

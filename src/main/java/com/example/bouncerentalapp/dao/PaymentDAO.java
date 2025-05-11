package com.example.bouncerentalapp.dao;
import com.example.bouncerentalapp.MyJDBC;
import com.example.bouncerentalapp.model.Payment;

import java.sql.*;
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

    public static void insertPayment(int orderID, String cardType, String cardNumber, String cardExpires, String billingAddress){
        String sql = "INSERT INTO payments (order_id, card_type, card_number, card_expires, billing_address) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = MyJDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, orderID);
            stmt.setString(2,cardType);
            stmt.setString(3,cardNumber);
            stmt.setString(4,cardExpires);
            stmt.setString(5,billingAddress);

            stmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}

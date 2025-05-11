package com.example.bouncerentalapp.dao;
import com.example.bouncerentalapp.MyJDBC;
import com.example.bouncerentalapp.model.Customer;
import com.example.bouncerentalapp.model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CustomerDAO
{
    public static int insertCustomerAndReturnId(Customer customer)
    {
        int generatedId = -1;

        String sql = "INSERT INTO customers (first_name, last_name, phone_number) VALUES (?, ?, ?)";

        try (Connection conn = MyJDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getLastName());
            stmt.setString(3, customer.getPhoneNumber());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedId = rs.getInt(1);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return generatedId;
    }

    public static Customer getCustomerById(int customerId){
        Customer customer = null;

        String sql = "SELECT * FROM customers WHERE customer_id = ?";

        try(Connection conn = MyJDBC.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, customerId);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                customer = new Customer(
                        rs.getInt("customer_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("phone_number")
                );
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return customer;
    }
}



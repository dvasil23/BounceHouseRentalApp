package com.example.bouncerentalapp.dao;
import com.example.bouncerentalapp.MyJDBC;
import com.example.bouncerentalapp.model.Order;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class OrderDAO
{
    public static List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();

        try (Connection conn = MyJDBC.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM ORDERS")) {

            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("order_id"),
                        rs.getInt("product_id"),
                        rs.getInt("customer_id"),
                        rs.getDate("order_date").toLocalDate(),
                        rs.getInt("rental_days_amount"),
                        rs.getFloat("total_price")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    public static void createOrder(int customerID, int productID, LocalDate orderDate,
                                   LocalDate startDate, LocalDate endDate,
                                   int amountChosen){
        String sql = "{CALL createOrder(?, ?, ?, ? ,?)}";

        try (Connection conn = MyJDBC.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, customerID);
            stmt.setInt(2, productID);
            stmt.setDate(3, java.sql.Date.valueOf(orderDate));
            stmt.setDate(4, java.sql.Date.valueOf(startDate));
            stmt.setDate(5, java.sql.Date.valueOf(endDate));
            stmt.setInt(6,amountChosen);

            stmt.execute();
            System.out.println("Order created successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void cancelOrder(int orderID){
        String sql = "{CALL cancelOrder(?)}";

        try(Connection conn = MyJDBC.getConnection();
            CallableStatement stmt  = conn.prepareCall(sql)){
                stmt.setInt(1,orderID);

            stmt.execute();
            System.out.println("Order canceled successfully.");

        } catch (SQLException e){
                e.printStackTrace();
        }
    }


    }



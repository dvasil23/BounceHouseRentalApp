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

    public static Order getOrderByOrderId(int orderId){
        Order order = null;

        String sql = "SELECT * FROM orders WHERE order_id = ?";

        try(Connection conn = MyJDBC.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, orderId);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                order = new Order(
                        rs.getInt("order_id"),
                        rs.getInt("product_id"),
                        rs.getInt("customer_id"),
                        rs.getDate("order_date").toLocalDate(),
                        rs.getInt("rental_days_amount"),
                        rs.getFloat("order_total")

                );
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return order;
    }

    public static int createOrderAndReturnOrderId(int customerID, int productID, LocalDate startDate, LocalDate endDate,
                                   int amountChosen){
        int generatedId = -1;

        String sql = "{CALL createOrder(?, ?, ?, ? ,?, ?)}";
/*
        try (Connection conn = MyJDBC.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, customerID);
            stmt.setInt(2, productID);
            stmt.setDate(3, java.sql.Date.valueOf(startDate));
            stmt.setDate(4, java.sql.Date.valueOf(endDate));
            stmt.setInt(5,amountChosen);

            stmt.execute();
            System.out.println("Order created successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
 */
        try(Connection conn = MyJDBC.getConnection();
            CallableStatement stmt = conn.prepareCall(sql)){
            stmt.setInt(1, customerID);
            stmt.setInt(2, productID);
            stmt.setDate(3, java.sql.Date.valueOf(startDate));
            stmt.setDate(4, java.sql.Date.valueOf(endDate));
            stmt.setInt(5,amountChosen);
            stmt.registerOutParameter(6,Types.INTEGER);

            stmt.execute();

            generatedId = stmt.getInt(6);
            System.out.println("Order created successfully.");

        }
        catch(Exception e){
            e.printStackTrace();
        }

        return generatedId;
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



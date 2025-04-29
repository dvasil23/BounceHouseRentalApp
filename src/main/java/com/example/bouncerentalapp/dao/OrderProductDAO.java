package com.example.bouncerentalapp.dao;

import com.example.bouncerentalapp.MyJDBC;
import com.example.bouncerentalapp.model.OrderProduct;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderProductDAO
{
    public static List<OrderProduct> getOrderProducts() {
        List<OrderProduct> orderProducts = new ArrayList<>();

        try (Connection conn = MyJDBC.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM ORDER_PRODUCTS")) {

            while (rs.next()) {
                orderProducts.add(new OrderProduct(
                        rs.getInt("order_product_id"),
                        rs.getInt("product_id"),
                        rs.getInt("order_id"),
                        rs.getInt("quantity_taken")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderProducts;
    }
}

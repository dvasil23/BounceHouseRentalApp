package com.example.bouncerentalapp.dao;
import com.example.bouncerentalapp.MyJDBC;
import com.example.bouncerentalapp.model.RentalProduct;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentalProductDAO
{
    public static List<RentalProduct> getRentalProducts() {
        List<RentalProduct> rentalProducts = new ArrayList<>();

        try (Connection conn = MyJDBC.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM RENTAL_PRODUCTS")) {

            while (rs.next()) {
                rentalProducts.add(new RentalProduct(
                        rs.getInt("product_id"),
                        rs.getInt("category_id"),
                        rs.getString("product_name"),
                        rs.getString("dimensions"),
                        rs.getInt("price"),
                        rs.getInt("quantity")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rentalProducts;
    }

    public static List<RentalProduct> getAvailableProducts(LocalDate startDate, LocalDate endDate) {
        List<RentalProduct> availableProducts = new ArrayList<>();

        String sql = "{CALL checkAvailableProducts(?, ?)}";

        try (Connection conn = MyJDBC.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setDate(1, Date.valueOf(startDate));
            stmt.setDate(2, Date.valueOf(endDate));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int productId = rs.getInt("product_id");
                    int categoryID = rs.getInt("category_id");
                    String name = rs.getString("product_name");
                    String dimensions = rs.getString("dimensions");
                    float price = rs.getFloat("price");
                    int amountAvailable = rs.getInt("amount_available");

                    // Assuming you have a Product constructor that includes available quantity
                    availableProducts.add(new RentalProduct(productId, categoryID, name, dimensions, price, amountAvailable));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return availableProducts;
    }

}

package com.example.bouncerentalapp.dao;
import com.example.bouncerentalapp.MyJDBC;
import com.example.bouncerentalapp.model.RentalProduct;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
}

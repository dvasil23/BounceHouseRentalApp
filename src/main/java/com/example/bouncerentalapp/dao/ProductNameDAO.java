package com.example.bouncerentalapp.dao;

import com.example.bouncerentalapp.MyJDBC;
import com.example.bouncerentalapp.model.ProductName;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//VIEW
public class ProductNameDAO
{
    public static List<ProductName> getAllProductNames() {
        List<ProductName> productList = new ArrayList<>();

        String sql = "SELECT * FROM product_names";

        try (Connection conn = MyJDBC.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String productName = rs.getString("product_name");
                String categoryType = rs.getString("category_type");

                // Handle nulls safely
                if (productName == null) productName = "N/A";
                if (categoryType == null) categoryType = "N/A";

                productList.add(new ProductName(productName, categoryType));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }
}

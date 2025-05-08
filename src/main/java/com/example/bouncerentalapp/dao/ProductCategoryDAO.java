package com.example.bouncerentalapp.dao;
import com.example.bouncerentalapp.MyJDBC;



import java.sql.*;


public class ProductCategoryDAO
{
    public static String getCategory(int categoryID) {

        String categoryType = null;
        String sql = "SELECT category_type FROM categories WHERE category_id = ?";

        try (Connection conn = MyJDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1,categoryID);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                categoryType = rs.getString("category_type");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categoryType;
}
}


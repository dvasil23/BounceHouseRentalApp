package com.example.bouncerentalapp.dao;

import com.example.bouncerentalapp.MyJDBC;
import com.example.bouncerentalapp.model.Admin;
import com.example.bouncerentalapp.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDAO
{
    public static boolean checkUserAndPassword(String user, String pass){
        boolean valid = false;
        Admin admin = null;

        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";

        try(Connection conn = MyJDBC.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, user);
            stmt.setString(2,pass);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                admin = new Admin(
                        rs.getInt("admin_id"),
                        rs.getString("username"),
                        rs.getString("password")
                );
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        if(admin != null)
            valid = true;
        return valid;
    }
}

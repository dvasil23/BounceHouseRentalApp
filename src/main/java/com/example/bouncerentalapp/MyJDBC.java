package com.example.bouncerentalapp;

import java.sql.*;

public class MyJDBC
{
    private static final String URL = "jdbc:mysql://localhost:3306/bounce_house_db";
    private static final String USER = "root";
    private static final String PASSWORD = "=68RKb5_p1";

//    try(Connection connection = DriverManager.getConnection(URL,USER,PASSWORD)){
//    System.out.println("Connected.");
//    Statement statement = connection.createStatement();
//    ResultSet results = statement.executeQuery("SELECT * FROM CUSTOMERS");
//
//    while(results.next()){
//        System.out.println("customer_id");
//        System.out.println("something");
//    }
//
//}
//    catch(SQLException ex)
//    {
//        Logger.getLogger(JDBCExample.class.getName().Log(Level.SEVERE,null,ex));
//    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


}



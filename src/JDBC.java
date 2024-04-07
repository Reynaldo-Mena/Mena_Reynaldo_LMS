package com.rey.lms;

import java.sql.*;
public class JDBC {

  /*  public static void connect() {
        Connection conn = null;
        try{
            String url = "jdbc:sqlite:/Users/Reyna/OneDrive/Desktop/SQLite/database";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite successfull. ");


        }catch( SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try{
                if(conn != null) {
                    conn.close();
                }
            }catch(SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }


    }
*/
public static void main(String[] args){
       // connect();
    Connection connection = null;
    try {
        // Load the SQLite JDBC driver
        Class.forName("org.sqlite.JDBC");

        // Connect to the SQLite database
        String url = "jdbc:sqlite:C:/sqlLite/sqlite-jdbc-3.45.2.0.jar/";
        connection = DriverManager.getConnection(url);

        if (connection != null) {
            System.out.println("Connected to the SQLite database.");
        } else {
            System.out.println("Failed to connect to the SQLite database.");
        }
    } catch (ClassNotFoundException e) {
        System.out.println("SQLite JDBC driver not found.");
        e.printStackTrace();
    } catch (SQLException e) {
        System.out.println("Failed to connect to the SQLite database.");
        e.printStackTrace();
    } finally {
        // Close the connection
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
}

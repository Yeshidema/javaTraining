package com.athang.javatraining.swing.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionUtil {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String databaseName = "athang-db";
            String URL = "jdbc:mysql://localhost:3306/";
            String user = "root";
            String password = "";
            Connection con = DriverManager.getConnection(URL + databaseName, user, password);
            // Creates table if not exixts already
            createTable(con);
            return con;
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
    private static void createTable(Connection connection) {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS contact "
                + "(ID INT PRIMARY KEY AUTO_INCREMENT ,"
                + " name VARCHAR(50), "
                + " number VARCHAR(50),"
                + " email VARCHAR(50)) ";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

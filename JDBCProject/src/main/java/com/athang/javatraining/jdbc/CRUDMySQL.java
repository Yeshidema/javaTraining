package com.athang.javatraining.jdbc;

import java.sql.*;


public class CRUDMySQL {
    public void createTable() {
        String url = "jdbc:mysql://localhost:3306/athang-db";
        String user = "root";
        String password = "";

        String createTableSQL = "CREATE TABLE IF NOT EXISTS Member "
                + "(ID INT PRIMARY KEY AUTO_INCREMENT,"
                + " SEC_ID VARCHAR(50), "
                + " LASTNAME VARCHAR(50), "
                + " FIRSTNAME VARCHAR(50), "
                + " ADDRESS TEXT,"
                + " CITY VARCHAR(50), "
                + " STATE VARCHAR(2), "
                + " ZIP VARCHAR(10))";
        System.out.println(createTableSQL);
        //Open Connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            // Do Operations
            statement.execute(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void insertData() {
        String url = "jdbc:mysql://localhost:3306/athang-db";
        String user = "root";
        String password = "";

        //Query Parameters
        String secId = "'10132343485'";
        String lastName = "'DEMA'";
        String firstName = "'YESHI'";
        String address = "'TABA, THIMPHU'";
        String city = "'TABA'";
        String state = "'TB'";
        String zip = "'1101'";
        String INSERT_MEMBER_SQL = "INSERT INTO member(" +
                "sec_id, lastname, firstname, address, city, state, zip)" +
                "VALUES (" + secId + "," + lastName + "," + firstName +
                "," + address + "," + city + "," + state + "," + zip + ");";

        System.out.println(INSERT_MEMBER_SQL);

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(INSERT_MEMBER_SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteData() {
        //jdbc:mysql://localhost:3306/athang-db
        String url = "jdbc:mysql://localhost/athang-db";
        String user = "root";
        String password = "";
        String QUERY = "delete from member where lastname =?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            preparedStatement.setString(1, "YESHI");
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateData() {
        String url = "jdbc:mysql://localhost/athang-db";
        String user = "root";
        String password = "";
        String QUERY = "update member set lastname =?, firstname =? where lastname=?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            preparedStatement.setString(1, "YESHI");
            preparedStatement.setString(2, "PRANGE");
            preparedStatement.setString(3, "JOHN");
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
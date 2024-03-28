package com.athang.javatraining.jdbc;import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CRUDOperationMySqlSQL {
    public static void main(String[] args) {
        CRUDOperationMySqlSQL cops = new CRUDOperationMySqlSQL();
        cops.execute();
    }

    private void execute() {
        createTable();
        insertDataWithPreparedStatement();
        insertDataWithStatement();
        readDataWithPreparedStatement();
        readAllDataWithStatement();
        readSpecificDataWithStatement();
        readDataWithPreparedStatement();
        updateData();
        //deleteData();
    }

    private void readAllDataWithStatement() {
        //Class.forName("org.postgresql.Driver");
        String url = "jdbc:mysql://localhost/athang-db";
        String user = "root";
        String password = "";
        String SELECT_ALL_QUERY = "select * from member";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String secId = resultSet.getString("sec_id");
                String lName = resultSet.getString("lastname");
                String fName = resultSet.getString("firstname");
                String address = resultSet.getString("address");
                System.out.println(id + "," + secId + "," + lName + "," + fName + "," + address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void readSpecificDataWithStatement() {
        //Class.forName("org.postgresql.Driver");
        String url = "jdbc:mysql://localhost/athang-db";
        String user = "root";
        String password = "";

        //This is SQL Injection
        String lastName = "'sdfsdf' or 1=1";
        String SELECT_ALL_QUERY = "select * from member where lastname=" + lastName + ";";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String secId = resultSet.getString("sec_id");
                String lName = resultSet.getString("lastname");
                String fName = resultSet.getString("firstname");
                String address = resultSet.getString("address");
                System.out.println(id + "," + secId + "," + lName + "," + fName + "," + address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void insertDataWithPreparedStatement() {
        String url = "jdbc:mysql://localhost/athang-db";
        String user = "root";
        String password = "";
        String INSERT_MEMBER_SQL = "INSERT INTO member(" +
                " sec_id, lastname, firstname, address, city, state, zip)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?);";
        System.out.println(INSERT_MEMBER_SQL);

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MEMBER_SQL)) {
            preparedStatement.setString(1, "222743521");
            preparedStatement.setString(2, "MCSHANE");
            preparedStatement.setString(3, "MIKI");
            preparedStatement.setString(4, "2220 KIEL PKWY");
            preparedStatement.setString(5, "ROCHERT");
            preparedStatement.setString(6, "MN");
            preparedStatement.setString(7, "56578");
//            preparedStatement.addBatch();
//            preparedStatement.executeBatch();
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void insertDataWithStatement() {
        String url = "jdbc:mysql://localhost/athang-db";
        String user = "root";
        String password = "";
        String secId = "'101396885'";
        String lastName = "'ALVA'";
        String firstName = "'CAROLA'";
        String address = "'4829 JAUREGUI BLVD'";
        String city = "'CORCORAN'";
        String state = "'IL'";
        String zip = "'55357'";
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

    private void readDataWithPreparedStatement() {
        String url = "jdbc:mysql://localhost/athang-db";
        String user = "root";
        String password = "";
        String QUERY = "select * from member where lastname =?";
        String SELECT_ALL_QUERY = "select * from member";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String secId = rs.getString("sec_id");
                String lName = rs.getString("lastname");
                String fName = rs.getString("firstname");
                String address = rs.getString("address");
                System.out.println(id + "," + secId + "," + lName + "," + fName + "," + address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            preparedStatement.setString(1, "ALVA");
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String secId = rs.getString("sec_id");
                String lName = rs.getString("lastname");
                String fName = rs.getString("firstname");
                String address = rs.getString("address");
                System.out.println(id + "," + secId + "," + lName + "," + fName + "," + address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteData() {
        //jdbc:mysql://localhost:3306/athang-db
        String url = "jdbc:mysql://localhost/athang-db";
        String user = "root";
        String password = "";
        String QUERY = "delete from member where lastname =?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            preparedStatement.setString(1, "ALVA");
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateData() {
        String url = "jdbc:mysql://localhost/athang-db";
        String user = "root";
        String password = "";
        String QUERY = "update member set lastname =?, firstname =? where lastname=?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            preparedStatement.setString(1, "TASHI");
            preparedStatement.setString(2, "TSHERING");
            preparedStatement.setString(3, "ALVA");
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTable() {
        String url = "jdbc:mysql://localhost/athang-db";
        String user = "root";
        String password = "";

        String createTableSQL = "CREATE TABLE IF NOT EXISTS Member "
                + "(ID SERIAL PRIMARY KEY ,"
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
            connection.setAutoCommit(false);
            boolean status = statement.execute(createTableSQL);
            connection.commit();
            System.out.println(status);
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
}

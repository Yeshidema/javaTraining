package com.athang.javatraining.jdbc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoadDataFromFileToDataBase {
    public static void main(String[] args) {
        LoadDataFromFileToDataBase loadToDb = new LoadDataFromFileToDataBase();
        loadToDb.execute();
    }

    private void execute() {
        List<Member> memberList = getMembersFromFile();
        System.out.println("Total Members: " + memberList.size());
        loadMembersToDatabase(memberList);
        readMembersFromDatabase();
    }

    private void readMembersFromDatabase() {
        String url = "jdbc:mysql://localhost/athang-db";
        String user = "root";
        String password = "";
        String SELECT_ALL_QUERY = "select * from member";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Member member = new Member();
                member.setId(rs.getString("sec_id"));
                member.setLastName(rs.getString("lastname"));
                member.setFirstName(rs.getString("firstname"));
                member.setAddress(rs.getString("address"));
                member.setCity(rs.getString("city"));
                member.setState(rs.getString("state"));
                member.setZip(rs.getString("zip"));
                System.out.println(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadMembersToDatabase(List<Member> memberList) {
        String url = "jdbc:mysql://localhost/athang-db";
        String user = "root";
        String password = "";
        String INSERT_MEMBER_SQL = "INSERT INTO member(" +
                " sec_id, lastname, firstname, address, city, state, zip)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?);";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MEMBER_SQL)) {
            for (Member member : memberList) {
                preparedStatement.setString(1, member.getId());
                preparedStatement.setString(2, member.getLastName());
                preparedStatement.setString(3, member.getFirstName());
                preparedStatement.setString(4, member.getAddress());
                preparedStatement.setString(5, member.getCity());
                preparedStatement.setString(6, member.getState());
                preparedStatement.setString(7, member.getZip());

//            preparedStatement.addBatch();
//            preparedStatement.executeBatch();
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private List<Member> getMembersFromFile() {
        List<Member> memberList = new ArrayList<>();
        URL memberFileURL = getClass().getClassLoader().getResource("Members.txt");
        if(memberFileURL == null){
            throw new RuntimeException("Members.txt file not found");
        }
        try (BufferedReader br = new BufferedReader(new FileReader(new File(memberFileURL.toURI())))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                Member member = new Member();
                member.setId(line.substring(0, 12).strip());
                member.setLastName(line.substring(12, 12 + 25).strip());
                member.setFirstName(line.substring(12 + 25, 12 + 25 + 25).strip());
                member.setAddress(line.substring(12 + 25 + 25, 12 + 25 + 25 + 30).strip());
                member.setCity(line.substring(12 + 25 + 25 + 30, 12 + 25 + 25 + 30 + 20).strip());
                member.setState(line.substring(12 + 25 + 25 + 30 + 20, 12 + 25 + 25 + 30 + 20 + 4).strip());
                member.setZip(line.substring(12 + 25 + 25 + 30 + 20 + 4, 12 + 25 + 25 + 30 + 20 + 4 + 5).strip());
                memberList.add(member);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return memberList;
    }

}
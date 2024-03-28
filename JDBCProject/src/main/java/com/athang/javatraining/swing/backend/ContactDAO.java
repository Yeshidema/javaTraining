package com.athang.javatraining.swing.backend;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


public class ContactDAO {

    Connection con;
    private PreparedStatement ps;

    public int addContact(Contact contact) {
        con = ConnectionUtil.getConnection();
        try {
            ps = con.prepareStatement("INSERT INTO contact (name, number, email) VALUES (?,?,?)");
            ps.setString(1, contact.getName());
            ps.setString(2, contact.getNumber());
            ps.setString(3, contact.getEmail());
            int n = ps.executeUpdate();
            con.close();
            return n;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
    }

    public int deleteContact(int id) {
        // TODO Auto-generated method stub
        con = ConnectionUtil.getConnection();
        try {
            ps = con.prepareStatement("Delete From contact where id=?");
            ps.setInt(1, id);
            int n = ps.executeUpdate();
            con.close();
            return n;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
    }

    public Contact searchContact(int id) {
        con = ConnectionUtil.getConnection();
        try {
            ps = con.prepareStatement("SELECT * FROM contact WHERE id=?");
            ps.setInt(1, id);

            ResultSet result = ps.executeQuery();
            Contact contact = null;
            if (result.next()) {
                contact = new Contact();
                contact.setId(result.getInt("id"));
                contact.setName(result.getString("name"));
                contact.setNumber(result.getString("number"));
                contact.setEmail(result.getString("email"));
            }
            con.close();
            return contact;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public int updateContact(Contact contact) {
        con = ConnectionUtil.getConnection();
        try {
            ps = con.prepareStatement("UPDATE contact SET name = ?, number =?, email =? WHERE id=?");
            ps.setString(1, contact.getName());
            ps.setString(2, contact.getNumber());
            ps.setString(3, contact.getEmail());
            ps.setInt(4, contact.getId());
            int n = ps.executeUpdate();
            con.close();
            return n;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
    }

    public void loadData(DefaultTableModel tableModel) {
        try (Connection conn = ConnectionUtil.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery("select * from Contact");
            ResultSetMetaData metaData = rs.getMetaData();

            // Names of columns
            Vector<String> columnNames = new Vector<String>();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(metaData.getColumnName(i));
            }

            // Data of the table
            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
            while (rs.next()) {
                Vector<Object> vector = new Vector<Object>();
                for (int i = 1; i <= columnCount; i++) {
                    vector.add(rs.getObject(i));
                }
                data.add(vector);
            }

            tableModel.setDataVector(data, columnNames);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

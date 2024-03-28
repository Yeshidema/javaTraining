package com.athang.javatraining.swing.view;

import com.athang.javatraining.swing.backend.Contact;
import com.athang.javatraining.swing.backend.ContactDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateDialog extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtName;
    private JTextField txtNumber;
    private JTextField txtEmail;
    private JTextField txtId;

    /**
     * Create the dialog.
     */
    public UpdateDialog() {
        setTitle("Update Contact Details");
        setBounds(100, 100, 350, 200);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblName = new JLabel("Name");
        lblName.setEnabled(false);
        lblName.setBounds(10, 42, 46, 14);
        contentPanel.add(lblName);

        txtName = new JTextField();
        txtName.setEnabled(false);
        txtName.setColumns(10);
        txtName.setBounds(85, 39, 86, 20);
        contentPanel.add(txtName);

        JLabel lblNumber = new JLabel("Number");
        lblNumber.setEnabled(false);
        lblNumber.setBounds(10, 72, 46, 14);
        contentPanel.add(lblNumber);

        txtNumber = new JTextField();
        txtNumber.setEnabled(false);
        txtNumber.setColumns(10);
        txtNumber.setBounds(85, 69, 86, 20);
        contentPanel.add(txtNumber);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setEnabled(false);
        lblEmail.setBounds(10, 72, 46, 14);
        contentPanel.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setEnabled(false);
        txtEmail.setColumns(10);
        txtEmail.setBounds(85, 69, 86, 20);
        contentPanel.add(txtEmail);

        JLabel lblId = new JLabel("ID");
        lblId.setBounds(10, 14, 46, 14);
        contentPanel.add(lblId);

        txtId = new JTextField();
        txtId.setColumns(10);
        txtId.setBounds(85, 11, 86, 20);
        contentPanel.add(txtId);

        JButton btnSearch = new JButton("Search");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ContactDAO cDAO = new ContactDAO();
                int id = Integer.parseInt(txtId.getText());
                Contact contact = cDAO.searchContact(id);
                if (contact != null) {
                    txtName.setText(contact.getName());
                    txtNumber.setText(contact.getNumber());
                    txtEmail.setText(contact.getEmail());
                    txtName.setEnabled(true);
                    txtNumber.setEnabled(true);
                    txtEmail.setEnabled(true);
                    lblName.setEnabled(true);
                    lblNumber.setEnabled(false);
                    lblEmail.setEnabled(true);
                    txtId.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(contentPanel, "Record Not Found For Given ID!!!");
                }
            }
        });
        btnSearch.setBounds(181, 10, 89, 23);
        contentPanel.add(btnSearch);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("OK");
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Contact contact = new Contact();
                        contact.setId(Integer.parseInt(txtId.getText()));
                        contact.setName(txtName.getText());
                        contact.setNumber(txtNumber.getText());
                        contact.setNumber(txtEmail.getText());
                        ContactDAO cDAO = new ContactDAO();
                        int count = cDAO.updateContact(contact);
                        if (count > 0) {
                            JOptionPane.showMessageDialog(contentPanel, "Record Updated Successfully!!!");
                        } else {
                            JOptionPane.showMessageDialog(contentPanel, "Record Can't Updated !!!");
                        }
                        //System.out.println("Result : "+count);
                        dispose();
                    }
                });
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("Cancel");
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
    }
}

package com.athang.javatraining.swing.view;

import com.athang.javatraining.swing.backend.Contact;
import com.athang.javatraining.swing.backend.ContactDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertDialog extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtName;
    private JTextField txtNumber;
    private JTextField txtEmail;

    /**
     * Create the dialog.
     */
    public InsertDialog() {
        setTitle("Insert Contact Details");
        setBounds(100, 100, 400, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        {
            txtNumber = new JTextField();
            txtNumber.setBounds(85, 49, 200, 20);
            contentPanel.add(txtNumber);
            txtNumber.setColumns(10);
        }
        {
            txtName = new JTextField();
            txtName.setBounds(85, 11, 200, 20);
            contentPanel.add(txtName);
            txtName.setColumns(10);
        }
        {
            txtEmail = new JTextField();
            txtEmail.setBounds(85, 88, 200, 20);
            contentPanel.add(txtEmail);
            txtEmail.setColumns(10);
        }

        JLabel lblName = new JLabel("Name");
        lblName.setBounds(10, 14, 60, 14);
        contentPanel.add(lblName);

        JLabel lblNumber = new JLabel("Number");
        lblNumber.setBounds(10, 52, 60, 14);
        contentPanel.add(lblNumber);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(10, 90, 60, 14);
        contentPanel.add(lblEmail);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("ADD");
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        Contact contact = new Contact();
                        contact.setName(txtName.getText());
                        contact.setNumber(txtNumber.getText());
                        contact.setEmail((txtEmail.getText()));
                        ContactDAO cDAO = new ContactDAO();
                        int count = cDAO.addContact(contact);
                        System.out.println("Result : " + count);
                        if (count > 0) {
                            JOptionPane.showMessageDialog(contentPanel, "Record Inserted Successfully!!!");
                        } else {
                            JOptionPane.showMessageDialog(contentPanel, "Record Can't Inserted !!!");
                        }
                        dispose();
                    }
                });
                okButton.setActionCommand("ADD");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("Cancel");
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        dispose();
                    }
                });
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
    }
}

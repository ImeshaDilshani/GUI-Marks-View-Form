package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class STmarks {
    private JTextField textID;
    private JTextField textName;
    private JTextField textCA;
    private JTextField textPractical;
    private JTextField textTheory;
    private JButton viewAllMarksButton;
    private JButton saveButton;
    private JPanel STMarks;
//    private JTable marksTable;
//    private DefaultTableModel tableModel;

    Connection con;
    PreparedStatement pst;

    public STmarks() {
        // Initialize UI components
//        createUIComponents();

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveMarks();
            }
        });
        viewAllMarksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                displayAllMarks();
            }
        });
    }

    private void saveMarks() {
        // Retrieve marks data from text fields
        String id = textID.getText();
        String name = textName.getText();
        String ca = textCA.getText();
        String practical = textPractical.getText();
        String theory = textTheory.getText();

        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/oopdb";
        String username = "root";
        String password = "Imesha@99";

        try {
            // Load the MySQL JDBC driver class
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection to the database
            Connection connection = DriverManager.getConnection(url, username, password);

            // SQL INSERT statement to add marks to the database
            String SQL = "INSERT INTO CLASS (sid, sname, sca, spractical, stheory) VALUES (?, ?, ?, ?, ?)";

            // Create a prepared statement to execute the SQL query
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            // Set parameters in the prepared statement with the retrieved marks data
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, ca);
            preparedStatement.setString(4, practical);
            preparedStatement.setString(5, theory);

            // Execute the SQL INSERT query
            preparedStatement.executeUpdate();

            // Show a success message dialog to the user
            JOptionPane.showMessageDialog(null,
                    "Marks saved successfully!");

            // Close the prepared statement and database connection
            preparedStatement.close();
            connection.close();
        } catch (Exception exception) {
            // Print stack trace for any exceptions that occur
            exception.printStackTrace();

            // Show an error message dialog to the user
            JOptionPane.showMessageDialog(null, "Failed to save marks!");
        }
    }


//    private void displayAllMarks() {
//        String url = "jdbc:mysql://localhost:3306/oopdb";
//        String username = "root";
//        String password = "Imesha@99";
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection connection = DriverManager.getConnection(url, username, password);
//
//            String SQL = "SELECT * FROM CLASS";
//            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            // Clear existing table data
//            tableModel.setRowCount(0);
//
//            // Populate table with fetched data
//            while (resultSet.next()) {
//                Object[] rowData = {
//                        resultSet.getString("sid"),
//                        resultSet.getString("sname"),
//                        resultSet.getString("sca"),
//                        resultSet.getString("spractical"),
//                        resultSet.getString("stheory")
//                };
//                tableModel.addRow(rowData);
//            }
//
//            resultSet.close();
//            preparedStatement.close();
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace(); // Print the stack trace for SQL exceptions
//            JOptionPane.showMessageDialog(null,
//                    "SQL error: " + e.getMessage());
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace(); // Print the stack trace for class not found exceptions
//            JOptionPane.showMessageDialog(null,
//                    "Failed to load JDBC driver: " + e.getMessage());
//        } catch (Exception e) {
//            e.printStackTrace(); // Print the stack trace for other exceptions
//            JOptionPane.showMessageDialog(null,
//                    "An unexpected error occurred: " + e.getMessage());
//        }
//    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("STmarks");
        frame.setContentPane(new STmarks().STMarks);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

//    private void createUIComponents() {
//        // Initialize table model
//        tableModel = new DefaultTableModel();
//        tableModel.addColumn("ID");
//        tableModel.addColumn("Name");
//        tableModel.addColumn("CA");
//        tableModel.addColumn("Practical");
//        tableModel.addColumn("Theory");
//
//        // Create table with model
//        marksTable = new JTable(tableModel);
//    }
}

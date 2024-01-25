package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class STmarks extends Component {
    private JTextField textID;
    private JTextField textName;
    private JTextField textCA;
    private JTextField textPractical;
    private JTextField textTheory;
    private JButton viewAllMarksButton;
    private JButton saveButton;
    private JPanel STMarks;

    Connection con;
    PreparedStatement pst;

    public STmarks() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveMarks();
            }
        });
    }

    private void saveMarks() {
        String id = textID.getText();
        String name = textName.getText();
        String ca = textCA.getText();
        String practical = textPractical.getText();
        String theory = textTheory.getText();

        String url = "jdbc:mysql://localhost:3306/oopdb";
        String username = "root";
        String password = "Imesha@99";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);

            String SQL = "INSERT INTO CLASS (sid, sname, sca, spractical, stheory) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, ca);
            preparedStatement.setString(4, practical);
            preparedStatement.setString(5, theory);

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }



    public static void main(String[] args) {
        JFrame frame = new JFrame("STmarks");
        frame.setContentPane(new STmarks().STMarks);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}



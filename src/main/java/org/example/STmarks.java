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

        //convert student id, CA, practical, theory into integer
        int sid = Integer.parseInt(id);
        int sname = Integer.parseInt(name);
        int sca = Integer.parseInt(ca);
        int spractical = Integer.parseInt(practical);
        int stheory = Integer.parseInt(theory);

        String url="jdbc:mysql://localhost:3306/oopdb";
        String username="root";
        String password="";

        try{
            //register the drive class
            //open connection
            Class.forName("com.mysql.cj.jdbc.Driver");

            //here student's oop marks is database name, root is username and password
            Connection CONNECTION = DriverManager.getConnection("jdbc:mysql://localhost:3306/oopdb","root","Imesha@99");

            //create statement for insert data into table
            Statement STATEMENT=CONNECTION.createStatement();

            String SQL = "INSERT INTO CLASS VALUES("+id+","+name+","+ca+","+practical+","+theory+")";

            //create resultset
            ResultSet resultSet=STATEMENT.executeQuery("select * from oop marks");

            //execute query
            while (resultSet.next()){
                sid = resultSet.getInt("sid");
                sname = Integer.parseInt(resultSet.getString("sname").trim());
                sca = resultSet.getInt("sca");
                spractical = resultSet.getInt("spractical");
                stheory = resultSet.getInt("stheory");

                System.out.println("StudentID" + "StudentName" + "CA" + "Practical" + "Theory");
            }

            resultSet.close();
            //close the statment
            STATEMENT.close();
            //close the connection
            CONNECTION.close();


        }catch(Exception exception){

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



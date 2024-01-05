package project.Teacher;

import project.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ViewMyCourses extends JFrame {
    JLabel title;
    JScrollPane scroll;
    JTable table;
    DefaultTableModel model;
    String[][] data;
    String[] columnNames;
    public ViewMyCourses(){
        super("View My Courses");
        setLayout(new BorderLayout());

        title = new JLabel("View My Courses", JLabel.CENTER);
        title.setFont(title.getFont().deriveFont (22.0f));
        title.setBackground(Color.LIGHT_GRAY);
        title.setForeground(Color.BLACK);
        title.setOpaque(true);
        add(title, BorderLayout.NORTH);







        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500,360);
        setLocation(420,260);


        try {
            DBConnection c1 = new DBConnection();
            ResultSet rs = c1.s.executeQuery("SELECT courseName FROM sub_courses WHERE teacherID = '" + TeacherLogin.currentTeacherID + "'");
            DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Class Name"}, 0);
            table = new JTable(tableModel);


            while (rs.next()) {
                String courseName = rs.getString("courseName");
                Object[] rowData = {courseName};
                tableModel.addRow(rowData);


            }



        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception here, display an error message, or log it.
        }

        // Add the JTable to the panel with a JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);

    }






    public static void main(String[] args) {
        new ViewMyCourses();
    }
}
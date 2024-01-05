package project.Student;

import project.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Ratings extends JFrame implements ActionListener {
    JPanel  rightPanel;
    JLabel title;
    JComboBox Filter;
    JButton enterButton;
    JTable table;
    DefaultTableModel tableModel;
    JTextField courseRating;
    JLabel courseRatingLbl;
    ArrayList<String> courseIDs = new ArrayList<String>();

    public Ratings() {
        SwingUtilities.invokeLater(() -> {
            try {
                DBConnection connection = new DBConnection();
                String query = "SELECT * FROM sub_courses ";
                ResultSet rs = connection.s.executeQuery(query);
                courseIDs = new ArrayList();

                tableModel = new DefaultTableModel(new Object[]{"Course ID", "Course Name", "Location ID", "Ratings", "Teacher ID", "Price"}, 0);
                table = new JTable(tableModel);

                while (rs.next()) {
                    String courseId = rs.getString("courseId");
                    courseIDs.add(courseId);
                    String courseName = rs.getString("courseName");
                    String locationId = rs.getString("locationId");
                    String ratings = rs.getString("ratings");
                    String teacherID = rs.getString("teacherID");
                    String Price = rs.getString("Price");
                    Object[] rowData = {courseId, courseName, locationId, ratings, teacherID, Price};
                    tableModel.addRow(rowData);
                }

                rs.close();

                // Create a JFrame to display the panel
                JFrame frame = new JFrame("Ratings");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                // Create a JPanel to hold the components
                JPanel panel = new JPanel();
                panel.setLayout(null);
                Dimension sidePanelSize = new Dimension(180, 720);
                panel.setPreferredSize(sidePanelSize);
                frame.add(panel, BorderLayout.WEST);

                // Create a custom alignment for components
                Component rigidArea = Box.createRigidArea(new Dimension(0, 5)); // Adjust the height as needed
                panel.add(rigidArea);

                courseRatingLbl = new JLabel("Your Ratings");
                courseRatingLbl.setFont(new Font(Font.SERIF, Font.BOLD, 16));
                courseRatingLbl.setBounds(30, 150, 140, 28);
                courseRatingLbl.setHorizontalAlignment(JLabel.LEFT);
                panel.add(courseRatingLbl);

                courseRating = new JTextField();
                courseRating.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
                courseRating.setBounds(30, 200, 120, 28);
                courseRating.setHorizontalAlignment(JLabel.CENTER);
                panel.add(courseRating);

                // Add "All Locations" at the beginning of the list
                String[] courseIDArray = courseIDs.toArray(new String[0]);
                Filter = new JComboBox<>(courseIDArray);
                Filter.setBounds(30, 100, 120, 28);
                panel.add(Filter);






                enterButton = new JButton("Enter");
                enterButton.setBounds(30, 300, 120, 28);
                enterButton.setBackground(new Color(246, 246, 246));
                enterButton.addActionListener((ActionListener) this);
                panel.add(enterButton);

                rightPanel = new JPanel(new BorderLayout());
                frame.add(rightPanel, BorderLayout.CENTER);


                // Add the JTable to the panel with a JScrollPane
                JScrollPane scrollPane = new JScrollPane(table);
                rightPanel.add(scrollPane);


                frame.pack();
                frame.setVisible(true);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
    private void updateRatingInDatabase(int courseId, int ratings) {
        try {
            DBConnection connection = new DBConnection(); // Replace with your database connection method
            String updateQuery = "UPDATE sub_courses SET ratings = ? WHERE courseId = ?";
            PreparedStatement preparedStatement = connection.c.prepareStatement(updateQuery);
            preparedStatement.setInt(1, ratings);
            preparedStatement.setInt(2, courseId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Thank You For Your Review");
                // Retrieve the updated ratings from the database

            } else {
                JOptionPane.showMessageDialog(this, "Failed to update the rating.");
            }
            preparedStatement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "An error occurred while updating the rating.");
            e.printStackTrace();
        }
    }



    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == enterButton) {
            // Get the selected courseId (assuming courseId is an Integer)
// Get the selected courseId (assuming courseId is an int)
            String selectedCourseIdStr = (String) Filter.getSelectedItem();

            // Convert the selectedCourseIdStr to an integer
            int selectedCourseId = Integer.parseInt(selectedCourseIdStr);

            // Get the entered ratings from the courseRating textField
            try {
                int enteredRatings = Integer.parseInt(courseRating.getText());


                // Perform the database update operation
                updateRatingInDatabase(selectedCourseId, enteredRatings);
            } catch (NumberFormatException e) {
                // Handle the case where the entered ratings are not a valid number
                JOptionPane.showMessageDialog(this, "Please enter a valid numeric rating.");
            }
            refreshTableData();

        }
    }
    private void refreshTableData() {
        try {
            // Fetch updated data from the database
            DBConnection connection = new DBConnection();
            String query = "SELECT * FROM sub_courses ";
            ResultSet rs = connection.s.executeQuery(query);

            // Clear existing table data
            tableModel.setRowCount(0);

            while (rs.next()) {
                // Populate the table with updated data
                String courseId = rs.getString("courseId");
                String courseName = rs.getString("courseName");
                String locationId = rs.getString("locationId");
                String ratings = rs.getString("ratings");
                String teacherID = rs.getString("teacherID");
                String Price = rs.getString("Price");
                Object[] rowData = {courseId, courseName, locationId, ratings, teacherID, Price};
                tableModel.addRow(rowData);
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ratings());
    }
}

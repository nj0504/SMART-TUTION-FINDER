package project.Teacher;

import project.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class AddCourse extends JFrame implements ActionListener {
    JLabel title, subjectCbLbl, courseNameLbl, courseDomainLbl, courseLocationLbl, coursePriceLbl;
    JComboBox subjectsCb;
    JTextArea courseDescription, courseContent;
    JButton addBtn;
    JPanel middlePanel;
    JTextField courseName, courseDomain, courseLocation, coursePrice;
    String SubjectsData[];
    JScrollPane scroll, scroll2;

    public AddCourse() {
        super("Add Course");
        setLayout(new BorderLayout());

        title = new JLabel("Add Course", JLabel.CENTER);
        title.setFont(title.getFont().deriveFont(22.0f));
        title.setBackground(Color.LIGHT_GRAY);
        title.setForeground(Color.BLACK);
        title.setOpaque(true);
        add(title, BorderLayout.NORTH);

        middlePanel = new JPanel();
        middlePanel.setLayout(null);
        add(middlePanel, BorderLayout.CENTER);


        courseDomainLbl = new JLabel("Course Domain");
        courseDomainLbl.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        courseDomainLbl.setBounds(80, 75, 140, 28);
        courseDomainLbl.setHorizontalAlignment(JLabel.LEFT);
        middlePanel.add(courseDomainLbl);

        courseDomain = new JTextField();
        courseDomain.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        courseDomain.setBounds(200, 75, 120, 28);
        courseDomain.setHorizontalAlignment(JLabel.CENTER);
        middlePanel.add(courseDomain);

        courseNameLbl = new JLabel("Class Name");
        courseNameLbl.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        courseNameLbl.setBounds(80, 175, 140, 28);
        courseNameLbl.setHorizontalAlignment(JLabel.LEFT);
        middlePanel.add(courseNameLbl);

        courseName = new JTextField();
        courseName.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        courseName.setBounds(200, 175, 120, 28);
        courseName.setHorizontalAlignment(JLabel.CENTER);
        middlePanel.add(courseName);

        courseLocationLbl = new JLabel("Course Location");
        courseLocationLbl.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        courseLocationLbl.setBounds(80, 275, 140, 28);
        courseLocationLbl.setHorizontalAlignment(JLabel.LEFT);
        middlePanel.add(courseLocationLbl);

        courseLocation = new JTextField();
        courseLocation.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        courseLocation.setBounds(200, 275, 120, 28);
        courseLocation.setHorizontalAlignment(JLabel.CENTER);
        middlePanel.add(courseLocation);

        coursePriceLbl = new JLabel("Price");
        coursePriceLbl.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        coursePriceLbl.setBounds(80, 375, 140, 28);
        coursePriceLbl.setHorizontalAlignment(JLabel.LEFT);
        middlePanel.add(coursePriceLbl);

        coursePrice = new JTextField();
        coursePrice.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        coursePrice.setBounds(200, 375, 120, 28);
        coursePrice.setHorizontalAlignment(JLabel.CENTER);
        middlePanel.add(coursePrice);


        addBtn = new JButton("Add");
        addBtn.setFont(new Font(Font.SERIF, Font.BOLD, 15));
        addBtn.setHorizontalAlignment(JButton.CENTER);
        addBtn.addActionListener(this);
        add(addBtn, BorderLayout.SOUTH);


        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(720, 570);
        setLocation(375, 145);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addBtn) {
            String courseDomainStr = courseDomain.getText();
            String courseNameStr = courseName.getText();
            String courseLocationStr = courseLocation.getText();
            String coursePriceStr = coursePrice.getText();

            try {
                DBConnection c1 = new DBConnection();

                // First, retrieve locationId from the "location" table based on the entered location
                String locationQuery = "SELECT locationId FROM location WHERE locationName = '" + courseLocationStr + "'";
                ResultSet locationRs = c1.s.executeQuery(locationQuery);
                locationRs.next();
                int locationId = locationRs.getInt("locationId");

                // Next, retrieve courseDomainId from the "courses" table based on the entered courseDomain
                String courseDomainQuery = "SELECT courseDomainId FROM courses WHERE courseDomainName = '" + courseDomainStr + "'";
                ResultSet courseDomainRs = c1.s.executeQuery(courseDomainQuery);
                courseDomainRs.next();
                int courseDomainId = courseDomainRs.getInt("courseDomainId");

                // Finally, insert the values into the "sub_courses" table
                String subCoursesQuery = "INSERT INTO sub_courses (courseDomainId, courseName, locationId, ratings, teacherID, Price) " +
                        "VALUES (" + courseDomainId + ", '" + courseNameStr + "', " + locationId + ", '0', " +
                        TeacherLogin.currentTeacherID + ", '" + coursePriceStr + "')";

                int x = c1.s.executeUpdate(subCoursesQuery);
                if (x == 0) {
                    JOptionPane.showMessageDialog(null, "Some Error Occurred");
                } else {
                    JOptionPane.showMessageDialog(null, "Course Added!");
                    dispose();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "This Course is Already Being Offered by Another Teacher!");
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        new AddCourse();
    }

}

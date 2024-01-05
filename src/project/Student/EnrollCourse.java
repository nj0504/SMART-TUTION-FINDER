package project.Student;

import project.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;



public class EnrollCourse extends JFrame implements ActionListener{
    JLabel title, subjectCbLbl, courseCbLbl, courseDescriptionLbl, lblUsername, text_label, text_label_1, l1;
    JTextArea courseDescription;
    JComboBox subjectsCb, coursesCb, locationFilter, ratingsFilter, priceFilter;
    JButton enrollBtn, backBtn, b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, searchButton, searchButton1;
    JPanel middlePanel, sidePanel, rightPanel;
    JScrollPane scroll;

    JTextField searchField;
    ArrayList<String> courseDomainIds = new ArrayList<String>();
    DBConnection c1 = new DBConnection();

    public EnrollCourse(){
        super("Enroll Course");
        setLayout(new BorderLayout());
        setSize(1280,720);
        setLocation(35,30);

        title = new JLabel("Enroll Course", JLabel.CENTER);
        title.setFont(title.getFont().deriveFont (22.0f));
        title.setBackground(Color.LIGHT_GRAY);
        title.setForeground(Color.BLACK);
        title.setOpaque(true);
        add(title, BorderLayout.NORTH);

        middlePanel = new JPanel();
        middlePanel.setLayout(null);

        add(middlePanel, BorderLayout.CENTER);

        sidePanel = new JPanel();
        sidePanel.setLayout(null);
        sidePanel.setBackground(new Color(0, 26, 195));
        Dimension sidePanelSize = new Dimension(180, 720);
        sidePanel.setPreferredSize(sidePanelSize);
        add(sidePanel, BorderLayout.WEST);

        String firstName = null, lastName = null, gender = "";
        try{
            DBConnection c1 = new DBConnection();
            PreparedStatement ps = c1.c.prepareStatement("select * from Student where stdID = '"+ StudentLogin.currentStudentID +"'");
            ResultSet rs = ps.executeQuery();


            if(rs.next()){
                firstName = rs.getString("fname");
                lastName = rs.getString("lname");
                gender = rs.getString("Gender");

            }else{
                JOptionPane.showMessageDialog(null, "Not Found");
            }
        }catch(HeadlessException | NumberFormatException | SQLException e){
            e.printStackTrace();
        }





        lblUsername = new JLabel();
        lblUsername.setFont(new Font(Font.SERIF,Font.BOLD, 20));
        lblUsername.setForeground(new Color(45,255,3));
        lblUsername.setBounds(20, 98, 150, 40);
        lblUsername.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        lblUsername.setText(firstName + " " + lastName);
        sidePanel.add(lblUsername);

        backBtn = new JButton("Back");
        backBtn.setFont(new Font(Font.SERIF,Font.BOLD, 13));
        backBtn.setBackground(Color.WHITE);
        backBtn.setForeground(Color.BLACK);
        backBtn.setBounds(30, 600, 120, 28);
        backBtn.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        backBtn.addActionListener((ActionListener) this);
        sidePanel.add(backBtn);

        searchField = new JTextField(100);
        searchButton = new JButton("Search");
        searchButton.setBounds(700, 35, 120, 28);
        searchButton.setBackground(new Color(246, 246, 246));
        searchButton.addActionListener((ActionListener) this);
        searchField.setBounds(50, 35, 650, 28);
        searchField.setBackground(new Color(246, 246, 246));
        middlePanel.add(searchField);
        middlePanel.add(searchButton);




        subjectCbLbl = new JLabel("Select Subject");
        subjectCbLbl.setFont(new Font(Font.SERIF,Font.BOLD, 16));
        subjectCbLbl.setBounds(260, 80, 120, 28);
        subjectCbLbl.setHorizontalAlignment(JLabel.CENTER);
        middlePanel.add(subjectCbLbl);

        subjectsCb = new JComboBox();
        subjectsCb.setSelectedIndex(-1);
        subjectsCb.setBounds(400, 80, 140, 28);
        subjectsCb.addActionListener(this);
        middlePanel.add(subjectsCb);

        searchButton1 = new JButton("Search");
        searchButton1.setBounds(550, 80, 120, 28);
        searchButton1.setBackground(new Color(246, 246, 246));
        searchButton1.addActionListener((ActionListener) this);
        middlePanel.add(searchButton1);


        try {

            String query = "SELECT DISTINCT courseDomainName FROM courses"; // Assuming "subject_domain" is the column containing subject domain names
            PreparedStatement preparedStatement = c1.c.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String subjectDomain = resultSet.getString("courseDomainName");
                subjectsCb.addItem(subjectDomain); // Add each subject domain to the JComboBox
            }

            // Close the resources (preparedStatement and resultSet)
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException ex) {
            ex.printStackTrace(); // Handle any exceptions or errors
        }






        text_label = new JLabel("Explore");
        text_label.setFont(new Font(Font.SERIF,Font.BOLD, 30));
        text_label.setForeground(new Color(0, 26, 195));
        text_label.setBounds(380, 150, 300, 28);
        text_label.setHorizontalAlignment(JLabel.CENTER);
        middlePanel.add(text_label);




        b1 = new JButton("Art");
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("project/icons/img_3.png"));
        Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Adjust the width and height as needed
        ImageIcon resizedIcon = new ImageIcon(image);
        b1.setIcon(resizedIcon);
        b1.setBounds(80,210,130,90);
        b1.setHorizontalTextPosition(JButton.CENTER);
        b1.setVerticalTextPosition(JButton.BOTTOM);
        b1.addActionListener((ActionListener) this);
        middlePanel.add(b1);

        b2 = new JButton("Computer Science");
        icon = new ImageIcon(ClassLoader.getSystemResource("project/icons/img_1.png"));
        image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Adjust the width and height as needed
        resizedIcon = new ImageIcon(image);
        b2.setIcon(resizedIcon);
        b2.setBounds(220,210,130,90);
        b2.setHorizontalTextPosition(JButton.CENTER);
        b2.setVerticalTextPosition(JButton.BOTTOM);
        b2.addActionListener((ActionListener) this);
        middlePanel.add(b2);

        b3 = new JButton("Finance");
        icon = new ImageIcon(ClassLoader.getSystemResource("project/icons/img_4.png"));
        image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Adjust the width and height as needed
        resizedIcon = new ImageIcon(image);
        b3.setIcon(resizedIcon);
        b3.setBounds(360,210,130,90);
        b3.setHorizontalTextPosition(JButton.CENTER);
        b3.setVerticalTextPosition(JButton.BOTTOM);
        b3.addActionListener((ActionListener) this);
        middlePanel.add(b3);

        b4 = new JButton("Academics");
        icon = new ImageIcon(ClassLoader.getSystemResource("project/icons/img_12.png"));
        image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Adjust the width and height as needed
        resizedIcon = new ImageIcon(image);
        b4.setIcon(resizedIcon);
        b4.setBounds(500,210,130,90);
        b4.setHorizontalTextPosition(JButton.CENTER);
        b4.setVerticalTextPosition(JButton.BOTTOM);
        b4.addActionListener((ActionListener) this);
        middlePanel.add(b4);

        b5 = new JButton("Foreign language");
        icon = new ImageIcon(ClassLoader.getSystemResource("project/icons/img_6.png"));
        image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Adjust the width and height as needed
        resizedIcon = new ImageIcon(image);
        b5.setIcon(resizedIcon);
        b5.setBounds(640,210,130,90);
        b5.setHorizontalTextPosition(JButton.CENTER);
        b5.setVerticalTextPosition(JButton.BOTTOM);
        b5.addActionListener((ActionListener) this);
        middlePanel.add(b5);

        b6 = new JButton("Psychology");
        icon = new ImageIcon(ClassLoader.getSystemResource("project/icons/img_7.png"));
        image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Adjust the width and height as needed
        resizedIcon = new ImageIcon(image);
        b6.setIcon(resizedIcon);
        b6.setBounds(780,210,130,90);
        b6.setHorizontalTextPosition(JButton.CENTER);
        b6.setVerticalTextPosition(JButton.BOTTOM);
        b6.addActionListener((ActionListener) this);
        middlePanel.add(b6);

        text_label_1 = new JLabel("Now Available online");
        text_label_1.setFont(new Font(Font.SERIF,Font.BOLD, 30));
        text_label_1.setForeground(new Color(0, 26, 195));
        text_label_1.setBounds(380, 360, 300, 28);
        text_label_1.setHorizontalAlignment(JLabel.CENTER);
        middlePanel.add(text_label_1);

        b7 = new JButton("Investment Risk Management");
        icon = new ImageIcon(ClassLoader.getSystemResource("project/icons/img_8.png"));
        image = icon.getImage().getScaledInstance(150, 80, Image.SCALE_SMOOTH); // Adjust the width and height as needed
        resizedIcon = new ImageIcon(image);
        b7.setIcon(resizedIcon);
        b7.setBounds(80,400,200,180);
        b7.setHorizontalTextPosition(JButton.CENTER);
        b7.setVerticalTextPosition(JButton.BOTTOM);
        b7.addActionListener((ActionListener) this);
        middlePanel.add(b7);

        b8 = new JButton("Google Cybersecurity");
        icon = new ImageIcon(ClassLoader.getSystemResource("project/icons/img_9.png"));
        image = icon.getImage().getScaledInstance(150, 80, Image.SCALE_SMOOTH); // Adjust the width and height as needed
        resizedIcon = new ImageIcon(image);
        b8.setIcon(resizedIcon);
        b8.setBounds(300,400,200,180);
        b8.setHorizontalTextPosition(JButton.CENTER);
        b8.setVerticalTextPosition(JButton.BOTTOM);
        b8.addActionListener((ActionListener) this);
        middlePanel.add(b8);

        b9 = new JButton("Mathematics for machine learning and data science");
        icon = new ImageIcon(ClassLoader.getSystemResource("project/icons/img_10.png"));
        image = icon.getImage().getScaledInstance(150, 80, Image.SCALE_SMOOTH); // Adjust the width and height as needed
        resizedIcon = new ImageIcon(image);
        b9.setIcon(resizedIcon);
        b9.setBounds(520,400,200,180);
        b9.setHorizontalTextPosition(JButton.CENTER);
        b9.setVerticalTextPosition(JButton.BOTTOM);
        b9.addActionListener((ActionListener) this);
        middlePanel.add(b9);

        b10 = new JButton("Prompt engineering for chatgpt");
        icon = new ImageIcon(ClassLoader.getSystemResource("project/icons/img_11.png"));
        image = icon.getImage().getScaledInstance(150, 80, Image.SCALE_SMOOTH); // Adjust the width and height as needed
        resizedIcon = new ImageIcon(image);
        b10.setIcon(resizedIcon);
        b10.setBounds(740,400,200,180);
        b10.setHorizontalTextPosition(JButton.CENTER);
        b10.setVerticalTextPosition(JButton.BOTTOM);
        b10.addActionListener((ActionListener) this);
        middlePanel.add(b10);



































        enrollBtn = new JButton("Enroll");
        enrollBtn.setFont(new Font(Font.SERIF,Font.BOLD, 15));
        enrollBtn.setHorizontalAlignment(JButton.CENTER);
        enrollBtn.addActionListener(this);
        add(enrollBtn, BorderLayout.SOUTH);


        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        setVisible(true);
    }


    @Override

    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == b1) {
            new Art();
        } else if (ae.getSource() == b4) {
            new Academics();

        } else if (ae.getSource() == searchButton) {
            String className = searchField.getText();

            // Query the database for the class information using className
            // Display the search results or update your UI accordingly
            // Replace the following example code with your database query and result display logic
            try {
                String query = "SELECT * FROM sub_courses WHERE courseName = ?";
                PreparedStatement preparedStatement = c1.c.prepareStatement(query);
                preparedStatement.setString(1, className);
                ResultSet resultSet = preparedStatement.executeQuery();
                DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Course ID", "Course Name", "Location ID", "Ratings", "Teacher ID", "Price"}, 0);
                JTable table = new JTable(tableModel);
                courseDomainIds = new ArrayList();

                // Process and display the results
                while (resultSet.next()) {
                    String courseId = resultSet.getString("courseId");
                    String courseName = resultSet.getString("courseName");
                    String locationId = resultSet.getString("locationId");
                    String ratings = resultSet.getString("ratings");
                    String teacherID = resultSet.getString("teacherID");
                    String Price = resultSet.getString("Price");
                    Object[] rowData = {courseId, courseName, locationId, ratings, teacherID, Price};
                    tableModel.addRow(rowData);
                }

                // Close the resources (preparedStatement and resultSet)
                preparedStatement.close();
                resultSet.close();
                // Create a JFrame to display the panel
                JFrame frame = new JFrame("Art Classes");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.add(new JScrollPane(table));
                frame.setSize(800, 600);


                frame.setVisible(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } else if(ae.getSource() == enrollBtn){
            String courseNameStr = coursesCb.getSelectedItem().toString();
            java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
            try{
                String q1  = "Select course_ID From Courses Where Name = '"+ courseNameStr +"'";
                ResultSet rs = c1.s.executeQuery(q1);
                int course_ID;
                rs.next();
                course_ID = rs.getInt("course_ID");

                String q = "INSERT INTO Enrollments (Enrollment_Date, course_ID, stdID) "
                        + "Values ('" + sqlDate +"', '" + course_ID +"', '" + StudentLogin.currentStudentID +"')";

                int x = c1.s.executeUpdate(q);
                if(x == 0){
                    JOptionPane.showMessageDialog(null, "Some Error Occured");
                }else{
                    JOptionPane.showMessageDialog(null, "Course Enrollement Successfull!");
                    dispose();
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "You are Already Enrolled in this Course!");
                e.printStackTrace();
            }
        } else if (ae.getSource() == searchButton1) {
            String selectedSubject = (String) subjectsCb.getSelectedItem();
            if(selectedSubject != null){
                try {
                    String query = "SELECT c.courseDomainName, c1.courseName FROM courses c INNER JOIN sub_courses c1 ON c.courseDomainId = c1.courseDomainId WHERE courseDomainName = ?";
                    PreparedStatement preparedStatement = c1.c.prepareStatement(query);
                    preparedStatement.setString(1, selectedSubject);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Domain Name", "Class Name"},0);
                    JTable table = new JTable(tableModel);



                    while (resultSet.next()) {
                        String classDomainName = resultSet.getString("courseDomainName");
                        String className = resultSet.getString("courseName");
                        Object[] rowData = {classDomainName, className};
                        tableModel.addRow(rowData);

                    }

                    // Close the resources (preparedStatement and resultSet)
                    preparedStatement.close();
                    resultSet.close();
                    JFrame frame1 = new JFrame("Classes");
                    frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    // Add the JTable to the panel with a JScrollPane
                   frame1.add(table);
                   frame1.setVisible(true);
                   frame1.setSize(500,700);

                } catch (SQLException ex) {
                    ex.printStackTrace(); // Handle any exceptions or errors
                }
            }



        } else if(ae.getSource() == backBtn){
            dispose();
            new Student();

        }
    }
    public static void main(String[] args) {
        new EnrollCourse();
    }

}
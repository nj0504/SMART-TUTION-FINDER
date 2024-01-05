package project.Student;

import project.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Art extends JFrame implements ActionListener{

    DBConnection connection = new DBConnection();

    JLabel title, jLabel4;
    JTextField searchField;
    JButton searchButton;
    JButton[] buttons = new JButton[5];
    JPanel middlePanel, sidePanel;
    JTable table;
    DefaultTableModel tableModel;
    JComboBox locationFilter;

    JLabel l1;
    // Create a list to hold the buttons
    ArrayList<JButton> courseButtons = new ArrayList<JButton>();
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,25,20));



    public Art(){
        super("Art");
        setLayout(new BorderLayout());
        setSize(1280,720);
        setLocation(35,30);

        title = new JLabel("Art", JLabel.CENTER);
        title.setFont(title.getFont().deriveFont (22.0f));
        title.setBackground(Color.LIGHT_GRAY);
        title.setForeground(Color.BLACK);
        title.setOpaque(true);
        add(title, BorderLayout.NORTH);

        middlePanel = new JPanel();
        middlePanel.setLayout(new GridLayout(0,1));
        add(middlePanel, BorderLayout.CENTER);




        sidePanel = new JPanel();
        sidePanel.setLayout(null);
        sidePanel.setBackground(new Color(0, 26, 195));
        Dimension sidePanelSize = new Dimension(180, 720);
        sidePanel.setPreferredSize(sidePanelSize);
        add(sidePanel, BorderLayout.WEST);

        locationFilter = new JComboBox<>(new String[]{"All Locations", "Kothrud", "Kataraj", "Baner", "Bibewadi", "Shivaginagar"});
        locationFilter.setBounds(30, 150, 120, 28);
        sidePanel.add(locationFilter);

        searchButton = new JButton("Search");
        searchButton.setBounds(30, 300, 120, 28);
        searchButton.setBackground(new Color(246, 246, 246));
        searchButton.addActionListener((ActionListener) this);
        sidePanel.add(searchButton);


        // Load your icon image files
        ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("project/icons/img_13.png"));
        Image image = icon1.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Adjust the width and height as needed
        ImageIcon resizedIcon = new ImageIcon(image);
        ImageIcon icon2 = new ImageIcon(ClassLoader.getSystemResource("project/icons/img_14.png"));
        image = icon2.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Adjust the width and height as needed
        ImageIcon resizedIcon1 = new ImageIcon(image);







        try {


            // Execute an SQL query to retrieve course names
            ResultSet resultSet = connection.s.executeQuery("SELECT * FROM sub_courses WHERE courseDomainId = 1");





            boolean useFirstImage = true;



            // Iterate through the result set and create buttons
            while (resultSet.next()) {
                String courseName = resultSet.getString("courseName");
                JButton courseButton = new JButton(courseName);
                courseButton.setHorizontalTextPosition(JButton.CENTER);
                courseButton.setVerticalTextPosition(JButton.BOTTOM);
                courseButton.addActionListener((ActionListener) this);





                // Set the icon for the button
                // Set the icon for the button based on the flag
                if (useFirstImage) {
                    courseButton.setIcon(resizedIcon);
                } else {
                    courseButton.setIcon(resizedIcon1);
                }

                // Toggle the flag for the next button
                useFirstImage = !useFirstImage;

                courseButtons.add(courseButton);
            }

            // Close the database resources
            resultSet.close();


            // Add the buttons to the panel
            for (JButton button : courseButtons) {
                buttonPanel.add(button);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Add the panel with buttons to the frame
        middlePanel.add(buttonPanel);

        jLabel4 = new JLabel("Course name: ");







        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        setVisible(true);















    }



    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == searchButton){
            // Clear existing buttons from the buttonPanel
            buttonPanel.removeAll();
            buttonPanel.revalidate();
            buttonPanel.repaint();

            // Clear the list of buttons
            courseButtons.clear();

            try {
                // Load your icon image files
                ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("project/icons/img_13.png"));
                Image image = icon1.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Adjust the width and height as needed
                ImageIcon resizedIcon = new ImageIcon(image);
                ImageIcon icon2 = new ImageIcon(ClassLoader.getSystemResource("project/icons/img_14.png"));
                image = icon2.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Adjust the width and height as needed
                ImageIcon resizedIcon1 = new ImageIcon(image);
                // Create a list to hold the buttons

                boolean useFirstImage = true;

                // Get selected filter values
                String selectedLocation = (String) locationFilter.getSelectedItem();

                // Build a SQL query based on the selected filters
                String query = "SELECT * FROM sub_courses WHERE courseDomainId = 1";

                if (!selectedLocation.equals("All Locations")) {
                    query += " AND locationId IN (SELECT locationId FROM location WHERE locationName = '" + selectedLocation + "')";
                }





                // Execute the SQL query and update the JTable with the filtered data
                ResultSet resultSet = connection.s.executeQuery(query);


                while (resultSet.next()) {
                    String courseName = resultSet.getString("courseName");
                    JButton courseButton = new JButton(courseName);
                    courseButton.setHorizontalTextPosition(JButton.CENTER);
                    courseButton.setVerticalTextPosition(JButton.BOTTOM);
                    courseButton.addActionListener((ActionListener) this);

                    if (useFirstImage) {
                        courseButton.setIcon(resizedIcon);
                    } else {
                        courseButton.setIcon(resizedIcon1);
                    }
                    courseButtons.add(courseButton);
                    // Toggle the flag for the next button
                    useFirstImage = !useFirstImage;


                }

                resultSet.close();

                // Add the new buttons to the buttonPanel
                for (JButton button : courseButtons) {
                    buttonPanel.add(button);
                }
            } catch (SQLException e) {
                System.out.println("Error");
                e.printStackTrace();
            }

        }
            else if (ae.getSource() == courseButtons.get(0)) {
                new Art2().setVisible(true);

        } else if (ae.getSource() == courseButtons.get(1)) {
                new Rangprarambha().setVisible(true);


        } else if (ae.getSource() == courseButtons.get(2)) {
                new MilindArtStudio().setVisible(true);

        }


    }
   public String getJLabel4() {
        return jLabel4.getText();

    }


}

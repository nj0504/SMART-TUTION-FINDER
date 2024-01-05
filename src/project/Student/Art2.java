
package project.Student;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Art2 extends JFrame {

    JButton jButton1;
    JLabel jLabel1;
    JLabel jLabel2;
    JLabel jLabel3;
    JLabel jLabel4;
    JPanel jPanel1;

    public Art2() {
        initComponents();
    }

    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        JLabel jLabel5 = new JLabel("Aakash Classes");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        // Set the preferred size of jPanel1 to make it wider
        jPanel1.setPreferredSize(new Dimension(125, jPanel1.getPreferredSize().height));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 15));
        jLabel1.setText("Tutor name: Aakash Gupta");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 15));
        jLabel2.setText("Phone no.: 9685741352");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 15));
        jLabel3.setText("Course Domain: Art");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 15));
        jLabel4.setText("Courses providing: Drawing, Painting");

        jLabel5.setFont(new Font("Arial", Font.BOLD, 16));
        jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
        jPanel1.add(jLabel5, BorderLayout.NORTH);
        jLabel5.setPreferredSize(new Dimension(200, 50));
        jLabel5.setForeground(Color.GREEN);

        jButton1.setText("Book appointment");
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                bookAppointmentButtonActionPerformed(evt);
            }
        });

        // Set layout
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel2)
                                        .addComponent(jButton1)
                                        .addGap(0, 93, Short.MAX_VALUE)
                                )));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addGap(28, 28, 28)
                                .addComponent(jButton1)
                                .addContainerGap(62, Short.MAX_VALUE)
                        ));

        pack();
        setSize(500, 500);  // Set the size of the main frame to 500x500
    }

    private void bookAppointmentButtonActionPerformed(ActionEvent evt) {
        // Create a new JFrame for booking an appointment
        JFrame bookingFrame = new JFrame("Book an Appointment");
        bookingFrame.setSize(400, 200);  // Set the size as per your requirements

        // You can add components and logic for booking an appointment in this new frame.
        JLabel appointmentLabel = new JLabel("Enter your appointment details:");
        JTextField nameField = new JTextField(10); // Smaller text field
        JTextField dateField = new JTextField(10); // Smaller text field
        JButton submitButton = new JButton("Submit");

        // Create JLabels for "Name" and "Date"
        JLabel nameLabel = new JLabel("Name:");
        JLabel dateLabel = new JLabel("Date:");

        // Set positions and sizes for components
        appointmentLabel.setBounds(20, 20, 200, 20);
        nameLabel.setBounds(20, 50, 50, 20);
        dateLabel.setBounds(20, 80, 50, 20);
        nameField.setBounds(80, 50, 200, 30);
        dateField.setBounds(80, 80, 200, 28);
        submitButton.setBounds(20, 120, 100, 28);

        JPanel bookingPanel = new JPanel(null); // Use null layout for absolute positioning
        bookingPanel.add(appointmentLabel);
        bookingPanel.add(nameLabel);
        bookingPanel.add(dateLabel);
        bookingPanel.add(nameField);
        bookingPanel.add(dateField);
        bookingPanel.add(submitButton);

        bookingFrame.add(bookingPanel);
        bookingFrame.setVisible(true);

        // Add an action listener to the Submit button
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Perform booking logic here (e.g., save to a database)

                // Show a pop-up message to indicate that the appointment is booked
                JOptionPane.showMessageDialog(null, "Appointment booked successfully!");
            }
        });
    }




}

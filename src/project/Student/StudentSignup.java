package project.Student;

import project.DBConnection;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentSignup extends JFrame implements ActionListener, FocusListener{
    JPanel contentPane;
    JTextField firstname, lastname, email, username;
    JPasswordField passwordField;
    JButton registerButton;
    JRadioButton maleRB, femaleRB;
    ButtonGroup radioBtns;
    JLabel fnameValidation, LnameValidation, emailValidation, userNameValidation, passwordValidation;

    public StudentSignup() {
        super("Student SignUp");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1014,515);
        setLocation(230,110);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);




        JLabel lblName = new JLabel("First name");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblName.setBounds(58, 152, 99, 43);
        contentPane.add(lblName);

        JLabel lblNewLabel = new JLabel("Last name");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setBounds(58, 243, 110, 29);
        contentPane.add(lblNewLabel);

        JLabel lblEmailAddress = new JLabel("Email\r\n address");
        lblEmailAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblEmailAddress.setBounds(58, 324, 124, 36);
        contentPane.add(lblEmailAddress);

        fnameValidation = new JLabel();
        firstname = new JTextField();
        firstname.setFont(new Font("Tahoma", Font.PLAIN, 32));
        firstname.setBounds(214, 151, 228, 50);
        firstname.addFocusListener(this);
        fnameValidation.setBounds(214, 205, 150, 10);
        contentPane.add(fnameValidation);
        contentPane.add(firstname);
        firstname.setColumns(10);

        LnameValidation = new JLabel();
        lastname = new JTextField();
        lastname.setFont(new Font("Tahoma", Font.PLAIN, 32));
        lastname.setBounds(214, 235, 228, 50);
        lastname.addFocusListener(this);
        LnameValidation.setBounds(214, 289, 150, 10);
        contentPane.add(LnameValidation);
        contentPane.add(lastname);
        lastname.setColumns(10);

        emailValidation = new JLabel();
        email = new JTextField();
        email.setFont(new Font("Tahoma", Font.PLAIN, 32));
        email.setBounds(214, 320, 228, 50);
        email.addFocusListener(this);
        emailValidation.setBounds(214, 374, 150, 10);
        contentPane.add(emailValidation);
        contentPane.add(email);
        email.setColumns(10);

        userNameValidation = new JLabel();
        username = new JTextField();
        username.setFont(new Font("Tahoma", Font.PLAIN, 32));
        username.setBounds(707, 151, 228, 50);
        username.addFocusListener(this);
        userNameValidation.setBounds(707, 205, 150, 10);
        contentPane.add(userNameValidation);
        contentPane.add(username);
        username.setColumns(10);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblUsername.setBounds(542, 159, 99, 29);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblPassword.setBounds(542, 245, 99, 24);
        contentPane.add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        passwordField.setBounds(707, 235, 228, 50);
        contentPane.add(passwordField);

        JLabel genderLbl = new JLabel("Gender");
        genderLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
        genderLbl.setBounds(542, 331, 99, 24);
        contentPane.add(genderLbl);

        maleRB = new JRadioButton("Male");
        femaleRB = new JRadioButton("Female");
        radioBtns = new ButtonGroup();

        maleRB.setFont(new Font("Tahoma", Font.PLAIN, 25));
        maleRB.setBounds(707, 321, 110, 50);
        maleRB.setActionCommand("Male");
        contentPane.add(maleRB);

        femaleRB.setFont(new Font("Tahoma", Font.PLAIN, 25));
        femaleRB.setBounds(825, 321, 120, 50);
        femaleRB.setActionCommand("Female");
        contentPane.add(femaleRB);

        radioBtns.add(maleRB);
        radioBtns.add(femaleRB);

        registerButton = new JButton("Register");
        registerButton.setBounds(410, 400, 228, 60);
        registerButton.addActionListener((ActionListener) this);
        contentPane.add(registerButton);

        setVisible(true);
    }
    // This code use to resize image to fit lable

    @Override
    public void actionPerformed(ActionEvent ae) {
        String firstName = firstname.getText();
        String lastName = lastname.getText();
        String emailId = email.getText();
        String userName = username.getText();
        String password = String.valueOf(passwordField.getPassword());
        java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
        String msg = "" + firstName;
        String genderStr = "";
        if (radioBtns.getSelection() != null)
            genderStr = radioBtns.getSelection().getActionCommand();

        if (fnameValidation.getText().isEmpty() &&
                LnameValidation.getText().isEmpty() &&
                emailValidation.getText().isEmpty() &&
                userNameValidation.getText().isEmpty()) {
            if (firstName.isEmpty() || lastName.isEmpty() || emailId.isEmpty() || userName.isEmpty() || password.isEmpty()
                    || genderStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Fill All Fields !");
            } else {
                try {
                    DBConnection c1 = new DBConnection();

                    PreparedStatement ps = c1.c.prepareStatement("Insert into Student (fname, lname, Email_ID, username, password, Registration_Date, Gender) "
                            + "values(?,?,?,?,?,?,?)");
                    ps.setString(1, firstName);
                    ps.setString(2, lastName);
                    ps.setString(3, emailId);
                    ps.setString(4, userName);
                    ps.setString(5, password);
                    ps.setDate(6, sqlDate);
                    ps.setString(7, genderStr);
                    int x = ps.executeUpdate();
                    if (x == 0) {
                        JOptionPane.showMessageDialog(null, "This user already exists");
                    } else {
                        JOptionPane.showMessageDialog(null, "Welcome, " + msg + " Your account is successfully created."
                                + "You can now log into your Account.");
                        setVisible(false);
                        new StudentLogin();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please Fill accurate Info !");
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(e.getSource() == firstname)
            fnameValidation.setText("");
        else if(e.getSource() == lastname)
            LnameValidation.setText("");
        else if(e.getSource() == email)
            emailValidation.setText("");
        else if(e.getSource() == username)
            userNameValidation.setText("");
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(e.getSource() == firstname){
            String fName = firstname.getText();
            if(fName.isEmpty()){
                fnameValidation.setText("Enter First Name");
            }
            else{
                boolean valid = fName.matches("[A-Z][a-z]*");
                if(!valid)
                    fnameValidation.setText("Invalid First Name");
                else
                    fnameValidation.setText("");
            }
        }
        else if(e.getSource() == lastname){
            String LName = lastname.getText();
            if(LName.isEmpty()){
                LnameValidation.setText("Enter Last Name");
            }
            else{
                boolean valid = LName.matches("[A-Z][a-z]*");
                if(!valid)
                    LnameValidation.setText("Invalid Last Name");
                else
                    LnameValidation.setText("");
            }
        }
        else if(e.getSource() == email){
            String emailTxt = email.getText();
            if(emailTxt.isEmpty()){
                emailValidation.setText("Enter Email");
            }
            else{
                boolean valid = emailTxt.matches("[\\w]+@[\\w]+\\.[a-zA-Z]{2,3}");
                if(!valid)
                    emailValidation.setText("Invalid Email");
                else
                    emailValidation.setText("");
            }
        }
        else if(e.getSource() == username){
            String usernameTxt = username.getText();
            if(usernameTxt.isEmpty()){
                userNameValidation.setText("Enter UserName");
            }
            else{
                boolean valid = usernameTxt.matches("\\b[a-zA-Z][a-zA-Z0-9\\-._]{3,}\\b");
                if(!valid)
                    userNameValidation.setText("Invalid UserName");
                else
                    userNameValidation.setText("");
            }
        }
    }
    public static void main(String[] args) {
        new StudentSignup();
    }
}

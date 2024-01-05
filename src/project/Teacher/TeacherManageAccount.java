package project.Teacher;


import project.DBConnection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TeacherManageAccount extends JFrame implements ActionListener{
    JPanel panel;
    JLabel title;
    JButton b1, b2, b3, b4;
    FileInputStream fis = null;
    File f = null;
    public TeacherManageAccount(){
        super("Manage Teacher Account");
        
        panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        title = new JLabel("<html><h1><strong>Manage Account</strong></h1></html>");
        panel.add(title, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel buttons = new JPanel(new GridBagLayout());
        b1 = new JButton("Change Password");
        b1.addActionListener((ActionListener) this);
        b2 = new JButton("Change Name");
        b2.addActionListener((ActionListener) this);
        b3 = new JButton("Change Email");
        b3.addActionListener((ActionListener) this);

        b4 = new JButton("Exit");
        b4.addActionListener((ActionListener) this);
        buttons.add(b1, gbc);
        buttons.add(b2, gbc);
        buttons.add(b3, gbc);
        buttons.add(b4, gbc);

        gbc.weighty = 1;
        panel.add(buttons, gbc);
        
        add(panel);
        
        setSize(420,320);
        setLocation(460,280);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1){
            new TeacherChangePassword();
        }
        else if(ae.getSource() == b2){
            new TeacherChangeName();
        }
        else if(ae.getSource() == b3){
            new TeacherChangeEmail();
        }

        else if(ae.getSource() == b4){
            dispose();
        }
    }
    public static void main(String[] args) {
        new TeacherManageAccount();
    }
}

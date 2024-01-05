package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class AboutProject extends JFrame{
    JTextArea aboutText;
    JLabel title;
    public AboutProject() {
        super("About Project");
        setSize(540,380);
        setLocation(430,280);
        setLayout(new BorderLayout());

        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("project/icons//systemIcon.png"));
        setIconImage(icon.getImage());

        title = new JLabel("E-Learning System", JLabel.CENTER);
        title.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        title.setBackground(Color.LIGHT_GRAY);
        title.setForeground(Color.BLUE);
        title.setOpaque(true);
        add(title, BorderLayout.NORTH);


        String txt = "\t-->  The Tuition Finder is a user-friendly Java and SQL-based GUI application designed to simplify the process of locating suitable tuition classes and private tutors \n\n" +
                "\t-->  Traditional methods of finding educational support can be overwhelming and " +
                 "time-consuming, but the Tuition Finder streamlines this process by categorizing tutors and centers based on subjects, grades, and locations. .";

        aboutText = new JTextArea(txt);
        aboutText.setLineWrap(true);
        aboutText.setWrapStyleWord(true);
        aboutText.setFont(new Font(Font.DIALOG, Font.ITALIC, 19));
        aboutText.setForeground(Color.BLACK);
        aboutText.setBackground(new Color(173, 246,249));
        aboutText.setBorder(new LineBorder(Color.BLUE, 2, true));
        aboutText.setEditable(false);
        add(aboutText, BorderLayout.CENTER);

        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) {
        new AboutProject();
    }
}
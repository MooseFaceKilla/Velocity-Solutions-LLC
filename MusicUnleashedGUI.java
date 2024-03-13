package cse201;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MusicUnleashedGUI {
    private JFrame frame;
    private JPanel panel;
    private JLabel usernameLabel, passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public MusicUnleashedGUI() {
        frame = new JFrame("Music Unleashed Login");
        panel = new JPanel();
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                // Here you can add your authentication logic
                // For demonstration purpose, let's just print the credentials
                System.out.println("Username: " + username);
                System.out.println("Password: " + password);
            }
        });

        panel.setLayout(new GridLayout(3, 2));
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(loginButton);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MusicUnleashedGUI();
            }
        });
    }
}
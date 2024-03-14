import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class LoginGUI extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private Map<String, String> credentials;

    public LoginGUI() {
        setTitle("Login");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loadCredentials(); // Load credentials from file

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 24));
        JLabel passwordLabel = new JLabel("Password (Case Sensitive):");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 24));
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        loginButton.setFont(new Font("Arial", Font.BOLD, 24));
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(this);
        signUpButton.setFont(new Font("Arial", Font.BOLD, 24));

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // Blank label for spacing
        panel.add(loginButton);
        panel.add(new JLabel()); // Blank label for spacing
        panel.add(signUpButton);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (e.getActionCommand().equals("Login")) {
            if (authenticate(username, password)) {
                openMusicUnleashedScreen(username);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getActionCommand().equals("Sign Up")) {
            if (createAccount(username, password)) {
                JOptionPane.showMessageDialog(this, "Account created successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Username already exists!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean authenticate(String username, String password) {
        // Check if entered username exists (case-insensitive)
        for (String storedUsername : credentials.keySet()) {
            if (storedUsername.equalsIgnoreCase(username)) {
                // Check if entered password matches the stored password (case-sensitive)
                return credentials.get(storedUsername).equals(password);
            }
        }
        return false; // Username not found
    }


    private boolean createAccount(String username, String password) {
        if (!credentials.containsKey(username)) {
            credentials.put(username, password);
            saveCredentialsToFile();
            return true;
        }
        return false;
    }

    private void openMusicUnleashedScreen(String username) {
        JFrame musicFrame = new JFrame("Music Unleashed");
        musicFrame.setSize(600, 400);
        musicFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel welcomeLabel = new JLabel("Welcome, " + username, SwingConstants.LEFT);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        JLabel titleLabel = new JLabel("Music Unleashed", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));

        musicFrame.setLayout(new BorderLayout());
        musicFrame.add(welcomeLabel, BorderLayout.NORTH);
        musicFrame.add(titleLabel, BorderLayout.CENTER);

        musicFrame.setVisible(true);
    }

    private void loadCredentials() {
        credentials = new HashMap<>();
        try (Scanner scanner = new Scanner(new File("credentials.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.isEmpty()) { // Check if line is not empty
                    String[] parts = line.split(",");
                    if (parts.length >= 2) { // Check if there are at least two parts
                        credentials.put(parts[0], parts[1]);
                    } else {
                        System.err.println("Invalid line format: " + line);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading credentials!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveCredentialsToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("credentials.txt"))) {
            for (Map.Entry<String, String> entry : credentials.entrySet()) {
                writer.println(entry.getKey() + "," + entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving credentials!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginGUI();
            }
        });
    }
}

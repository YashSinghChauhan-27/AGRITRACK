/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agritrack;

/**
 *
 * @author yashc
 */
//import com.sun.jdi.connect.spi.Connection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignupGUI extends JFrame {
    Image img;
    JTextField nameField;
    JTextField emailField;
    JTextField phoneField;
    JTextField usernameField;
    JTextField securityquesField;
    JPasswordField passwordField;
    JPasswordField confirmPasswordField; // New field for Confirm Password

    public SignupGUI() {
       
        try {
            img = Toolkit.getDefaultToolkit().getImage("C:\\Users\\yashc\\OneDrive\\Pictures\\AOOP_PROJECT\\agritrack1.jpg");

        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception, log an error message, or display a message to the user.
        }

        // Create registration form components
        nameField = new JTextField(20);
        emailField = new JTextField(20);
        phoneField = new JTextField(10);
        usernameField = new JTextField(10);
        securityquesField = new JTextField(10);
        passwordField = new JPasswordField(10);
        confirmPasswordField = new JPasswordField(10); // Initialize Confirm Password field

        JButton registerButton = new JButton("Register");
        registerButton.setBackground(new Color(50,50,50));
        registerButton.setForeground(new Color(255, 255, 255));
        Font boldFont = new Font(registerButton.getFont().getName(), Font.BOLD, registerButton.getFont().getSize() + 2);
        registerButton.setFont(boldFont);
                registerButton.addActionListener(e -> {
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (password.equals(confirmPassword)) {
                if (establishDatabaseConnection()) {
                    JOptionPane.showMessageDialog(null, "Registration Successful!");
                    // Insert code here to save user data to the database
                    saveUserData();
                    LoginGUI loginpage = new LoginGUI();
                    loginpage.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Registration Failed. Please try again.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Passwords do not match. Please try again.");
            }
        });


        // Create layout for the registration form using GridBagLayout
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setPreferredSize(new Dimension(450, 590)); // Set preferred size
        formPanel.setBackground(new Color(100, 100, 100, 170));
        formPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Create constraints for centering components
        GridBagConstraints centerConstraints = new GridBagConstraints();
        centerConstraints.gridx = 0;
        centerConstraints.gridy = 0;
        centerConstraints.gridwidth = 2;
        centerConstraints.anchor = GridBagConstraints.CENTER;

        // Add the "SIGN UP" label in the center
        JLabel signUpLabel = new JLabel("SIGN UP");
        signUpLabel.setFont(new Font("Arial", Font.BOLD, 46));
        formPanel.add(signUpLabel, centerConstraints);
        signUpLabel.setForeground(new Color(250, 250, 250)); // Light green color

        // Add some vertical space between "SIGN UP" and "Name"
        centerConstraints.gridy++;
        formPanel.add(new JLabel(" "), centerConstraints); // An empty label for spacing

        // Add other components below the label with adjusted constraints
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setForeground(new Color(255, 255, 150)); // Light yellow color
        formPanel.add(nameLabel, constraints);
        constraints.gridy++;
        constraints.gridwidth = 2;
        formPanel.add(nameField, constraints);

        constraints.gridy++;
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setForeground(new Color(255, 255, 150)); // Light yellow color
        formPanel.add(emailLabel, constraints);
        constraints.gridy++;
        constraints.gridwidth = 2;
        formPanel.add(emailField, constraints);

        constraints.gridy++;
        JLabel phoneLabel = new JLabel("Phone");
        phoneLabel.setForeground(new Color(255, 255, 150)); // Light yellow color
        formPanel.add(phoneLabel, constraints);
        constraints.gridy++;
        constraints.gridwidth = 2;
        formPanel.add(phoneField, constraints);

        constraints.gridy++;
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setForeground(new Color(255, 255, 150)); // Light yellow color
        formPanel.add(usernameLabel, constraints);
        constraints.gridy++;
        constraints.gridwidth = 2;
        formPanel.add(usernameField, constraints);

        constraints.gridy++;
        JLabel sqLabel = new JLabel("Who is your favourite actor?");
        sqLabel.setForeground(new Color(255, 255, 150)); // Light yellow color
        formPanel.add(sqLabel, constraints);
        constraints.gridy++;
        constraints.gridwidth = 2;
        formPanel.add(securityquesField, constraints);
        
        constraints.gridy++;
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(new Color(255, 255, 150)); // Light yellow color
        formPanel.add(passwordLabel, constraints);
        constraints.gridy++;
        constraints.gridwidth = 2;
        formPanel.add(passwordField, constraints);

        constraints.gridy++;
        JLabel confirmpasswordLabel =new JLabel("Confirm Password");
        confirmpasswordLabel.setForeground(new Color(255, 255, 150)); // Light yellow color
        formPanel.add(confirmpasswordLabel, constraints);
        constraints.gridy++;
        constraints.gridwidth = 2;
        formPanel.add(confirmPasswordField, constraints);
        
        constraints.gridy++;
        constraints.gridwidth = 2;
        constraints.gridy++;
        formPanel.add(new JLabel(""), constraints);

        constraints.gridy += 2;
        formPanel.add(registerButton, constraints);

        // Add the "Already have an account? Log In" statement as two separate labels
        JLabel alreadyLabel = new JLabel("Already have an account?");
        JLabel loginLabel = new JLabel("Log In");

        alreadyLabel.setForeground(new Color(255, 255, 150)); // Light yellow color
        loginLabel.setForeground(new Color(255, 255, 150)); // Light yellow color

        alreadyLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)); // Default cursor
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Hand cursor

        constraints.gridy++;
        constraints.gridwidth = 1;
        formPanel.add(alreadyLabel, constraints);

        constraints.gridx++;
        formPanel.add(loginLabel, constraints);

        // Add a mouse listener to the "Log In" label to handle the click event
        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Handle the click event here to redirect to the login page
                // You can open a new JFrame for the login page or perform any other action
                //JOptionPane.showMessageDialog(null, "Redirecting to Log In Page");
                LoginGUI loginpage = new LoginGUI();
                loginpage.setVisible(true);
                dispose();
            }
        });

        // Create a main panel for the background image
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (img != null) {
                    g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        // Set the layout of the mainPanel to BorderLayout
        mainPanel.setLayout(new BorderLayout());

        // Create a nested panel with FlowLayout for centering the formPanel
        JPanel centeringPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 180));
        centeringPanel.setOpaque(false); // Make the nested panel transparent
        

        // Add the formPanel to the centeringPanel
        centeringPanel.add(formPanel);

        // Add the centeringPanel to the mainPanel in the CENTER position
        mainPanel.add(centeringPanel, BorderLayout.CENTER);
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
 
        // Set the mainPanel as the content pane
        setContentPane(mainPanel);

        setTitle("Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center the JFrame on the screen
        
        // Set the frame to maximize both horizontally and vertically
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
        private boolean establishDatabaseConnection() {
            
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String username = usernameField.getText();
        String securityQuestion = securityquesField.getText();
        String password = new String(passwordField.getPassword());

            
        String url = "jdbc:mysql://localhost:3306/agritrack"; // Update with your database URL
        String user = "root"; // Update with your database username
        String passwordDB = ""; // Update with your database password

        try {
            Connection connection = DriverManager.getConnection(url, user, passwordDB);
            String insertQuery = "INSERT INTO user_authentication (Name, Email, Phone, Username, SecurityQuestion, Password) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, username);
            preparedStatement.setString(5, securityQuestion);
            preparedStatement.setString(6, password);

            int rowsInserted = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    private void saveUserData() {
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String username = usernameField.getText();
        String securityQuestion = securityquesField.getText();
        String password = new String(passwordField.getPassword());

        String url = "jdbc:mysql://localhost:3306/agritrack";
        String user = "root";
        String passwordDB = "";

        try {
            Connection connection = DriverManager.getConnection(url, user, passwordDB);
            String insertQuery = "INSERT INTO user_authentication (Name, Email, Phone, Username, SecurityQuestion, Password) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, username);
            preparedStatement.setString(5, securityQuestion);
            preparedStatement.setString(6, password);

            int rowsInserted = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

            if (rowsInserted > 0) {
                // User data successfully saved to the database
                // You can add any additional logic here if needed
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SignupGUI().setVisible(true);
        });
    }

}


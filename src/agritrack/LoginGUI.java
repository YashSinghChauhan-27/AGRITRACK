/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agritrack;

/**
 *
 * @author yashc
 */
import java.sql.Statement;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginGUI extends JFrame {
    private Image img;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private String userEmail;

    public LoginGUI() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        try {
            img = Toolkit.getDefaultToolkit().getImage("C:\\Users\\yashc\\OneDrive\\Pictures\\AOOP_PROJECT\\agritrack1.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create login form components
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(10);

        // Create login button with a green background color and bold font
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(50, 50, 50)); // Set the background color to green
        loginButton.setForeground(Color.WHITE); // Set the text color to white
        Font boldFont = new Font(loginButton.getFont().getName(), Font.BOLD, loginButton.getFont().getSize() + 2);
        loginButton.setFont(boldFont); // Make the text a little bolder

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton1ActionPerformed(e);
            }
        });

        // Create layout for the login form using GridBagLayout
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setPreferredSize(new Dimension(400, 400));
        formPanel.setBackground(new Color(100, 100, 100, 170)); // Set the background color with alpha value (semi-transparent)
        formPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Create constraints for centering components
        GridBagConstraints centerConstraints = new GridBagConstraints();
        centerConstraints.gridx = 0;
        centerConstraints.gridy = 0;
        centerConstraints.gridwidth = 2;
        centerConstraints.anchor = GridBagConstraints.CENTER;
        centerConstraints.insets = new Insets(5, 27, 5, 5);

        // Add the "LOGIN" label in the center with green color and bolder font
        JLabel loginLabel = new JLabel("LOGIN");
        loginLabel.setFont(new Font("Arial", Font.BOLD, 46));
        loginLabel.setForeground(new Color(250, 250, 250)); // Set the text color to green
        formPanel.add(loginLabel, centerConstraints);

        // Add other components below the label with adjusted constraints
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 4;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        // Change the color of labels to light yellow
        JLabel usernameLabel = new JLabel("Username or Email:");
        usernameLabel.setForeground(new Color(255, 255, 150)); // Set the text color to light yellow
        formPanel.add(usernameLabel, constraints);
        constraints.gridy++;
        usernameField.setForeground(new Color(0, 0, 0)); // Set the text color of username field to light yellow
        formPanel.add(usernameField, constraints);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(new Color(255, 255, 150)); // Set the text color to light yellow
        constraints.gridy++;
        formPanel.add(passwordLabel, constraints);
        constraints.gridy++;
        passwordField.setForeground(new Color(0,0,0)); // Set the text color of password field to light yellow
        formPanel.add(passwordField, constraints);

        constraints.gridy++;
        constraints.gridwidth = 4;

        constraints.gridy++;
        JLabel forgetPasswordLabel = new JLabel("Forget your password?");
        forgetPasswordLabel.setForeground(new Color(255, 255, 150)); // Set the text color to light yellow
        forgetPasswordLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showForgotPasswordDialog();

            }
        });
        formPanel.add(forgetPasswordLabel, constraints);

        constraints.gridy++;
        formPanel.add(loginButton, constraints);

        constraints.gridy++;
        JLabel accLabel = new JLabel("Don't have an account?");
        JLabel signUpLabel = new JLabel("Sign up");
        signUpLabel.setForeground(new Color(255, 255, 150)); // Set the text color to light yellow
        accLabel.setForeground(new Color(255, 255, 150)); // Set the text color to light yellow
        constraints.gridy++;
        constraints.gridwidth = 4;
        formPanel.add(accLabel, constraints);
        constraints.insets = new Insets(5, 145, 5, 5); // Adjust the left inset to reduce the gap
        constraints.gridx++;
        formPanel.add(signUpLabel, constraints);
        signUpLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SignupGUI signuppage = new SignupGUI();
                signuppage.setVisible(true);
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
        JPanel centeringPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 220));
        centeringPanel.setOpaque(false);

        // Add the formPanel to the centeringPanel
        centeringPanel.add(formPanel);

        // Add the centeringPanel to the mainPanel in the EAST position
        mainPanel.add(centeringPanel, BorderLayout.CENTER);

        // Set the mainPanel as the content pane
        setContentPane(mainPanel);

        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        // Set the frame to maximize both horizontally and vertically
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void showForgotPasswordDialog() {
        JDialog forgotPasswordDialog = new JDialog(this, "Forgot Password", true);
        forgotPasswordDialog.setLayout(new GridLayout(4, 1));

        JTextField emailField = new JTextField(20);
        JTextField securityAnswerField = new JTextField(20);
        JButton verifyButton = new JButton("Verify");
        verifyButton.setForeground(new Color(250,250,250));
        verifyButton.setBackground(new Color(50,50,50));

        verifyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userEmail = emailField.getText();
                String SecurityQuestion = securityAnswerField.getText();
                if (verifyUser(userEmail, SecurityQuestion)) {
                    verifyButton.setEnabled(false);
                    JOptionPane.showMessageDialog(forgotPasswordDialog, "User verified. You can now reset your password.");
                    forgotPasswordDialog.setVisible(false);
                    showResetPasswordDialog();
                } else {
                    JOptionPane.showMessageDialog(forgotPasswordDialog, "Invalid email or security answer.");
                }
            }
        });
        
        forgotPasswordDialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel securityverify=new JLabel(" Security Verification");
        securityverify.setForeground(new Color(250,250,250));
        Font boldFont = new Font(securityverify.getFont().getName(), Font.BOLD, securityverify.getFont().getSize() + 7);
        securityverify.setFont(boldFont);
        forgotPasswordDialog.add(securityverify,gbc);
        gbc.gridy++;
        forgotPasswordDialog.add(new JLabel(" "),gbc);
        gbc.gridy++;
        forgotPasswordDialog.add(new JLabel(" "),gbc);
        gbc.gridy++;
        JLabel emaill=new JLabel("Enter your email:");
        emaill.setForeground(new Color(255, 255, 150));
        forgotPasswordDialog.add(emaill,gbc);
        gbc.gridy++;
        forgotPasswordDialog.add(emailField,gbc);
        
        gbc.gridy++;
        gbc.gridx--;
        JLabel actor=new JLabel("Who is your favourite actor?");
        actor.setForeground(new Color(255, 255, 150));
        forgotPasswordDialog.add(actor,gbc);
        gbc.gridy++;
        forgotPasswordDialog.add(securityAnswerField,gbc);
        
        gbc.gridy++;
        forgotPasswordDialog.add(new JLabel(" "),gbc);
        gbc.gridy++;
        forgotPasswordDialog.add(verifyButton,gbc);

        int width = 400;
        int height = 300;
        forgotPasswordDialog.setBounds((Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2, width, height);

        forgotPasswordDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                // Center the dialog after setting the bounds
                forgotPasswordDialog.setLocationRelativeTo(LoginGUI.this);
            }
        });
        forgotPasswordDialog.getContentPane().setBackground(new Color(100, 100, 100));
        forgotPasswordDialog.setVisible(true);
    }
    
    private void showResetPasswordDialog() {
        JDialog resetPasswordDialog = new JDialog(this, "Reset Password", true);
        resetPasswordDialog.setLayout(new GridLayout(4, 1));
        
        JPasswordField newPasswordField = new JPasswordField(20);
        JPasswordField confirmPasswordField = new JPasswordField(20);
        JButton resetButton = new JButton("Reset Password");
        resetButton.setForeground(new Color(250,250,250));
        resetButton.setBackground(new Color(50,50,50));

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                char[] newPassword = newPasswordField.getPassword();
                char[] confirmPassword = confirmPasswordField.getPassword();

                if (arePasswordsValid(newPassword, confirmPassword)) {
                    // Update the user's password in the database
                    boolean passwordChanged = updatePassword(userEmail, new String(newPassword));
                    if (passwordChanged) {
                        JOptionPane.showMessageDialog(resetPasswordDialog, "Password reset successfully.");
                        resetPasswordDialog.dispose();
                    } else {
                        JOptionPane.showMessageDialog(resetPasswordDialog, "Failed to reset password.");
                    }
                } else {
                    JOptionPane.showMessageDialog(resetPasswordDialog, "Passwords do not match.");
                }
            }
        });
        
        resetPasswordDialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        
        JLabel securityverify=new JLabel("    Reset Password");
        securityverify.setForeground(new Color(250,250,250));
        Font boldFont = new Font(securityverify.getFont().getName(), Font.BOLD, securityverify.getFont().getSize() + 7);
        securityverify.setFont(boldFont);
        resetPasswordDialog.add(securityverify,gbc);
        gbc.gridy++;
        resetPasswordDialog.add(new JLabel(" "),gbc);
        gbc.gridy++;
        resetPasswordDialog.add(new JLabel(" "),gbc);
        gbc.gridy++;
        JLabel pass=new JLabel("New Password");
        pass.setForeground(new Color(255, 255, 150));
        resetPasswordDialog.add(pass,gbc);
        gbc.gridy++;
        resetPasswordDialog.add(newPasswordField,gbc);
        
        gbc.gridy++;
        gbc.gridx--;
        JLabel confirm=new JLabel("Confirm Password");
        confirm.setForeground(new Color(255,255,150));
        resetPasswordDialog.add(confirm,gbc);
        gbc.gridy++;
        resetPasswordDialog.add(confirmPasswordField,gbc);
        
        gbc.gridy++;
        resetPasswordDialog.add(new JLabel(" "),gbc);
        gbc.gridy++;
        resetPasswordDialog.add(resetButton,gbc);

        int width = 400;
        int height = 300;
        resetPasswordDialog.setBounds((Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2, width, height);

        resetPasswordDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                // Center the dialog after setting the bounds
                resetPasswordDialog.setLocationRelativeTo(LoginGUI.this);
            }
        });
        resetPasswordDialog.getContentPane().setBackground(new Color(100, 100, 100));
        resetPasswordDialog.setVisible(true);
    }

    private boolean verifyUser(String Email, String SecurityQuestion) {
        userEmail = Email;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/agritrack", "root", "");

            String sql = "SELECT SecurityQuestion FROM user_authentication WHERE LOWER(Email) = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, Email.toLowerCase().trim());  // Convert to lowercase and trim whitespace

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String storedSecurityAnswer = resultSet.getString("SecurityQuestion");

                // Compare the stored security answer with the provided answer in a case-insensitive manner
                if (SecurityQuestion.equalsIgnoreCase(storedSecurityAnswer.trim())) {  // Trim and compare
                    con.close();
                    return true;  // Verification successful
                }
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;  // Verification failed
    }

    private boolean arePasswordsValid(char[] password1, char[] password2) {
        return password1.length!=0 && new String(password1).equals(new String(password2));
    }

    private boolean updatePassword(String email, String newPassword) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agritrack", "root", "");

            String query = "UPDATE user_authentication SET password = ? WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, email);

            int rowsUpdated = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            // If rowsUpdated is greater than 0, the password was updated successfully
            return rowsUpdated > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private void jButton1ActionPerformed(ActionEvent evt) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String un = usernameField.getText();
            String pw = new String(passwordField.getPassword());

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/agritrack", "root", "");
            Statement st = con.createStatement();
            String sql = "Select * from user_authentication";
            ResultSet rs = st.executeQuery(sql);
            boolean loginSuccessful = false;

            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");

                if (un.equals(username) && pw.equals(password)) {
                    loginSuccessful = true;
                    break;
                }
            }

            if (loginSuccessful) {
                // Login successful, open the dashboard or perform other actions
                new HomePage().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Username or Password is incorrect!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error while establishing connection failed!!!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginGUI().setVisible(true);
        });
    }
}
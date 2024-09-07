/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package agritrack;

/**
 *
 * @author yashc
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IntroPage extends JFrame {
    public IntroPage() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        // Set the frame properties
        setTitle("Intro Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the frame

        // Create a panel for the background image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("C:\\Users\\yashc\\OneDrive\\Pictures\\AOOP_PROJECT\\AGRITRACK.jpg");
                Image image = backgroundImage.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        // Create a panel for the links
        JPanel linkPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        linkPanel.setOpaque(false); // Make the link panel transparent
        linkPanel.setPreferredSize(new Dimension(800, 100));

        // Create the "Login" link with increased font size and bold
        JLabel loginLabel = new JLabel("Login");
        loginLabel.setForeground(new Color(250, 250, 250));
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Font loginFont = loginLabel.getFont();
        loginLabel.setFont(new Font(loginFont.getName(), Font.BOLD, loginFont.getSize() + 20)); // Increase font size and make it bold

        // Create the "Sign Up" link with increased font size and bold
        JLabel signUpLabel = new JLabel("Sign Up");
        signUpLabel.setForeground(new Color(250, 250, 250));
        signUpLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Font signUpFont = signUpLabel.getFont();
        signUpLabel.setFont(new Font(signUpFont.getName(), Font.BOLD, signUpFont.getSize() + 20)); // Increase font size and make it bold

        // Add mouse listeners to the "Login" and "Sign Up" labels
        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Handle the "Login" link click event (navigate to login page)
                // You can implement the navigation logic here
                //JOptionPane.showMessageDialog(null, "Redirecting to Login Page");
                LoginGUI loginpage = new LoginGUI();
                loginpage.setVisible(true);
                dispose();
            }
        });

        signUpLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Handle the "Sign Up" link click event (navigate to signup page)
                // You can implement the navigation logic here
                //JOptionPane.showMessageDialog(null, "Redirecting to Sign Up Page");
                SignupGUI signuppage = new SignupGUI();
                signuppage.setVisible(true);
                dispose();
            }
        });

        // Add the "Login" and "Sign Up" labels to the link panel
        linkPanel.add(loginLabel);
        linkPanel.add(signUpLabel);

        // Add the link panel to the background panel
        backgroundPanel.add(linkPanel, BorderLayout.SOUTH);

        // Add the background panel to the frame
        add(backgroundPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Timer timer = new Timer(1000, e -> {
                IntroPage introPage = new IntroPage();
                introPage.setVisible(true);
            });
            timer.setRepeats(false); // Only execute the timer once
            timer.start();
        });
    }
}

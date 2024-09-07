/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agritrack;

/**
 *
 * @author yashc
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

public class NewData extends JFrame {
    Image img;
    JTextField nameField;
    JTextField addressField;
    JTextField phoneField;
    JTextField adharField;
    JTextField itemField;
    JTextField itemamtField;
    JTextField dateofsubmissionField;

    public NewData() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        try {
            img = Toolkit.getDefaultToolkit().getImage("C:\\Users\\yashc\\OneDrive\\Pictures\\AOOP_PROJECT\\17.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }

        nameField = new JTextField(20);
        addressField = new JTextField(20);
        phoneField = new JTextField(10);
        adharField = new JTextField(10);
        itemField = new JTextField(10);
        itemamtField = new JTextField(10);
        dateofsubmissionField = new JTextField(10);

        JButton submitButton = new JButton("Submit");
        submitButton.setBackground(new Color(0, 150, 100));
        submitButton.setForeground(Color.WHITE);
        Font boldFont = new Font(submitButton.getFont().getName(), Font.BOLD, submitButton.getFont().getSize() + 2);
        submitButton.setFont(boldFont);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String address = addressField.getText();
                String phoneNumber = phoneField.getText();
                String aadharNumber = adharField.getText();
                String itemName = itemField.getText();
                String itemAmount = itemamtField.getText();

                // Convert the date string to a java.sql.Date
                java.sql.Date date = Date.valueOf(dateofsubmissionField.getText());

                // Database connectivity and insertion logic
                insertDataIntoDatabase(name, address, phoneNumber, aadharNumber, itemName, itemAmount, date);
            }
        });

        // Create a JPanel for the buttons
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backPanel.setOpaque(false);
        ImageIcon newIcon = new ImageIcon(new ImageIcon("C:\\Users\\yashc\\OneDrive\\Pictures\\AOOP_PROJECT\\pngwing.com.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        JLabel newLabel = new JLabel(newIcon);
        backPanel.add(newLabel);

        backPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                HomePage back = new HomePage();
                back.setVisible(true);
                dispose();
            }
        });

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setPreferredSize(new Dimension(450, 600));
        formPanel.setBackground(new Color(100, 100, 100, 170));
        formPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        GridBagConstraints centerConstraints = new GridBagConstraints();
        centerConstraints.gridx = 0;
        centerConstraints.gridy = 0;
        centerConstraints.gridwidth = 2;
        centerConstraints.anchor = GridBagConstraints.CENTER;

        JLabel newclientLabel = new JLabel("NEW DATA");
        newclientLabel.setFont(new Font("Arial", Font.BOLD, 46));
        formPanel.add(newclientLabel, centerConstraints);
        newclientLabel.setForeground(new Color(250, 250, 250));

        centerConstraints.gridy++;
        formPanel.add(new JLabel(" "), centerConstraints);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setForeground(new Color(255, 255, 150));
        formPanel.add(nameLabel, constraints);
        constraints.gridy++;
        constraints.gridwidth = 2;
        formPanel.add(nameField, constraints);

        constraints.gridy++;
        JLabel addressLabel = new JLabel("Address");
        addressLabel.setForeground(new Color(255, 255, 150));
        formPanel.add(addressLabel, constraints);
        constraints.gridy++;
        constraints.gridwidth = 2;
        formPanel.add(addressField, constraints);

        constraints.gridy++;
        JLabel phoneLabel = new JLabel("Phone Number");
        phoneLabel.setForeground(new Color(255, 255, 150));
        formPanel.add(phoneLabel, constraints);
        constraints.gridy++;
        constraints.gridwidth = 2;
        formPanel.add(phoneField, constraints);

        constraints.gridy++;
        JLabel adharLabel = new JLabel("Aadhar Number");
        adharLabel.setForeground(new Color(255, 255, 150));
        formPanel.add(adharLabel, constraints);
        constraints.gridy++;
        constraints.gridwidth = 2;
        formPanel.add(adharField, constraints);

        constraints.gridy++;
        JLabel itemLabel = new JLabel("Item Name");
        itemLabel.setForeground(new Color(255, 255, 150));
        formPanel.add(itemLabel, constraints);
        constraints.gridy++;
        constraints.gridwidth = 2;
        formPanel.add(itemField, constraints);

        constraints.gridy++;
        JLabel itemamtLabel = new JLabel("Item Amount (in Kg)");
        itemamtLabel.setForeground(new Color(255, 255, 150));
        formPanel.add(itemamtLabel, constraints);
        constraints.gridy++;
        constraints.gridwidth = 2;
        formPanel.add(itemamtField, constraints);

        constraints.gridy++;
        JLabel dateofsubmissionLabel = new JLabel("Date Of Submission (yyyy-mm-dd)");
        dateofsubmissionLabel.setForeground(new Color(255, 255, 150));
        formPanel.add(dateofsubmissionLabel, constraints);
        constraints.gridy++;
        constraints.gridwidth = 2;
        formPanel.add(dateofsubmissionField, constraints);

        constraints.gridy++;
        constraints.gridwidth = 2;
        constraints.gridy++;
        formPanel.add(new JLabel(""), constraints);

        constraints.gridy += 2;
        formPanel.add(submitButton, constraints);

        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (img != null) {
                    g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        mainPanel.setLayout(new BorderLayout());

        JPanel centeringPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 50));
        centeringPanel.setOpaque(false);

        centeringPanel.add(formPanel);
        mainPanel.add(centeringPanel, BorderLayout.CENTER);
        mainPanel.add(backPanel, BorderLayout.NORTH);

        setContentPane(mainPanel);
        setTitle("New Data");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void insertDataIntoDatabase(String name, String address, String phone, String adhar, String item, String itemamt, Date date) {
        // JDBC URL, username, and password of MySQL server
        String URL = "jdbc:mysql://localhost/agritrack";
        String USER = "root";
        String PASSWORD = "";

        // SQL query to insert data into the "client_data" table
        String insertQuery = "INSERT INTO `client_data`(`Name`, `Address`, `Phone`, `Adhar`, `Item Name`, `Item Amount`, `Date`) VALUES (?,?,?,?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, adhar);
            preparedStatement.setString(5, item);
            preparedStatement.setString(6, itemamt);
            preparedStatement.setDate(7, date);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Data inserted successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Data insertion failed.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Data insertion failed."+ e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new NewData().setVisible(true);
        });
    }
}
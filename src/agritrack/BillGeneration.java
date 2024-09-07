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
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BillGeneration extends JFrame {
    Image img;
    JTextField adharField;
    JTextField dateofsubmissionField;

    public BillGeneration() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        try {
            img = Toolkit.getDefaultToolkit().getImage("C:\\Users\\yashc\\OneDrive\\Pictures\\AOOP_PROJECT\\5.jpg");

        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception, log an error message, or display a message to the user.
        }

        // Create new data form components
        adharField = new JTextField(10);
        dateofsubmissionField = new JTextField(10); 

        JButton billButton = new JButton("Generate Bill");
        billButton.setBackground(new Color(0,150,100));
        billButton.setForeground(Color.WHITE);
        Font boldFont = new Font(billButton.getFont().getName(), Font.BOLD, billButton.getFont().getSize() + 2);
        billButton.setFont(boldFont);
        billButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String adharNumber = adharField.getText();
                String dateOfSubmissionText = dateofsubmissionField.getText();

                // Parse the dateOfSubmissionText with the "yyyy/MM/dd" format
                Date dateOfSubmission = null;
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    dateOfSubmission = dateFormat.parse(dateOfSubmissionText);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid date format. Please use yyyy/MM/dd format.");
                    return; // Exit the actionPerformed method
                }

                // Get the current date for bill generation
                Date billGenerationDate = new Date();
                
                // Create a SimpleDateFormat object with the desired format
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                // Format the Date object
                String formattedDate = sdf.format(billGenerationDate);


                // Fetch client data
                String[] clientData = fetchClientData(adharNumber, dateOfSubmission);
                String clientName = clientData[0];
                String clientAdhar = clientData[1];
                String clientDate = clientData[2];
                String itemName = clientData[3];
                double itemAmount = Double.parseDouble(clientData[4]);

                // Calculate the number of days
                long numberOfDays = calculateNumberOfDays(dateOfSubmission, billGenerationDate);

                // Calculate the total amount
                double totalAmount = calculateTotalAmount(itemName, itemAmount, numberOfDays);

                // Display the result
                String message = "Client Name: " + clientName + "\nAdhar No.:"+clientAdhar + "\nDate of submission:"+clientDate + "\nDate of retrieval:"+formattedDate + "\nItem Name: " + itemName + "\nTotal Amount: Rs " + totalAmount;
                JOptionPane.showMessageDialog(null, message);
            }
        });


        // Create a JPanel for the BACK button
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backPanel.setOpaque(false);
        ImageIcon newIcon = new ImageIcon(new ImageIcon("C:\\Users\\yashc\\OneDrive\\Pictures\\AOOP_PROJECT\\pngwing.com.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        JLabel newLabel = new JLabel(newIcon);
        backPanel.add(newLabel);
        
        backPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Handle the "Login" link click event (navigate to the login page)
                // You can implement the navigation logic here
                //JOptionPane.showMessageDialog(null, "Redirecting to Login Page");
                HomePage back = new HomePage();
                back.setVisible(true);
                dispose();
            }
        });
        
        // Create layout for the new data form using GridBagLayout
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setPreferredSize(new Dimension(450, 450)); // Set preferred size
        formPanel.setBackground(new Color(100, 100, 100, 170));
        formPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Create constraints for centering components
        GridBagConstraints centerConstraints = new GridBagConstraints();
        centerConstraints.gridx = 0;
        centerConstraints.gridy = 0;
        centerConstraints.gridwidth = 2;
        centerConstraints.anchor = GridBagConstraints.CENTER;

        // Add the "NEW DATA" label in the center
        JLabel newclientLabel = new JLabel("BILL");
        newclientLabel.setFont(new Font("Arial", Font.BOLD, 46));
        formPanel.add(newclientLabel, centerConstraints);
        newclientLabel.setForeground(new Color(250, 250, 250)); // white color

        centerConstraints.gridy++;
        JLabel generateLabel = new JLabel("GENERATION");
        generateLabel.setFont(new Font("Arial", Font.BOLD, 46));
        formPanel.add(generateLabel, centerConstraints);
        generateLabel.setForeground(new Color(250, 250, 250)); // Light green color
        
        // Add some vertical space between "NEW DATA" and "Name"
        centerConstraints.gridy++;
        formPanel.add(new JLabel(" "), centerConstraints); // An empty label for spacing

        // Add other components below the label with adjusted constraints
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        constraints.gridy++;
        JLabel adharLabel = new JLabel("Adhar Number");
        adharLabel.setForeground(new Color(255, 255, 150)); // Light yellow color
        formPanel.add(adharLabel, constraints);
        constraints.gridy++;
        constraints.gridwidth = 2;
        formPanel.add(adharField, constraints);
        
        constraints.gridy++;
        JLabel dateofsubmissionLabel = new JLabel("Date Of Submission (yyyy-mm-dd)");
        dateofsubmissionLabel.setForeground(new Color(255, 255, 150)); // Light yellow color
        formPanel.add(dateofsubmissionLabel, constraints);
        constraints.gridy++;
        constraints.gridwidth = 2;
        formPanel.add(dateofsubmissionField, constraints);
        
        constraints.gridy++;
        constraints.gridwidth = 2;
        constraints.gridy++;
        formPanel.add(new JLabel(""), constraints);

        constraints.gridy += 2;
        formPanel.add(billButton, constraints);

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
        JPanel centeringPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 150));
        centeringPanel.setOpaque(false); // Make the nested panel transparent
        

        // Add the formPanel to the centeringPanel
        centeringPanel.add(formPanel);

        // Add the centeringPanel to the mainPanel in the CENTER position
        mainPanel.add(centeringPanel, BorderLayout.CENTER);
        
        // Add the backLabel to the mainPanel in the Northeast position
        mainPanel.add(backPanel, BorderLayout.NORTH);

        // Set the mainPanel as the content pane
        setContentPane(mainPanel);

        setTitle("Bill Generation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center the JFrame on the screen
        
        // Set the frame to maximize both horizontally and vertically
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    // Method to fetch client data
    private String[] fetchClientData(String adharNumber, Date dateOfSubmission) {
        String[] clientData = new String[5];
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/agritrack", "root", "");
            String sql = "SELECT `Name`, `Adhar`, `Date`, `Item Name`, `Item Amount` FROM client_data WHERE `Adhar`=? AND `Date`=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, adharNumber);
            preparedStatement.setDate(2, new java.sql.Date(dateOfSubmission.getTime()));
            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                clientData[0] = result.getString("Name");
                clientData[1] = result.getString("Adhar");
                clientData[2] = result.getString("Date");
                clientData[3] = result.getString("Item Name");
                clientData[4] = result.getString("Item Amount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientData;
    }

    private long calculateNumberOfDays(Date dateOfSubmission, Date billGenerationDate) {
        long differenceInMilliseconds = billGenerationDate.getTime() - dateOfSubmission.getTime();
        return differenceInMilliseconds / (24 * 60 * 60 * 1000);
    }

    private double calculateTotalAmount(String itemName, double itemAmount, long numberOfDays) {
        double rentPerDay = calculateRentPerDay(itemName);
        double totalAmount = rentPerDay * itemAmount * numberOfDays;
        return totalAmount;
    }

    private double calculateRentPerDay(String itemName) {
        switch (itemName) {
            case "Potato":
                return 5;
            case "Tomato":
                return 7;
            case "Onion":
                return 6;
            case "Cauliflower":
                return 8;
            case "Cabbage":
                return 4;
            case "Carrot":
                return 9;
            case "Capsicum":
                return 10;
            default:
                return 0; // Invalid item name
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BillGeneration().setVisible(true);
        });
    }
}
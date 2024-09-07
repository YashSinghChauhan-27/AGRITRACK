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
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class HomePage extends JFrame {

    private DefaultTableModel tableModel;
    private JTable resultTable;

    public HomePage() {
        setTitle("AGRITRACK");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1500, 800);

        // Create a JPanel for the main content
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load and draw the background image
                ImageIcon background = new ImageIcon("C:\\Users\\yashc\\OneDrive\\Pictures\\AOOP_PROJECT\\16.jpg");
                Image image = background.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Create a JPanel for the search bar
        JPanel searchBarPanel = new JPanel();
        searchBarPanel.setOpaque(false);
        ImageIcon searchIcon = new ImageIcon(new ImageIcon("C:\\Users\\yashc\\OneDrive\\Pictures\\AOOP_PROJECT\\pngaaa.com-25409.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        JLabel searchIconLabel = new JLabel(searchIcon);
        searchBarPanel.add(searchIconLabel);
        JTextField searchField = new JTextField("Enter your Aadhar number to retrieve Data", 30);
        searchField.setFont(new Font("Arial", Font.ITALIC, 18));
        searchField.setBackground(new Color(150, 150, 150));
        searchField.setPreferredSize(new Dimension(searchField.getPreferredSize().width, 35));
        searchField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (searchField.getText().equals("Enter your Aadhar number to retrieve Data")) {
                    searchField.setText(""); // Clear the text when the field is clicked
                }
            }
        });
        searchBarPanel.add(searchField);

        // Create separate panels for "WELCOME," "TO," and "AGRITRACK"
        JPanel welcomePanel = new JPanel();
        welcomePanel.setOpaque(false);
        JLabel welcomeLabel = new JLabel("W E L C O M E");
        welcomeLabel.setFont(new Font("BankGothic Md BT", Font.PLAIN, 98));
        welcomeLabel.setForeground(Color.WHITE);
        welcomePanel.add(welcomeLabel);

        JPanel toPanel = new JPanel();
        toPanel.setOpaque(false);
        JLabel toLabel = new JLabel("T O");
        toLabel.setFont(new Font("BankGothic Md BT", Font.PLAIN, 98));
        toLabel.setForeground(Color.WHITE);
        toPanel.add(toLabel);

        JPanel agritrackPanel = new JPanel();
        agritrackPanel.setOpaque(false);
        JLabel agritrackLabel = new JLabel("A G R I T R A C K");
        agritrackLabel.setFont(new Font("BankGothic Md BT", Font.BOLD, 98));
        agritrackLabel.setForeground(Color.WHITE);
        agritrackPanel.add(agritrackLabel);

        JPanel desPanel = new JPanel();
        desPanel.setOpaque(false);
        JLabel desLabel = new JLabel("MANAGING YOUR HARVEST WITH EASE");
        desLabel.setFont(new Font("BankGothic Md BT", Font.PLAIN, 28));
        desLabel.setForeground(Color.WHITE);
        desPanel.add(desLabel);

        // Create empty labels for spacing
        JLabel emptyLabel1 = new JLabel("");
        emptyLabel1.setPreferredSize(new Dimension(100, 80)); // Set preferred size

        JLabel emptyLabel2 = new JLabel("");
        emptyLabel2.setPreferredSize(new Dimension(100, 140)); // Set preferred size

        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        logoutPanel.setOpaque(false);
        ImageIcon Icon = new ImageIcon(new ImageIcon("C:\\Users\\yashc\\OneDrive\\Pictures\\AOOP_PROJECT\\44.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        JLabel logoutLabel = new JLabel(Icon);
        logoutPanel.add(logoutLabel);

        // Create a JPanel for the buttons
        JPanel buttonPanel1 = new JPanel();
        buttonPanel1.setOpaque(false);
        ImageIcon newIcon = new ImageIcon(new ImageIcon("C:\\Users\\yashc\\OneDrive\\Pictures\\AOOP_PROJECT\\PngItem_1727979.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        JLabel newLabel = new JLabel(newIcon);
        buttonPanel1.add(newLabel);

        JPanel buttonPanel2 = new JPanel();
        buttonPanel2.setOpaque(false);
        ImageIcon updateIcon = new ImageIcon(new ImageIcon("C:\\Users\\yashc\\OneDrive\\Pictures\\AOOP_PROJECT\\—Pngtree—white laptop icon_4629581.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        JLabel updateLabel = new JLabel(updateIcon);
        buttonPanel2.add(updateLabel);

        JPanel buttonPanel3 =  new JPanel();
        buttonPanel3.setOpaque(false);
        ImageIcon displayIcon = new ImageIcon(new ImageIcon("C:\\Users\\yashc\\OneDrive\\Pictures\\AOOP_PROJECT\\data-viewer.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        JLabel displayLabel = new JLabel(displayIcon);
        buttonPanel3.add(displayLabel);
        
        JPanel buttonPanel4 =  new JPanel();
        buttonPanel4.setOpaque(false);
        ImageIcon billIcon = new ImageIcon(new ImageIcon("C:\\Users\\yashc\\OneDrive\\Pictures\\AOOP_PROJECT\\g5k4el1l7iagmsbcl9d35m52t0.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        JLabel billLabel = new JLabel(billIcon);
        buttonPanel4.add(billLabel);

        JPanel nPanel = new JPanel();
        nPanel.setOpaque(false);
        JLabel nLabel = new JLabel("NEW DATA");
        nLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        nLabel.setForeground(Color.WHITE);
        nPanel.add(nLabel);

        JPanel uPanel = new JPanel();
        uPanel.setOpaque(false);
        JLabel uLabel = new JLabel("UPDATE DATA");
        uLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        uLabel.setForeground(Color.WHITE);
        uPanel.add(uLabel);
        
        JPanel dPanel = new JPanel();
        dPanel.setOpaque(false);
        JLabel dLabel = new JLabel("DISPLAY DATA");
        dLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        dLabel.setForeground(Color.WHITE);
        dPanel.add(dLabel);

        JPanel bPanel = new JPanel();
        bPanel.setOpaque(false);
        JLabel bLabel = new JLabel("GENERATE BILL");
        bLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        bLabel.setForeground(Color.WHITE);
        bPanel.add(bLabel);

        // Add action listeners to the buttons (you can implement your logic here)
        logoutLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Add your logic for "Logout" action here
                IntroPage logout = new IntroPage();
                logout.setVisible(true);
                dispose();
            }
        });

        newLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Add your logic for "New Data" action here
                NewData newdatapage = new NewData();
                newdatapage.setVisible(true);
                dispose();
            }
        });

        updateLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Add your logic for "Update Data" action here
                UpdateData updatedatapage = new UpdateData();
                updatedatapage.setVisible(true);
                dispose();
            }
        });

        billLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Add your logic for "Bill Generation" action here
                BillGeneration billpage = new BillGeneration();
                billpage.setVisible(true);
                dispose();
            }
        });
        
        displayLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Add your logic for "display data" action here
                // Query the database and retrieve the data
                List<String> searchData = retrieveAllData();

                // Display the retrieved data in a new frame with a table
                displayDataInTable(searchData);
            }
        });

        // Add an action listener to the searchField
        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve the Aadhar number from the searchField
                String aadharNumber = searchField.getText();

                // Query the database and retrieve the data
                List<String> searchData = retrieveDataByAadharNumber(aadharNumber);

                // Display the retrieved data in a new frame with a table
                displayDataInTable(searchData);
            }
        });
        
        

        Insets logoutInsets = new Insets(0, -110, 0, 0); // Adjust the left inset
        Insets logout1Insets = new Insets(0, 0, 0, 0); // Adjust the left inset

        // Add components to the main panel using GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = logoutInsets;
        mainPanel.add(logoutPanel, gbc);

        gbc.insets = logout1Insets;
        gbc.gridx++;
        mainPanel.add(searchBarPanel, gbc);

        gbc.gridy = 1;
        mainPanel.add(emptyLabel1, gbc); // Add empty label for spacing

        gbc.gridy = 2;
        mainPanel.add(welcomePanel, gbc);

        gbc.gridy = 3;
        mainPanel.add(toPanel, gbc);

        gbc.gridy = 4;
        mainPanel.add(agritrackPanel, gbc);

        gbc.gridy = 5;
        mainPanel.add(desPanel, gbc);

        gbc.gridy = 6;
        mainPanel.add(emptyLabel2, gbc); // Add empty label for spacing

        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(buttonPanel1);
        buttonBox.add(Box.createHorizontalStrut(260)); // Add spacing between the panels
        buttonBox.add(buttonPanel2);
        buttonBox.add(Box.createHorizontalStrut(270)); // Add spacing between the panels
        buttonBox.add(buttonPanel3);
        buttonBox.add(Box.createHorizontalStrut(270)); // Add spacing between the panels
        buttonBox.add(buttonPanel4);

        gbc.gridx = 1;
        gbc.gridy = 7;
        mainPanel.add(buttonBox, gbc);

        Box buttonBox1 = Box.createHorizontalBox();
        buttonBox1.add(nPanel);
        buttonBox1.add(Box.createHorizontalStrut(270)); // Add spacing between the panels
        buttonBox1.add(uPanel);
        buttonBox1.add(Box.createHorizontalStrut(270)); // Add spacing between the panels
        buttonBox1.add(dPanel);
        buttonBox1.add(Box.createHorizontalStrut(260)); // Add spacing between the panels
        buttonBox1.add(bPanel);

        gbc.gridx = 1;
        gbc.gridy = 8;
        mainPanel.add(buttonBox1, gbc);

        add(mainPanel);
        setLocationRelativeTo(null); // Center the frame

        // Set the frame to maximize both horizontally and vertically
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Make the frame visible after all components are added and configurations are set
        setVisible(true);
    }

    private List<String> retrieveDataByAadharNumber(String aadharNumber) {
        String URL = "jdbc:mysql://localhost/agritrack";
        String USER = "root";
        String PASSWORD = "";

        String query = "SELECT * FROM client_data WHERE Adhar = ? ORDER BY Date DESC";

        List<String> searchData = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, aadharNumber);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Customize this format according to your needs
                String dataEntry = String.format(
                        "%s<%s<%s<%s<%s<%s<%s",
                        resultSet.getString("Name"),
                        resultSet.getString("Address"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Adhar"),
                        resultSet.getString("Item Name"),
                        resultSet.getString("Item Amount"),
                        resultSet.getDate("Date")
                );

                searchData.add(dataEntry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Data retrieval failed.");
        }

        return searchData;
    }
    
    private List<String> retrieveAllData() {
        String URL = "jdbc:mysql://localhost/agritrack";
        String USER = "root";
        String PASSWORD = "";

        String query = "SELECT * FROM `client_data` ORDER BY `Date` DESC";

        List<String> allData = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Customize this format according to your needs
                String dataEntry = String.format(
                        "%s<%s<%s<%s<%s<%s<%s",
                        resultSet.getString("Name"),
                        resultSet.getString("Address"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Adhar"),
                        resultSet.getString("Item Name"),
                        resultSet.getString("Item Amount"),
                        resultSet.getDate("Date")
                );

                allData.add(dataEntry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Data retrieval failed.");
        }

        return allData;
    }


    private void displayDataInTable(List<String> data) {
        // Create a new frame for displaying the table
        JFrame resultFrame = new JFrame("Search Results");
        resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        resultFrame.setSize(800, 600);
        resultFrame.setLayout(new BorderLayout());
        resultFrame.setLocationRelativeTo(null); // Center the frame
        
        // Create a table model to store the data
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("");
        tableModel.addColumn("Name");
        tableModel.addColumn("Address");
        tableModel.addColumn("Phone");
        tableModel.addColumn("Adhar");
        tableModel.addColumn("Item Name");
        tableModel.addColumn("Item Amount(in kg)");
        tableModel.addColumn("Date");

        // Create the JTable with the table model
        JTable resultTable = new JTable(tableModel);
        resultTable.setFillsViewportHeight(true);
        
        resultTable.getColumnModel().getColumn(0).setMinWidth(10);
        resultTable.getColumnModel().getColumn(0).setMaxWidth(40);
        resultTable.getColumnModel().getColumn(0).setPreferredWidth(30);

        // Populate the table with the retrieved data
        int rowCount = 1; // Start with the first row number
        for (String dataEntry : data) {
            String[] dataFields = dataEntry.split("<");
            // Insert the row number at the beginning of each data entry
            String[] rowData = new String[dataFields.length + 1];
            rowData[0] = String.valueOf(rowCount++);
            System.arraycopy(dataFields, 0, rowData, 1, dataFields.length);
            tableModel.addRow(rowData);
        }

        resultTable.getTableHeader().setPreferredSize(new Dimension(resultTable.getTableHeader().getWidth(), 25));
        
        resultTable.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor(new JCheckBox(), resultTable, tableModel, data));

        // Create a panel to hold both the table and the delete panel
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create a panel for the delete button and input fields
        // Create a panel for the delete button and input fields
        JPanel deletePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        deletePanel.setOpaque(true);

        // Set the background color for the delete panel
        deletePanel.setBackground(new Color(0, 150, 100)); // Set your desired color

        // Create input fields for Aadhar and Date
        JTextField aadharField = new JTextField("Enter Aadhar");
        aadharField.setBackground(new Color(255, 255, 153));
        Dimension preferredSize = new Dimension(150, aadharField.getPreferredSize().height);
        aadharField.setPreferredSize(preferredSize);
        // Add a FocusListener to clear the text when the field is clicked
        aadharField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (aadharField.getText().equals("Enter Aadhar")) {
                    aadharField.setText(""); // Clear the text when the field is clicked
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // You can add additional logic if needed when focus is lost
            }
        });
        
        JTextField dateField = new JTextField("Enter Date (yyyy-mm-dd)");
        dateField.setBackground(new Color(255, 255, 153));
        dateField.setPreferredSize(preferredSize);
        // Add a FocusListener to clear the text when the field is clicked
        dateField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (dateField.getText().equals("Enter Date (yyyy-mm-dd)")) {
                    dateField.setText(""); // Clear the text when the field is clicked
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // You can add additional logic if needed when focus is lost
            }
        });

        // Create the delete button
        JButton deleteButton = new JButton("Delete");
        deleteButton.setBackground(new Color(173, 255, 173));
        deleteButton.addActionListener(e -> {
            // Get Aadhar and Date values from input fields
            String aadharToDelete = aadharField.getText();
            String dateToDeleteStr = dateField.getText();

            try {
                // Convert the date string to a java.sql.Date object
                Date dateToDelete = Date.valueOf(dateToDeleteStr);

                // Perform deletion
                if (deleteRecordFromDatabase(aadharToDelete, dateToDelete)) {
                    JOptionPane.showMessageDialog(null, "Record deleted successfully.");
                    // Refresh the table after deletion
                    resultFrame.setVisible(false);
                    List<String> searchData = retrieveAllData();
                    displayDataInTable(searchData);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete record from the database.");
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "Invalid adhar or date format. Please use yyyy-mm-dd.");
            }
        });


        // Add components to the delete panel
        deletePanel.add(aadharField);
        deletePanel.add(dateField);
        deletePanel.add(deleteButton);


                // Create a custom cell renderer to set the background color
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Set the background color based on your condition (example: alternate rows)
                if (row % 2 == 0) {
                    setBackground(new Color(255, 255, 153));
                } else {
                     // Set your other desired color
                     setBackground(new Color(173, 255, 173)); // Set your desired color
                }

                return this;
            }
        };
        // Apply the custom renderer to each column in the table
        for (int i = 0; i < resultTable.getColumnCount(); i++) {
            resultTable.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        // Create a custom header renderer to set the background color
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
            {setHorizontalAlignment(SwingConstants.CENTER);}
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Set the background color for the header
                setBackground(new Color(0,150,100)); // Set your desired color

                // Set the text color for the header
                setForeground(Color.WHITE); // Set your desired text color
                setFont(getFont().deriveFont(Font.BOLD, 14));

                return this;
            }
        };

        // Apply the custom header renderer to each column header in the table
        for (int i = 0; i < resultTable.getColumnModel().getColumnCount(); i++) {
            resultTable.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        
        // Add the table and delete panel to the main panel
        mainPanel.add(new JScrollPane(resultTable), BorderLayout.CENTER);
        mainPanel.add(deletePanel, BorderLayout.SOUTH);
        

        // Add the main panel to the frame
        resultFrame.add(mainPanel);


        // Make the frame visible
        resultFrame.setVisible(true);
    }

    // Create a button editor to handle button clicks for deletion
    class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private int row; // Store the row number for this button
        private JTable table;
        private DefaultTableModel tableModel;
        private List<String> data;

        public ButtonEditor(JCheckBox checkBox, JTable table, DefaultTableModel tableModel, List<String> data) {
            super(checkBox);
            this.table = table;
            this.tableModel = tableModel;
            this.data = data;
            button = new JButton("Delete");
            button.setOpaque(true);
            button.addActionListener(e -> {
                if (row >= 0) {
                    String aadharToDelete = (String) tableModel.getValueAt(row, 3);
                    Date dateToDelete = (Date) tableModel.getValueAt(row, 6);

                    if (deleteRecordFromDatabase(aadharToDelete, dateToDelete)) {
                        tableModel.removeRow(row);
                        fireEditingStopped(); // Refresh the table
                        JOptionPane.showMessageDialog(null, "Record deleted successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to delete record from the database.");
                    }
                }
            });
            this.editorComponent = button;
            setClickCountToStart(1);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.row = row; // Set the current row for this button

            // Add print statements for debugging
            System.out.println("Editing row: " + row);

            return button;  // Return the button as the editor component
        }

        @Override
        public boolean stopCellEditing() {
            fireEditingStopped(); // Call fireEditingStopped within this method
            return super.stopCellEditing();
        }
    }



    private boolean deleteRecordFromDatabase(String aadharToDelete, Date dateToDelete) {
        String URL = "jdbc:mysql://localhost/agritrack";
        String USER = "root";
        String PASSWORD = "";

        String query = "DELETE FROM `client_data` WHERE `Adhar`=? AND `Date`=?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
           PreparedStatement preparedStatement = connection.prepareStatement(query)) {
           System.out.println("Connected to the database");

           preparedStatement.setString(1, aadharToDelete);
           preparedStatement.setDate(2, dateToDelete);
           System.out.println("Parameters set");

           int rowsDeleted = preparedStatement.executeUpdate();
           System.out.println(rowsDeleted + " row(s) deleted");

           return rowsDeleted > 0;
       } catch (SQLException e) {
           e.printStackTrace();
           return false;
       }

    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                HomePage homePage = new HomePage();
            }
        });
    }
}
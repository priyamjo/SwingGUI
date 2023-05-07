/*************************************************************************************************
 *  Database Pgm Using Java - ITC-5201-RNA – Assignment 2  *
 *  I declare that this assignment is my own work in accordance with Humber Academic Policy.  *
 *  No part of this assignment has been copied manually or electronically from any other source   *
 *  (including web sites) or distributed to other students/social media.  *
 *  Name: Priya Mary Joseph Student ID:N01468981 Date: 16-02-2022  *
 * *************************************************************************************************/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static java.lang.System.exit;

public class CustomerDashboard extends JFrame {

    private static final int FRAME_WIDTH = 500; //sets the frame width
    private static final int FRAME_HEIGHT = 700; //sets the frame height

    private JButton submitButton;
    private JPanel submitButtonPanel;

    private JLabel newDataLabel;
    private JPanel newDataPanel;

    private JPanel newEntryPanel;

    private JLabel nameLabel;
    private JTextField nameField;
    private JPanel namePanel;

    private JLabel phoneLabel;
    private JTextField phoneField;
    private JPanel phonePanel;

    private JLabel emailLabel;
    private JTextField emailField;
    private JPanel emailPanel;

    private JLabel postalCodeLabel;
    private JTextField postalCodeField;
    private JPanel postalCodePanel;


    private JPanel radioButtonPanel;
    private JRadioButton idButton;
    private JTextField customerIDField;
    private JRadioButton allButton;

    private JLabel messageLabel;
    private JPanel messagePanel;

    private JButton searchButton;
    private JPanel searchButtonPanel;

    private JPanel searchPanel;

    private CustomerDetails customerDetails;
    private CustomerUtils customerUtils = new CustomerUtils();

    private CustSearch custSearch;
    private ActionListener searchActionListener;

    /**
     * This constructor creates the structure of the customer management application frame
     */
    CustomerDashboard() {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        customerDetails = new CustomerDetails("/src/main/resources/custInfo.txt"); //create file to store data
        //create menu bar
        JMenuBar mainMenu = new JMenuBar();
        setJMenuBar(mainMenu);

        /* create each menu components
        	Home	Menu
        	 |		  |
        	Exit      |--- New Customer
        			  |--- Customer Search
         */
        mainMenu.add(createHomeMenu());
        mainMenu.add(createMenuMenu());
    }

    /**
     * creates the first menu bar component - Home
     *
     * @return JMenu for Home
     */
    public JMenu createHomeMenu() {
        JMenu home = new JMenu("Home");
        home.add(createHomeItem());
        return home;
    }

    /**
     * create the menu item for Home - Exit
     *
     * @return JMenuItem of Exit
     */
    public JMenuItem createHomeItem() {
        JMenuItem itemExit = new JMenuItem("Exit");
        class HomeItemListener implements ActionListener {

            public void actionPerformed(ActionEvent e) {
                exit(0);
            }
        }
        ActionListener listener = new HomeItemListener();
        itemExit.addActionListener(listener);
        return itemExit;
    }

    /**
     * creates the second menu bar component - Menu
     *
     * @return JMenu for Menu
     */
    public JMenu createMenuMenu() {
        JMenu home = new JMenu("Menu");
        home.add(createNewCustomer());
        home.add(createCustomerSearch());
        return home;
    }

    /**
     * creates the menu item for adding new customer details
     *
     * @return JMenuItem for New Entry
     */
    public JMenuItem createNewCustomer() {
        JMenuItem newEntry = new JMenuItem("New Customer");
        /**
         * Action Listener for Menu selection - New Customer
         */
        class newEntryListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                //create layout for adding new customer info
                addNewCustomerDetails();
                submitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //validate customer name
                        String name = nameField.getText();
                        if (!customerUtils.validateText(name)) {
                            JOptionPane.showMessageDialog(null, "Enter a valid name", "Warning", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                        //validate email
                        String email = emailField.getText();
                        if(!customerUtils.validateEmail(email)) {
                            JOptionPane.showMessageDialog(null, "Enter a valid email", "Warning", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                        //validate phone number to format XXX-XXXXXXX
                        String phone = phoneField.getText();
                        if(!customerUtils.validatePhone(phone)) {
                            JOptionPane.showMessageDialog(null, "Enter phone number in XXX-XXXXXXX format", "Warning", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                        //validate postal code
                        String postalCode = postalCodeField.getText();
                        if(!customerUtils.validatePostalCode(postalCode)) {
                            JOptionPane.showMessageDialog(null, "Enter a valid postal code", "Warning", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                        //add all validated date to file
                        Customer customer = new Customer();
                        //generate customer Id
                        customer.setCustomerId("C" + customerUtils.gen());
                        customer.setName(name);
                        customer.setEmail(email);
                        customer.setPostalCode(postalCode);
                        customer.setPhone(phone);
                        //call method to add data to file
                        customerDetails.addCustomer(customer);
                        //display message on successful data entry
                        messageLabel.setText("<html><p>Data added..!!</p></br><p>Customer Id : "+customer.getCustomerId()+"</p></html>");
                        clearNewData();
                    }
                });
            }
        }
        ActionListener listener = new newEntryListener();
        newEntry.addActionListener(listener);
        return newEntry;
    }

    public void addNewCustomerDetails() {
        nameLabel = new JLabel("Name");
        nameField = new JTextField(25);
        namePanel = new JPanel();
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        phoneLabel = new JLabel("Phone");
        phoneField = new JTextField(10);
        phonePanel = new JPanel();
        phonePanel.add(phoneLabel);
        phonePanel.add(phoneField);

        emailLabel = new JLabel("Email");
        emailField = new JTextField(35);
        emailPanel = new JPanel();
        emailPanel.add(emailLabel);
        emailPanel.add(emailField);

        postalCodeLabel = new JLabel("Postal Code");
        postalCodeField = new JTextField(6);
        postalCodePanel = new JPanel();
        postalCodePanel.add(postalCodeLabel);
        postalCodePanel.add(postalCodeField);

        submitButton = new JButton("Submit");
        submitButtonPanel = new JPanel();
        submitButtonPanel.add(submitButton);

        newDataLabel = new JLabel("");
        newDataPanel = new JPanel();
        newDataPanel.add(newDataLabel);

        messageLabel = new JLabel("");
        messagePanel = new JPanel();
        messagePanel.add(messageLabel);

        newEntryPanel = new JPanel();
        newEntryPanel.add(namePanel);
        newEntryPanel.add(phonePanel);
        newEntryPanel.add(emailPanel);
        newEntryPanel.add(postalCodePanel);
        newEntryPanel.add(submitButtonPanel);
        newEntryPanel.add(newDataPanel);
        newEntryPanel.add(messagePanel);

        add(newEntryPanel);
        setVisible(true);
    }

    /**
     * creates the menu item for searching customer details
     *
     * @return JMenuItem for Search
     */
    public JMenuItem createCustomerSearch() {
        JMenuItem search = new JMenuItem("Customer Search");
        /**
         * ActionListener for the Menu selection - Search
         */
        class searchListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                //create the design for searching customer info
                searchForCustomer();
                //add action listener for Search button
                searchButton.addActionListener(searchActionListener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //logic for search by id
                        if (idButton.isSelected()) {
                            String customerId = customerIDField.getText();
                            //check if customer id is entered
                            if (!customerId.isEmpty() && !customerId.isBlank()) {
                                Customer custData = customerDetails.getCustomerDetailsById(customerIDField.getText());
                                if (custData != null) { //check if the customer details were available in file
                                    custSearch = new CustSearch();
                                    //display retrieved data
                                    custSearch.getCustomerDetailsById(custData);
                                }
                            } else {
                                //shoe error message to enter a valid customer id
                                JOptionPane.showMessageDialog(null, "Enter a valid customer ID", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } //logic for retrieving all customers
                        else if (allButton.isSelected()) {
                            List<Customer> allCustomers = customerDetails.getAllCustomers();
                            custSearch = new CustSearch();
                            //display list of all customers
                            custSearch.displayAllCustomers(allCustomers);
                        }
                        //once search button is clicked, clear the customer id field entered
                        clearSearchData();
                    }
                });
            }
        }
        ActionListener listener = new searchListener();
        search.addActionListener(listener);
        return search;
    }


    public void searchForCustomer() {
        radioButtonPanel = new JPanel();
        radioButtonPanel.setLayout(new GridLayout(2, 1));

        idButton = new JRadioButton("Search by id");
        allButton = new JRadioButton("Search all");

        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(idButton);
        radioGroup.add(allButton);

        idButton.addActionListener(searchActionListener);
        allButton.addActionListener(searchActionListener);

        idButton.setSelected(true);

        customerIDField = new JTextField(10);

        radioButtonPanel.add(idButton);
        radioButtonPanel.add(customerIDField);
        radioButtonPanel.add(allButton);

        searchButtonPanel = new JPanel();
        searchButton = new JButton("Search");
        searchButtonPanel.add(searchButton);

        searchPanel = new JPanel();
        searchPanel.add(radioButtonPanel);
        searchPanel.add(searchButtonPanel);

        add(searchPanel);
        setVisible(true);
    }

    /**
     * Clear all the new customer details from respective fields
     */
    public void clearNewData() {
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        postalCodeField.setText("");
    }

    /**
     * Clear customer id field in search
     */
    public void clearSearchData() {
        customerIDField.setText("");
    }

}


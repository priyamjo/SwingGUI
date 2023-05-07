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

/**
 * This class is used to create new frame to display the search results
 */
public class CustSearch extends JFrame {

    private JLabel idLabel;
    private JTextField idField;
    private JPanel idPanel;

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

    private JButton modifyButton;
    private JPanel modifyButtonPanel;

    private JPanel modifyPanel;

    private JTable allCustTable;
    private JScrollPane allCustScroll;
    private JPanel allCustPanel;

    private JLabel messageLabel;
    private JPanel messagePanel;

    private CustomerDetails customerDetails;
    private CustomerUtils customerUtils = new CustomerUtils();

    /**
     * Constructor to set the dimensions and other attributes to frame
     */
    CustSearch() {
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Method to display list of all customer data retrieved from the file
     * @param allCustomers list of all customers retrieved from the file
     */
    public void displayAllCustomers(List<Customer> allCustomers) {

        //create table for size and price
        int size = allCustomers.size();
        String column[] = {"Customer ID", "Name", "Email", "Phone", "PostalCode"};
        String data[][] = new String[size][];
        int count = 0;
        if (!allCustomers.isEmpty()) {
            //iterate through all customers and add to table
            for (Customer ae : allCustomers) {
                if (count < size) {
                    data[count] = new String[]{ae.getCustomerId(), ae.getName(), ae.getEmail(), String.valueOf(ae.getPhone()), ae.getPostalCode()};
                    ++count;
                }
            }
        }
        allCustTable = new JTable(data, column);
        allCustScroll = new JScrollPane(allCustTable);
        allCustScroll.setPreferredSize(new Dimension(500, 500));

        allCustPanel = new JPanel();
        allCustPanel.add(allCustScroll);
        add(allCustPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    /**
     * Method to display the details for the customer that matches the customer id entered
     * @param customer info on customer with matching customer id
     */
    public void getCustomerDetailsById(Customer customer) {
        idLabel = new JLabel("Customer ID");
        idField = new JTextField(customer.getCustomerId());
        idField.setEditable(false);
        idPanel = new JPanel();
        idPanel.add(idLabel);
        idPanel.add(idField);

        nameLabel = new JLabel("Name");
        nameField = new JTextField(customer.getName());
        namePanel = new JPanel();
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        phoneLabel = new JLabel("Phone");
        phoneField = new JTextField((String.valueOf(customer.getPhone())));
        phonePanel = new JPanel();
        phonePanel.add(phoneLabel);
        phonePanel.add(phoneField);

        emailLabel = new JLabel("Email");
        emailField = new JTextField(customer.getEmail());
        emailPanel = new JPanel();
        emailPanel.add(emailLabel);
        emailPanel.add(emailField);

        postalCodeLabel = new JLabel("Postal Code");
        postalCodeField = new JTextField(customer.getPostalCode());
        postalCodePanel = new JPanel();
        postalCodePanel.add(postalCodeLabel);
        postalCodePanel.add(postalCodeField);

        modifyButton = new JButton("Update");
        modifyButtonPanel = new JPanel();
        modifyButtonPanel.add(modifyButton);

        messageLabel = new JLabel("");
        messagePanel = new JPanel();
        messagePanel.add(messageLabel);

        modifyPanel = new JPanel();
        modifyPanel.add(idPanel);
        modifyPanel.add(namePanel);
        modifyPanel.add(phonePanel);
        modifyPanel.add(emailPanel);
        modifyPanel.add(postalCodePanel);
        modifyPanel.add(modifyButtonPanel);
        modifyPanel.add(messageLabel);

        add(modifyPanel);
        setVisible(true);

        //add action listener to modify button
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer updatedCustomer = new Customer();
                CustomerDetails customerDetails = new CustomerDetails("/src/main/resources/custInfo.txt");
                //validate fields by calling validation methods in util class
                //validate name
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
                //validate phone
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
                updatedCustomer.setCustomerId(customer.getCustomerId());
                updatedCustomer.setName(name);
                updatedCustomer.setEmail(email);
                updatedCustomer.setPhone(phone);
                updatedCustomer.setPostalCode(postalCode);
                customerDetails.updateCustomer(updatedCustomer);
                //display message after updating
                messageLabel.setText("Data updated successfully..!!");
            }
        });

    }
}

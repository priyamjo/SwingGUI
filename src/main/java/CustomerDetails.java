/*************************************************************************************************
 *  Database Pgm Using Java - ITC-5201-RNA – Assignment 2  *
 *  I declare that this assignment is my own work in accordance with Humber Academic Policy.  *
 *  No part of this assignment has been copied manually or electronically from any other source   *
 *  (including web sites) or distributed to other students/social media.  *
 *  Name: Priya Mary Joseph Student ID:N01468981 Date: 16-02-2022  *
 * *************************************************************************************************/
import javax.swing.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CustomerDetails {

    private File customerFile;

    public CustomerDetails() {

    }
    /**
     * Constructor that creates the storage file
     *
     * @param fileName
     */
    public CustomerDetails(String fileName) {
        customerFile = new File(System.getProperty("user.dir") + fileName);
        try {
            if (customerFile.createNewFile()) {
                System.out.println("File created: " + customerFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check for customer that matches the custoemr id
     * @param custId customer id entered
     * @return customer object with details for the customer
     */
    public Customer getCustomerDetailsById(String custId) {
        Customer custDetails = new Customer();
        boolean flag = false;
        try (DataInputStream input = new DataInputStream(new FileInputStream(customerFile));) {
            //iterate through whole file
            while (true) {
                String id = input.readUTF();
                String name = input.readUTF();
                String email = input.readUTF();
                String phone = input.readUTF();
                String postal = input.readUTF();
                //check if id matches
                if (id.equalsIgnoreCase(custId)) {
                    //change flag to true of id matches
                    flag=true;
                    //set customer info to customer object
                    custDetails.setCustomerId(id);
                    custDetails.setName(name);
                    custDetails.setEmail(email);
                    custDetails.setPhone(phone);
                    custDetails.setPostalCode(postal);
                    //close the input stream
                    input.close();
                    return custDetails;
                }
                else continue;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //if matching data is not found show error message
        if(!flag){
            JOptionPane.showMessageDialog(null, "No matching data ..!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return custDetails;
    }

    /**
     * Method to retrieve list of all customer info in file
     * @return list of customers
     */
    public List<Customer> getAllCustomers() {
        List<Customer> allCustomers = new ArrayList<>();
        try (DataInputStream input = new DataInputStream(new FileInputStream(customerFile));) {
            DecimalFormat df = new DecimalFormat("#");
            df.setMaximumFractionDigits(0);
            //iterate through all the data
            while (true) {
                Customer custDetails = new Customer();
                custDetails.setCustomerId(input.readUTF());
                custDetails.setName(input.readUTF());
                custDetails.setEmail(input.readUTF());
                custDetails.setPhone(input.readUTF());
                custDetails.setPostalCode(input.readUTF());
                allCustomers.add(custDetails);
                }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return allCustomers;
    }

    /**
     * Method to add a new customer info to the file
     * @param customer customer object with new data
     */
    public void addCustomer(Customer customer) {
        try (DataOutputStream output = new DataOutputStream(new FileOutputStream(customerFile, true));) {
            output.writeUTF(customer.getCustomerId());
            output.writeUTF(customer.getName());
            output.writeUTF(customer.getEmail());
            output.writeUTF(customer.getPhone());
            output.writeUTF(customer.getPostalCode());
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    /**
     * Method to update customer info
     * @param customer customer object with new data
     */
    public void updateCustomer(Customer customer) {
        try (DataInputStream input = new DataInputStream(new FileInputStream(customerFile));
        DataOutputStream output = new DataOutputStream(new FileOutputStream(customerFile, true))) {
            while (true) {
                String id = input.readUTF();
                if (id.equalsIgnoreCase(customer.getCustomerId())) {
                    output.writeUTF(id);
                    output.writeUTF(customer.getName());
                    output.writeUTF(customer.getEmail());
                    output.writeUTF(customer.getPhone());
                    output.writeUTF(customer.getPostalCode());
                    input.close();
                    output.close();
                }
                else continue;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

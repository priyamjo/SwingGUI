/*************************************************************************************************
 *  Database Pgm Using Java - ITC-5201-RNA – Assignment 2  *
 *  I declare that this assignment is my own work in accordance with Humber Academic Policy.  *
 *  No part of this assignment has been copied manually or electronically from any other source   *
 *  (including web sites) or distributed to other students/social media.  *
 *  Name: Priya Mary Joseph Student ID:N01468981 Date: 16-02-2022  *
 * *************************************************************************************************/
public class Customer {
    String customerId;
    String name;
    String email;
    String phone;
    String postalCode;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Customer() {

}
    public Customer(String customerId, String name, String email, String phone, String postalCode) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.postalCode = postalCode;
    }
}

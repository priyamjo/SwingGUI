/*************************************************************************************************
 *  Database Pgm Using Java - ITC-5201-RNA – Assignment 2  *
 *  I declare that this assignment is my own work in accordance with Humber Academic Policy.  *
 *  No part of this assignment has been copied manually or electronically from any other source   *
 *  (including web sites) or distributed to other students/social media.  *
 *  Name: Priya Mary Joseph Student ID:N01468981 Date: 16-02-2022  *
 * *************************************************************************************************/
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class contains all the utility methods like validating different fields
 */
public class CustomerUtils {

    //creating an email pattern
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final String POSTAL_CODE_PATTERN =
            "^(?!.*[DFIOQU])[A-VXY][0-9][A-Z]●?[0-9][A-Z][0-9]$";

    /**
     * Generates a random 5 digit integer. This is used to create the customer id
     * @return 5 digit random number
     */
    public int gen() {
        Random r = new Random(System.currentTimeMillis());
        return 10000 + r.nextInt(20000);
    }

    /**
     * Method to validate text fields
     * @param content text content
     * @return true if valid, false if not valid
     */
    public boolean validateText(String content) {
        boolean valid = true;
        if (!content.isEmpty()) {
            char[] chars = content.toCharArray();
            for (char c : chars) {
                if (Character.isDigit(c)) {
                    valid = false;
                }
            }
        } else
            valid = false;
        return valid;
    }

    /**
     * Method to validate email field
     * @param email customer email
     * @return true if valid, false if not valid
     */
    public boolean validateEmail(String email) {
        //check if the email entered matches the email pattern
        if (!email.matches(EMAIL_PATTERN)) {
            return false; }
        return true;
    }

    /**
     * Method to validate postal code field
     * @param postalCode customer postal code
     * @return true if valid, false if not valid
     */
    public boolean validatePostalCode(String postalCode) {
        //check if the email entered matches the email pattern
        if (!postalCode.matches(POSTAL_CODE_PATTERN)) {
            return false; }
        return true;
    }

    /**
     * Validate the phone number entered against XXX-XXXXXXX pattern
     * @param phone customer phone
     * @return true if valid, false if not valid
     */
    public boolean validatePhone(String phone) {
        Pattern pattern = Pattern.compile("\\d{3}-\\d{7}");
        Matcher matcher = pattern.matcher(phone);

        if (!matcher.matches()) {
            return false;}
        return true;
    }
}

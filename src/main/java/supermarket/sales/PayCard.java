package supermarket.sales;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDate;
public class PayCard {
    private static final Logger log = LoggerFactory.getLogger(PayCard.class);

    private PayCardType payCardType;
    private String cardNumber;
    private String expiryDate;
    private String lastName;
    private String firstName;

    public PayCard(PayCardType payCardType, String cardNumber, String expiryDate, String lastName, String firstName) {
        this.payCardType = payCardType;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public PayCardType getPayCardType() {
        return payCardType;
    }

    public void setPayCardType(PayCardType payCardType) {
        this.payCardType = payCardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Method to check if the card is valid
    public boolean isValid() {
        // Check First Name and Last Name
        if (firstName.isBlank() || lastName.isBlank()) {
            log.error("First Name and Last Name are wrong");
            return false;
        }

        // Check date of validity
        // Assuming "expiryDate" is in the format "MM/YY"
        String[] dateParts = expiryDate.split("/");
        try {
            int expectedMonth = Integer.parseInt(dateParts[0]);
            int expectedYear = Integer.parseInt("20" + dateParts[1]); // Assuming all years are in the 21st century

            // Check if the card has expired
            if (expectedYear < getCurrentYear() || (expectedYear == getCurrentYear() && expectedMonth < getCurrentMonth())) {
                log.info("The card is expired");
                return false;
            }
        } catch (NumberFormatException e) {
            log.error("Wrong expiry date {}", e.getMessage());
            return false;
        }

        // Check the sum of the first leading digits of the card number
        int sum = 0;
        for (char digit : cardNumber.replaceAll("\\s", "").toCharArray()) {
            if (Character.isDigit(digit)) {
                sum += Character.getNumericValue(digit);
            } else {
                log.error("Wrong digit symbol {}", digit);
                return false;
            }
        }

        if (sum != 10) {
            log.info("Check Sum is wrong");
            return false;
        }

        // Add more checks if needed

        return true;
    }

    private int getCurrentMonth() {
        LocalDate localDate = LocalDate.now();
        return localDate.getMonthValue();
    }

    // Helper method to get the current year
    private int getCurrentYear() {
        LocalDate localDate = LocalDate.now();
        return localDate.getYear();
    }
}

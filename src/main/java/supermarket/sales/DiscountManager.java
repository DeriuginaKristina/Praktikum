package supermarket.sales;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class DiscountManager {
    private static final Logger logger = LoggerFactory.getLogger(DiscountManager.class);

    private HashMap<String, DiscountInfo> discountMap;

    public DiscountManager() {
        this.discountMap = new HashMap<>();
    }

    public void addDiscount(String itemName, double discount, String expirationDate, String promotion) {
        try {
            discountMap.put(itemName, new DiscountInfo(discount, expirationDate, promotion));
        } catch (Exception e) {
            logger.error("Error adding discount for item: " + itemName, e);
        }
    }

    public void applyDiscount(String itemName, String outputFilePath) {
        try {
            if (discountMap.containsKey(itemName)) {
                DiscountInfo discountInfo = discountMap.get(itemName);

                if (!discountInfo.isExpired()) {
                    double discountedPrice = calculateDiscountedPrice(itemName, discountInfo.getDiscount());
                    String promotionMessage = discountInfo.getPromotion();

                    // Output the result to the specified file
                    try (PrintWriter writer = new PrintWriter(new FileWriter(outputFilePath, true))) {
                        writer.println("Item: " + itemName + ", Discounted Price: " + discountedPrice + ", Promotion: " + promotionMessage);
                    } catch (IOException e) {
                        logger.error("Error writing to file while applying discount for item: " + itemName, e);
                    }

                    System.out.println("Discount applied successfully.");
                } else {
                    System.out.println("Discount has expired for item " + itemName + ".");
                }
            } else {
                System.out.println("No discount available for item " + itemName + ".");
            }
        } catch (Exception e) {
            logger.error("Error applying discount for item: " + itemName, e);
        }
    } // TODO Test not work

    private double calculateDiscountedPrice(String itemName, double discount) {
        try {
            // Replace this with your actual logic to calculate discounted price
            // For simplicity, let's assume the original price is 100
            double originalPrice = 100;
            return originalPrice * (1 - discount);
        } catch (Exception e) {
            logger.error("Error calculating discounted price for item: " + itemName, e);
            return 0.0; // Return 0 in case of an error
        }
    }

   public DiscountInfo getDiscountInfo(String name) {
       return discountMap.get(name);
    }

    static class DiscountInfo {
        private double discount;
        private Date expirationDate;
        private String promotion;

        public DiscountInfo(double discount, String expirationDate, String promotion) {
            this.discount = discount;
            try {
                this.expirationDate = new SimpleDateFormat("yyyy-MM-dd").parse(expirationDate);
            } catch (ParseException e) {
                logger.error("Error parsing expiration date", e);
            }
            this.promotion = promotion;
        }

        public double getDiscount() {
            return discount;
        }

        public boolean isExpired() {
            return expirationDate != null && expirationDate.before(new Date());
        }

        public String getPromotion() {
            return promotion;
        }
    }
}

//The provided code includes a Java method called applyDiscount and a corresponding test method. Let's break down the code:
//
//applyDiscount Method Explanation:
//This method is designed to apply discounts to items based on certain conditions. Here's a step-by-step explanation:
//
//Parameters:
//
//itemName: A string representing the name of the item for which the discount is to be applied.
//outputFilePath: A string specifying the file path where the result of applying the discount will be stored.
//Try-Catch Block:
//
//The method is enclosed in a try-catch block to handle any exceptions that might occur during the discount application process.
//Checking Discount Availability:
//
//It checks if the discountMap (presumably a map storing discount information) contains an entry for the given itemName.
//Applying Discount if Available:
//
//If a discount is available for the specified item, it retrieves the DiscountInfo object from the map.
//It checks if the discount is not expired using !discountInfo.isExpired().
//Calculating Discounted Price:
//
//If the discount is still valid, it calculates the discounted price using the calculateDiscountedPrice method.
//Writing to File:
//
//The result, including item name, discounted price, and promotion message, is then written to the specified file (outputFilePath) using a PrintWriter within a try-with-resources block.
//Handling File Writing Errors:
//
//If an IOException occurs during file writing, an error message is logged using a logger.
//Outputting Success or Expiry Message:
//
//Depending on the outcome (successful discount application or expired discount), a corresponding message is printed to the console.
//Exception Handling:
//
//Any exception that occurs during the process is caught, and an error message is logged using a logger.
//applyDiscount Test Method Explanation:
//The provided test method is testing the applyDiscount method for a specific case. Here's a breakdown:
//
//Test Setup:
//
//It redirects the standard output (System.out) to capture the printed output during the test.
//Discount Configuration:
//
//It adds a discount for a test item (TestItem1) with a 10% discount, a specified expiration date ("2023-01-01"), and a promotion message.
//Applying Discount:
//
//It then calls the applyDiscount method for the same test item and provides an output file path.
//Assertion:
//
//It asserts that the printed output captured during the test matches the expected output ("Discount applied successfully.\n").
//Notes:
//The calculateDiscountedPrice method is assumed to be defined elsewhere in the code, as it's referenced but not provided here.
//The code is using a logger (logger.error) to log errors, but the logger instantiation and configuration are not included in the provided snippet. Ensure proper logger setup elsewhere in the code.
//Additional test cases may be needed to thoroughly test the applyDiscount method for different scenarios.





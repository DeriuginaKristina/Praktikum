package supermarket.sales;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class ProductInPut {
    private static final Logger logger = LoggerFactory.getLogger(ProductInPut.class);

    final double SALES_TAX_RATE_LOW = 0.07;
    final double SALES_TAX_RATE_HIGH = 0.19;

    private String barcode;
    private String name;
    private double price;
    private double discount;

    public ProductInPut(String barcode, String name, double price, double discount) {
        this.barcode = barcode;
        this.name = name;
        this.price = price;
        this.discount = discount;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative for item: " + name);
        } else {
            this.price = price;
        }
    /*public void setPrice(double price) {
        if (price < 0) {
            logger.warn("Attempted to set a negative price for item: " + name);
            // You can choose to throw an IllegalArgumentException or take appropriate action based on your requirements
            // For now, let's set the price to 0 to avoid negative prices
            this.price = 0;
        } else {
            this.price = price;
        }*/

    /*public void setPrice(double price) {
        this.price = price;*/
    }
    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double calculateSalesTax() {
        try {
            // Condition: If the product is milk or bread, apply low sales tax; otherwise, apply high sales tax
            if (name.toLowerCase().contains("milk") || name.toLowerCase().contains("bread") ||
                    name.toLowerCase().contains("tea") || name.toLowerCase().contains("coffee") ||
                    name.toLowerCase().contains("apple") || name.toLowerCase().contains("potato") ||
                    name.toLowerCase().contains("tomato") || name.toLowerCase().contains("cucumber") ||
                    name.toLowerCase().contains("onion") || name.toLowerCase().contains("cauliflower") ||
                    name.toLowerCase().contains("cabbage") || name.toLowerCase().contains("carrot") ||
                    name.toLowerCase().contains("turnip") || name.toLowerCase().contains("beet")) {
                return price * SALES_TAX_RATE_LOW;
            } else {
                return price * SALES_TAX_RATE_HIGH;
            }
        } catch (Exception e) {
            logger.error("Error calculating sales tax for item: " + name, e);
            return 0.0; // Return 0 in case of an error
        }
    }
    public double calculateTotalPrice() {
        return price + calculateSalesTax();
    }

    public double calculateDiscount() {
        try {
            if (price >= 200) {
                discount = price * 0.1;
                price = price - discount;
            }
            return discount;
        } catch (Exception e) {
            logger.error("Error calculating discount for item: " + name, e);
            return 0.0; // Return 0 in case of an error //TODO test logger info price discount show /reschedule to Discount Info
        }
    }
    public double calculateDiscountFromManager(DiscountManager discountManager, String outputFilePath) {
        try {
            if (discountManager != null) {
                DiscountManager.DiscountInfo discountInfo = discountManager.getDiscountInfo(name);

                if (discountInfo != null && !discountInfo.isExpired()) {
                    double discountedPrice = calculateDiscountedPrice(price, discountInfo.getDiscount());

                    // Output the result to the specified file
                    try (PrintWriter writer = new PrintWriter(new FileWriter(outputFilePath, true))) {
                        writer.println("Item: " + name + ", Discounted Price: " + discountedPrice + ", Promotion: " + discountInfo.getPromotion());
                    } catch (IOException e) {
                        logger.error("Error writing to file while applying discount for item: " + name, e);
                    }

                    System.out.println("Discount applied successfully.");
                    return discountedPrice;
                } else {
                    System.out.println("No discount available or discount has expired for item " + name + ".");
                }
            }
        } catch (Exception e) {
            logger.error("Error applying discount for item: " + name, e);
        }

        // Return the original price if no discount is applied
        return price;
    }
    private double calculateDiscountedPrice(double price, double discount) {
        return price - discount;
    }
}
















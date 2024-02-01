package supermarket.sales;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class SalesService {
    private static final Logger logger = LoggerFactory.getLogger(SalesService.class);

    private Map<String, ProductInPut> productDatabase;
    private Map<String, Integer> cart;

    public SalesService() {
        productDatabase = new HashMap<>();
        cart = new HashMap<>();
    }

    public void addProduct(ProductInPut product) {
        try {
            productDatabase.put(product.getBarcode(), product);
        } catch (Exception e) {
            logger.error("Error adding product to the database", e);
        }
    }

    public void scanProduct(String barcode, int quantity) {
        try {
            if (productDatabase.containsKey(barcode)) {
                cart.put(barcode, cart.getOrDefault(barcode, 0) + quantity);
                System.out.println("Scanned: " + productDatabase.get(barcode).getName());
            } else {
                System.out.println("Product not found!");
            }
        } catch (Exception e) {
            logger.error("Error scanning product", e);
        }
    }
    public double calculateTotal() {
        double total = 0;
        double totalActualPrice = 0;
        double totalSalesTax = 0;

        try {
            for (Map.Entry<String, Integer> entry : cart.entrySet()) {
                String barcode = entry.getKey();
                int quantity = entry.getValue();
                ProductInPut product = productDatabase.get(barcode);

                double price = product.getPrice();
                double salesTax = product.calculateSalesTax();
                double totalProductPrice = product.calculateTotalPrice() * quantity;

                totalActualPrice += price * quantity;
                totalSalesTax += salesTax * quantity;
                total += totalProductPrice;
            }

            System.out.println("Total Actual Price: €" + totalActualPrice);
            System.out.println("Total Sales Tax: €" + totalSalesTax);
        } catch (Exception e) {
            logger.error("Error calculating total", e);
        }

        return total;
    }
    public double calculateTotalWithDiscount() {
        double totalWithDiscount = 0;

        try {
            for (Map.Entry<String, Integer> entry : cart.entrySet()) {
                String barcode = entry.getKey();
                int quantity = entry.getValue();
                ProductInPut product = productDatabase.get(barcode);

                double discount = product.calculateDiscount();
                double discountedTotalProductPrice = (product.calculateTotalPrice() - discount) * quantity;

                totalWithDiscount += discountedTotalProductPrice;
            }
        } catch (Exception e) {
            logger.error("Error calculating total with discount", e);
        }

        return totalWithDiscount;
    }

    public void processPayment(double amount) {
        try {
            System.out.println("Payment processed successfully. Total amount: €" + amount);
            // reset the cart after processing the payment
            cart.clear();
        } catch (Exception e) {
            logger.error("Error processing payment", e);
        }
    }
    public Map<String, Integer> getCart() {
        return cart;
    }

    public Map<Object, Object> getProductDatabase() {
        return null;
    }
}



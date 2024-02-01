package supermarket.sales;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductInPutTest {

    @Test
    public void getBarcode() {
        String barcode = "123";
        ProductInPut product = new ProductInPut(barcode, "Test Product", 10.0, 0.0);
        assertEquals(barcode, product.getBarcode());
    }

    @Test
    public void setBarcode() {
        String barcode = "456";
        ProductInPut product = new ProductInPut("123", "Test Product", 10.0, 0.0);
        product.setBarcode(barcode);
        assertEquals(barcode, product.getBarcode());
    }

    @Test
    public void getName() {
        String name = "Test Product";
        ProductInPut product = new ProductInPut("123", name, 10.0, 0.0);
        assertEquals(name, product.getName());
    }

    @Test
    public void setName() {
        String name = "Updated Product";
        ProductInPut product = new ProductInPut("123", "Test Product", 10.0, 0.0);
        product.setName(name);
        assertEquals(name, product.getName());
    }

    @Test
    public void getPrice() {
        double price = 15.0;
        ProductInPut product = new ProductInPut("123", "Test Product", price, 0.0);
        assertEquals(price, product.getPrice(), 0.0);
    }

    @Test
    public void setPrice() {
        double price = 20.0;
        ProductInPut product = new ProductInPut("123", "Test Product", 10.0, 0.0);
        product.setPrice(price);
        assertEquals(price, product.getPrice(), 0.0);
    }

    @Test
    public void getDiscount() {
        double discount = 2.0;
        ProductInPut product = new ProductInPut("123", "Test Product", 10.0, discount);
        assertEquals(discount, product.getDiscount(), 0.0);
    }

    @Test
    public void setDiscount() {
        double discount = 3.0;
        ProductInPut product = new ProductInPut("123", "Test Product", 10.0, 0.0);
        product.setDiscount(discount);
        assertEquals(discount, product.getDiscount(), 0.0);
    }

    @Test
    public void calculateSalesTaxLow() {
        // Test when the product is milk, expect low sales tax
        ProductInPut product = new ProductInPut("123", "Milk", 10.0, 0.0);
        assertEquals(0.7, product.calculateSalesTax(), 0.0);
    }

    @Test
    public void calculateSalesTaxHigh() {
        // Test when the product is not exempt, expect high sales tax
        ProductInPut product = new ProductInPut("123", "Electronics", 100.0, 0.0);
        assertEquals(19.0, product.calculateSalesTax(), 0.0);
    }

    @Test
    public void calculateTotalPrice() {
        // Test the total price calculation
        ProductInPut product = new ProductInPut("123", "Bread", 2.0, 0.0);
        assertEquals(2.14, product.calculateTotalPrice(), 0.0);
    }

    @Test
    public void calculateTotalPriceWithDiscount() {
        // Test the total price calculation with discount
        ProductInPut product = new ProductInPut("123", "Laptop", 1000.0, 0.0);
        assertEquals(900.0, product.calculateTotalPrice(), 0.0);
    }

    @Test
    public void calculateTotalPriceWithDiscountAndSalesTax() {
        // Test the total price calculation with discount and sales tax
        ProductInPut product = new ProductInPut("123", "Laptop", 1000.0, 0.0);
        assertEquals(1071.0, product.calculateTotalPrice(), 0.0);
    }
}



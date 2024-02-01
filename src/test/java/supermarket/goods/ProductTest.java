package supermarket.goods;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    private Product product;

    @BeforeEach
    void setUp() {
        product = new FoodProduct("TestFood", 10.0, LocalDate.now(), 20);
    }

    @Test
    void testGetName() {
        assertEquals("TestFood", product.getName(), "Product name should match");
    }

    @Test
    void testSetName() {
        product.setName("NewName");
        assertEquals("NewName", product.getName(), "Setting product name should update the name");
    }

    @Test
    void testGetPrice() {
        assertEquals(10.0, product.getPrice(), 0.001, "Product price should match");
    }

    @Test
    void testSetPrice() {
        product.setPrice(15.0);
        assertEquals(15.0, product.getPrice(), 0.001, "Setting product price should update the price");
    }

    @Test
    void testGetQuantityInStock() {
        assertEquals(20, product.getQuantityInStock(), "Product quantity in stock should match");
    }

    @Test
    void testSetQuantityInStock() {
        product.setQuantityInStock(30);
        assertEquals(30, product.getQuantityInStock(), "Setting product quantity in stock should update the quantity");
    }

    @Test
    void testGetExpirationDate() {
        assertEquals(LocalDate.now(), product.getExpirationDate(), "Product expiration date should match");
    }

    @Test
    void testSetExpirationDate() {
        LocalDate newDate = LocalDate.now().plusDays(5);
        product.setExpirationDate(newDate);
        assertEquals(newDate, product.getExpirationDate(), "Setting product expiration date should update the date");
    }

    @Test
    void testIsDecreased() {
        assertFalse(product.isDecreased(), "Product should not be marked as decreased initially");
    }

    @Test
    void testToString() {
        String expectedToString = "FoodProduct{name='TestFood', price=10.0, quantityInStock=20, expirationDate=" + LocalDate.now() + '}';
        assertEquals(expectedToString, product.toString(), "toString method should return expected string");
    }

    @Test
    void testGetInfo() {
        // Assuming getInfo() does not throw an exception
        assertDoesNotThrow(() -> product.getInfo(), "Calling getInfo() should not throw an exception");
    }
}
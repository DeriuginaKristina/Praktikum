package supermarket.sales;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class SalesServiceTest {

    private SalesService salesService;
    private ProductInPut product1;
    private ProductInPut product2;

    @BeforeEach
    public void setUp() {
        salesService = new SalesService();

        // Creating sample products for testing
        product1 = new ProductInPut("123", "Milk", 2.0, 0.0);
        product2 = new ProductInPut("456", "Bread", 1.5, 0.0);

        // Adding products to the service
        salesService.addProduct(product1);
        salesService.addProduct(product2);
    }

    @Test
    public void addProduct() {
        Map<Object, Object> productDatabase = salesService.getProductDatabase();

        // Check that both products are present in the product database
        assertTrue(productDatabase.containsKey("123"));
        assertTrue(productDatabase.containsKey("456"));

        // Optionally, you can check the size of the product database
        assertEquals(2, productDatabase.size());
    }

    @Test
    public void scanProduct() {
        // Redirect System.out to capture printed output
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        // Use existing salesService instance (created in setUp)
        salesService.scanProduct("123", 2);
        assertTrue(salesService.getCart().containsKey("123"));

        salesService.scanProduct("789", 1);
        assertFalse(salesService.getCart().containsKey("789"));

        // Reset System.out
        System.setOut(System.out);

        /*// Check if the correct message is printed
        assertEquals("Scanned: Milk\nProduct not found!\n", outputStreamCaptor.toString());*/
    }
}


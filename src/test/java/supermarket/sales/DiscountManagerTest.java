package supermarket.sales;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class DiscountManagerTest {

    private DiscountManager discountManager;

    @BeforeEach
    public void setUp() {
        discountManager = new DiscountManager();
    }

    @Test
    public void addDiscount() {
        // Test adding a discount
        discountManager.addDiscount("TestItem1", 0.1, "2023-01-01", "10% off");
        assertTrue(discountManager.getDiscountInfo("TestItem1") != null);
        /* assertEquals(0.1, discountManager.getDiscountInfo("TestItem1").getDiscount(), 0.0);*/
    }

    @Test
    public void applyValidDiscount() {
        // Test applying a valid discount
        discountManager.addDiscount("TestItem1", 0.1, "2023-01-01", "10% off");

        // Redirect System.out to capture printed output
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        discountManager.applyDiscount("TestItem1", "output.txt");

        assertEquals("Discount applied successfully.\n", outputStreamCaptor.toString());
    }

    @Test
    public void applyExpiredDiscount() {
        // Test applying an expired discount
        discountManager.addDiscount("TestItem1", 0.1, "2022-01-01", "Expired Discount");

        // Redirect System.out to capture printed output
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        discountManager.applyDiscount("TestItem1", "output.txt");

        assertEquals("Discount has expired for item TestItem1.\n", outputStreamCaptor.toString());
    }}
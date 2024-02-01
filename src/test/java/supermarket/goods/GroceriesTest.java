package supermarket.goods;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class GroceriesTest {

    private Groceries groceries;

    @BeforeEach
    void setUp() {
        groceries = new Groceries("TestGroceries", 5.0, 15, LocalDate.now().plusMonths(3));
    }

    @Test
    void testGetInfo() {
        String expectedInfo = "Information of Groceries: " +
                "Name: TestGroceries, Price: 5.0, Quantity in Stock: 15";
        assertEquals(expectedInfo, getConsoleOutput(() -> groceries.getInfo()), "getInfo should print expected information");
    }

    // Utility method to capture console output
    private String getConsoleOutput(Runnable action) {
        // Redirect System.out to capture printed output
        var systemOut = System.out;
        try {
            var outputStream = new java.io.ByteArrayOutputStream();
            System.setOut(new java.io.PrintStream(outputStream));

            // Perform the action that prints to console
            action.run();

            // Return the captured output
            return outputStream.toString().trim();
        } finally {
            // Restore System.out
            System.setOut(systemOut);
        }
    }
}
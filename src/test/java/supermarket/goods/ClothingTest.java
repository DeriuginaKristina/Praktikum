package supermarket.goods;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClothingTest {

    private Clothing clothing;

    @BeforeEach
    void setUp() {
        clothing = new Clothing("TestClothing", 30.0, 15, LocalDate.now().plusMonths(6));
    }

    @Test
    void testGetInfo() {
        String expectedInfo = "Information of Clothing: " +
                "Name: TestClothing, Price: 30.0, Quantity in Stock: 15";
        assertEquals(expectedInfo, getConsoleOutput(() -> clothing.getInfo()), "getInfo should print expected information");
    }

    // Utility method to capture console output
    private String getConsoleOutput(Runnable action) {
        var systemOut = System.out;
        try {
            var outputStream = new java.io.ByteArrayOutputStream();
            System.setOut(new java.io.PrintStream(outputStream));

            action.run();

            return outputStream.toString().trim();
        } finally {
            System.setOut(systemOut);
        }
    }
}
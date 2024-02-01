package supermarket.goods;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElectronicsTest {

    private Electronics electronics;

    @BeforeEach
    void setUp() {
        electronics = new Electronics("TestElectronics", 50.0, 20, LocalDate.now().plusYears(2));
        electronics.setWarrantyYears(3);
    }

    @Test
    void testGetInfo() {
        String expectedInfo = "Information of Electronics: " +
                "Name: TestElectronics, Price: 50.0, Quantity in Stock: 20, Warranty Years: 3";
        assertEquals(expectedInfo, getConsoleOutput(() -> electronics.getInfo()), "getInfo should print expected information");
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
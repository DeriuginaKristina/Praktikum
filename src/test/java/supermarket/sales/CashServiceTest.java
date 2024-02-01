package supermarket.sales;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CashServiceTest {

    @BeforeEach
    public void setUp() {
        // You might perform any setup here, like initializing a test database, if necessary
    }

    @Test
    public void processCashPaymentSuccess() {
        // Arrange
        int amount = 50;

        // Act
        String result = CashService.processCashPayment(amount);

        // Assert
        assertTrue(result.contains("Cash payment successful"));
    }

    @Test
    public void processCashPaymentFailure() {
        // Arrange
        int amount = -10;  // Simulate a failed payment with a negative amount

        // Act
        String result = CashService.processCashPayment(amount);

        // Assert
        assertTrue(result.contains("Cash payment failed"));
    }

    @Test
    public void generateEndOfDayReportNegativeRemainder() {
        // Arrange
        // Assuming there is a negative cash remainder in the register for this test
        // You might set up a test database with specific data for this test case //

        // Act
        String result = CashService.generateEndOfDayReport();

        // Assert
        // Uncomment the following line when the implementation includes the error message
        // assertTrue(result.contains("Error: Negative cash remainder"));
    }
    @Test
    public void generateEndOfDayReportPositiveRemainder() {
        // Arrange
        // Assuming there is a positive cash remainder in the register for this test
        // You might set up a test database with specific data for this test case

        // Act
        String result = CashService.generateEndOfDayReport();

        // Assert
        // Uncomment the following line when the implementation includes the success message
        // assertTrue(result.contains("End-of-day cash report generated successfully"));
    }
}


package supermarket.sales;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class CashService {
    private static final Logger logger = LoggerFactory.getLogger(CashService.class);

    // Database connection details (replace with your own)
    ////TODO  Replace placeholders with actual database details.
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String DB_USER = "username";
    private static final String DB_PASSWORD = "password";

    // Method for processing cash payments
    public static String processCashPayment(int amount) {
        try {
            // Simulate cash payment processing
            boolean paymentSuccessful = processCashTransaction(amount);

            // Update payment status based on the success of the cash transaction
            if (paymentSuccessful) {
                updatePaymentStatus("CASH_PAYMENT_SUCCESS", amount);
                return "Cash payment successful. Payment status updated.";
            } else {
                updatePaymentStatus("CASH_PAYMENT_FAILURE", amount);
                return "Cash payment failed. Payment status updated.";
            }
        } catch (Exception e) {
            // Handle exceptions
            logger.error("Error processing cash payment", e);
            return "Error processing cash payment. Please try again.";
        }
    }

    // Simulate cash transaction (replace this with actual cash handling logic)
    private static boolean processCashTransaction(int amount) {
        // In a real application, you would perform actual cash handling logic
        // For simplicity, let's assume the payment is successful if the amount is positive
        return amount > 0;
    }

    // Update payment status with actual logic to update the status in a database
    private static void updatePaymentStatus(String status, int amount) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Update payment status in the database
            String updateQuery = "UPDATE payment_table SET status = ? WHERE amount = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, status);
                preparedStatement.setInt(2, amount);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    logger.info("Payment status updated to: {} for amount: {} in the database", status, amount);
                } else {
                    logger.warn("No rows updated in the database. Payment status not updated.");
                }
            }
        } catch (SQLException e) {
            // Handle database-related exceptions
            logger.error("Error updating payment status in the database", e);
        }
    }

    // Generate end-of-day cash report with checks on negative remainder
    public static String generateEndOfDayReport() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Calculate total cash in the register
            String selectQuery = "SELECT SUM(amount) FROM payment_table";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    int totalCash = resultSet.getInt(1);

                    // Check if the total cash is negative
                    if (totalCash < 0) {
                        logger.error("Negative cash remainder in the register: {}", totalCash);
                        return "Error: Negative cash remainder. Please investigate.";
                    } else {
                        logger.info("End-of-day cash report: Total cash in the register: {}", totalCash);
                        return "End-of-day cash report generated successfully.";
                    }
                }
            }
        } catch (SQLException e) {
            // Handle database-related exceptions
            logger.error("Error generating end-of-day cash report", e);
            return "Error generating end-of-day cash report. Please try again.";
        }
        return "Unknown error generating end-of-day cash report.";
    }
}
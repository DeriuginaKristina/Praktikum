package supermarket.sales;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PayCardTest {
    private PayCard validCard;
    private PayCard invalidCard;

    @BeforeEach
    public void setUp() {
        // Initialize valid card
        validCard = new PayCard(PayCardType.MASTER_CARD, "1234 5678 9012 3456", "12/25", "Doe", "John");

        // Initialize invalid card
        invalidCard = new PayCard(PayCardType.VISA, "9876 5432 1098 7654", "01/20", "Card", "Invalid");
    }

    @Test
    public void testInvalidCard() {
        assertFalse(invalidCard.isValid());
    }

    @Test
    public void testInvalidFirstName() {
        invalidCard.setFirstName("");
        assertFalse(invalidCard.isValid());
    }

    @Test
    public void testInvalidLastName() {
        invalidCard.setLastName("");
        assertFalse(invalidCard.isValid());
    }

    @Test
    public void testInvalidExpiryDate() {
        invalidCard.setExpiryDate("invalid");
        assertFalse(invalidCard.isValid());
    }

    @Test
    public void testExpiredCard() {
        invalidCard.setExpiryDate("01/22");
        assertFalse(invalidCard.isValid());
    }

    @Test
    public void testWrongDigitInCardNumber() {
        invalidCard.setCardNumber("9876 5432 1098 ABCD");
        assertFalse(invalidCard.isValid());
    }

    @Test
    public void testWrongSumInCardNumber() {
        invalidCard.setCardNumber("1111 1111 1111 1111");
        assertFalse(invalidCard.isValid());
    }
}


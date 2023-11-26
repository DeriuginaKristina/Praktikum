package Homework47;
import exceptions.InsufficientFundsException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    @Test
    public void testDeposit() {
        BankAccount account = new BankAccount("John Doe", "123456789", 100.0);
        account.deposit(50.0);
        assertEquals(150.0, account.getBalance(), 0.01);
    }

    @Test
    public void testWithdrawSufficientFunds() {
        BankAccount account = new BankAccount("Alice Smith", "987654321", 200.0);
        account.withdraw(50.0);
        assertEquals(150.0, account.getBalance(), 0.01);
    }

    @Test
    public void testWithdrawInsufficientFunds() {
        BankAccount account = new BankAccount("Bob Johnson", "555555555", 30.0);

        InsufficientFundsException exception = assertThrows(InsufficientFundsException.class,
                () -> account.withdraw(50.0));

        assertEquals("Das Guthaben auf dem Konto reicht nicht aus, um 50,0 € abzuheben.", exception.getMessage());
        assertEquals(30.0, exception.getCurrentBalance(), 0.01);
        assertEquals(50.0, exception.getRequestedAmount(), 0.01);
    }

    @Test
    public void testWithdrawNegativeAmount() {
        BankAccount account = new BankAccount("Eve Brown", "777777777", 100.0);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> account.withdraw(-20.0));

        assertEquals("Der Auszahlungsbetrag -20,0 darf nicht negativ oder gleich 0 sein.", exception.getMessage());
    }
}



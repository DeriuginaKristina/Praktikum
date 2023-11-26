package Homework47;
import exceptions.InsufficientFundsException;
import java.util.logging.Logger;
public class BankAccount {
    /*Entwicklung eines Bankensystems mit Ausnahmebehandlung
1. Modellierung von Bankkonten
BankAccount-Klasse:
Felder: AccountNumber (String), OwnerName (String), Balance (Double).
Methoden:
Einzahlung (doppelter Betrag): erhöht das Guthaben.
Abheben (doppelter Betrag): reduziert den Kontostand.
Wenn der Auszahlungsbetrag größer als das Guthaben ist, wird eine InsufficientFundsException ausgelöst.
getBalance(): Gibt den aktuellen Kontostand zurück.
InsufficientFundsException:
Eine benutzerdefinierte Ausnahme, die von RuntimeException erbt.
Enthält Details wie den aktuellen Kontostand und den angeforderten Auszahlungsbetrag.*/
    private static final Logger LOGGER = Logger.getLogger(BankAccount.class.getName());
    private String ownerName;
    public String accountNumber;
    private double balance;
    public BankAccount(String ownerName, String accountNumber, double balance) {
        this.ownerName = ownerName;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Einzahlungsbetrag " + amount + " darf nicht negativ oder gleich 0 sein.");
        } else {
            balance += amount;
            LOGGER.info("Besitzerkonto " + ownerName + " um den Betrag aufgefüllt " + amount + " €");
        }
    }
    public double withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Abzuhebender Betrag " + amount + " darf nicht negativ oder gleich 0 sein 0.");
        }
        if (amount > balance) {
            LOGGER.info("Es ist nicht möglich, {} € von Ihrem {}-Konto abzuheben. Unzureichende Mittel.");
            throw new InsufficientFundsException("Auf dem Konto ist nicht genügend Guthaben für eine Auszahlung vorhanden " + amount + " €.", balance, amount);
        } else {
            balance -= amount;
            LOGGER.info("Der Betrag von {} € wurde erfolgreich vom Konto abgebucht {}");
        }
        return balance;  // Geben Sie das aktualisierte Guthaben nach der Auszahlung zurück (Return the updated balance after the withdrawal)
    }
    public double getBalance() {
        return balance;
    }}



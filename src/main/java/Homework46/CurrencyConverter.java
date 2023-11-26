package Homework46;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*Aufgabe: „Währungsumrechner mit Ausnahmebehandlung“
Erstellen Sie ein Java-Programm, das einen vom Benutzer eingegebenen
Betrag von einer Währung in eine andere umrechnet.
Der Benutzer muss den Quellbetrag, die Quellwährung und die Zielwährung eingeben.
Das Programm sollte den umgerechneten Betrag berechnen und das Ergebnis anzeigen.
Die Ausnahmebehandlung muss bei der Jobausführung berücksichtigt werden.
Programmvoraussetzungen:
Fordern Sie den Benutzer auf, den umzurechnenden Betrag anzugeben.
Fordern Sie den Benutzer auf, die Quell- und Zielwährung anzugeben.
Verwenden Sie für die Umrechnung vordefinierte Wechselkurse (z. B. 1 USD = 0,85 EUR, 1 EUR = 1,2 USD usw.)*/

public class CurrencyConverter {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CurrencyConverter.class);
    private static final Map<String, Double> exchangeRates = new HashMap<>();

    static {
        // Vordefinierte Wechselkurse (Predefined exchange rates)
        exchangeRates.put("USD", 1.0);
        exchangeRates.put("EUR", 0.85);
        exchangeRates.put("GBP", 0.75);
        // Fügen Sie nach Bedarf weitere Währungen und Wechselkurse hinzu (Add more currencies and exchange rates as needed)
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                // Erhalten Sie Benutzereingaben (Get user input)
                double sourceAmount = getAmountFromUser(scanner);

                String sourceCurrency = getCurrencyFromUser(scanner, "Enter the source currency: ");//Originalwährung (original currency)
                String targetCurrency = getCurrencyFromUser(scanner, "Enter the target currency: ");//Zielwährung(target currency)

                // Rufen Sie die Konvertierungsmethode auf (Call the conversion method)
                double convertedAmount = convertCurrency(sourceAmount, sourceCurrency, targetCurrency);

                // Zeigen Sie das Ergebnis an (Display the result)
                LOGGER.info("Converted amount: " + convertedAmount + " " + targetCurrency);

                // Operation erfolgreich abgeschlossen (Operation completed successfully)
                LOGGER.info("Conversion operation completed.");

                break;
            } catch (NumberFormatException e) {
                LOGGER.error("Error: Please enter a valid numeric amount.");
            } catch (IllegalArgumentException e) {
                LOGGER.error("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    // Methode, um den Betrag vom Benutzer zu erhalten (Method to get the amount from the user)
    private static double getAmountFromUser(Scanner scanner) {
        LOGGER.info("Enter the amount to convert: ");
        String input = scanner.next();

        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid numeric format");
        }
    }

    // Methode zum Abrufen des Währungscodes vom Benutzer (Method to get the currency code from the user)
    private static String getCurrencyFromUser(Scanner scanner, String prompt) {
        while (true) {
            LOGGER.info(prompt);
            String currency = scanner.next().toUpperCase();

            if (exchangeRates.containsKey(currency)) {
                return currency;
            } else {
                LOGGER.info("Error: Unsupported currency code. Please enter a valid code.");
            }
        }
    }

    // Methode zur Währungsumrechnung (Method to convert currency)
    private static double convertCurrency(double sourceAmount, String sourceCurrency, String targetCurrency) {
        if (sourceCurrency.equals(targetCurrency)) {
            return sourceAmount; // Keine Konvertierung erforderlich (No conversion needed)
        }

        double sourceRate = exchangeRates.get(sourceCurrency);
        double targetRate = exchangeRates.get(targetCurrency);

        return sourceAmount * (targetRate / sourceRate);
    }
}
/*In dieser Version fordert das Programm den Benutzer ständig zur Dateneingabe auf,
        bis eine gültige Konvertierung durchgeführt wird.
        Es fängt NumberFormatException ab, wenn der Benutzer einen nicht numerischen Betragswert eingibt.
        und IllegalArgumentException, wenn ein nicht unterstützter Währungscode eingegeben wird.
        Vordefinierte Wechselkurse werden in einer HashMap gespeichert. getAmountFromUser- und getCurrencyFromUser-Methoden
        werden zur Verarbeitung von Benutzereingaben verwendet, und die ConvertCurrency-Methode führt die eigentliche Konvertierung durch.
        Das Programm zeigt eine Fehlermeldung an und fordert den Benutzer erneut auf, wenn eine Ausnahme auftritt.
        Die Schleife wird fortgesetzt, bis die Konvertierung erfolgreich ist.*/

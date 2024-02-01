package supermarket.goods;
import org.junit.jupiter.api.*;
import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InventoryTest {

    private Inventory inventory;
    private Product product;

    @BeforeEach
    public void setUp() {
        inventory = new Inventory() {
            @Override
            public Map<String, Product> getProductInventory() {
                return null;
            }
        }; // An anonymous subclass to instantiate the abstract class
        product = new FoodProduct("Apple", 1.0, LocalDate.now().plusDays(7), 20);
        inventory.addProduct(product);
    }

    @Test
    @DisplayName("Update stock should reflect in product quantity")
    public void updateStock() {
        inventory.updateStock("Apple", 5);
        assertEquals(5, product.getQuantityInStock());
    }

    @Test
    @DisplayName("Check stock should trigger low stock alert")
    public void checkStockLowStockAlert() {
        // Reduce stock to trigger a low stock alert
        product.setQuantityInStock(9);
        inventory.checkStock();
        // Add assertions for the low stock alert logic if applicable
        //TODO logger check
    }

    @Test
    @DisplayName("Restock should increase product quantity")
    public void restock() {
        inventory.restock(product, 10);
        assertEquals(30, product.getQuantityInStock());
    }

    @Test
    @DisplayName("Sell should decrease product quantity")
    public void sell() {
        inventory.sell(product, 5);
        assertEquals(15, product.getQuantityInStock());
    }

    @Test
    @DisplayName("Restock should throw exception for non-existing product")
    public void restockProductNotFound() {
        assertThrows(ProductNotFoundException.class, () -> {
            Product nonExistingProduct = new FoodProduct("Nonexistent", 1.0, LocalDate.now(), 0);
            inventory.restock(nonExistingProduct, 10);
        });
    }

    @Test
    @DisplayName("Sell should throw exception for non-existing product")
    public void sellProductNotFound() {
        assertThrows(ProductNotFoundException.class, () -> {
            Product nonExistingProduct = new FoodProduct("Nonexistent", 1.0, LocalDate.now(), 0);
            inventory.sell(nonExistingProduct, 5);
        });
    }
}



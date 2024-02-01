package supermarket.goods;
import java.util.Map;
public interface InventoryManagement {
    void restock(Product product, int quantity);
    void sell(Product product, int quantity);
    Map<String, Product> getProductInventory();
}


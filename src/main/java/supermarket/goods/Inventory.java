package supermarket.goods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;
public abstract class Inventory implements InventoryManagement {
    private static final Logger logger = LoggerFactory.getLogger(Inventory.class);

    private Map<String, Product> productInventory;

    public Inventory() {
        this.productInventory = new HashMap<>();
    }

    public void addProduct(Product product) {
        productInventory.put(product.getName(), product);
    }

    public void updateStock(String productName, int newQuantity) {
        Product product = productInventory.get(productName);
        if (product != null) {
            product.setQuantityInStock(newQuantity);
        } else {
            logger.error("Product not found in inventory: {}", productName);
            throw new ProductNotFoundException("Product not found in inventory: " + productName);
        }
    }
    public void checkStock() {
        for (Product product : productInventory.values()) {
            if (product.getQuantityInStock() < 10) {
                logger.warn("Low stock alert for product: {}", product.getName());
                logger.warn("Current stock level: {}", product.getQuantityInStock());
                logger.warn("Please replenish stock!");
            }
        }
    }
    @Override
    public void restock(Product product, int quantity) {
        String productName = product.getName();
        if (productInventory.containsKey(productName)) {
            int currentStock = productInventory.get(productName).getQuantityInStock();
            productInventory.get(productName).setQuantityInStock(currentStock + quantity);
            logger.info("Restocked {} units of {}", quantity, productName);
        } else {
            logger.error("Product not found in inventory. Cannot restock.");
            throw new ProductNotFoundException("Product not found in inventory: " + productName);
        }
    }
    @Override
    public void sell(Product product, int quantity) {
        String productName = product.getName();
        if (productInventory.containsKey(productName)) {
            int currentStock = productInventory.get(productName).getQuantityInStock();
            if (currentStock >= quantity) {
                productInventory.get(productName).setQuantityInStock(currentStock - quantity);
                logger.info("Sold {} units of {}", quantity, productName);
            } else {
                logger.warn("Insufficient stock for selling {} units of {}", quantity, productName);
            }
        } else {
            logger.error("Product not found in inventory. Cannot sell.");
            throw new ProductNotFoundException("Product not found in inventory: " + productName);
        }}}











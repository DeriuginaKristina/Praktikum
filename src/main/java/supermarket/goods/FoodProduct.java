package supermarket.goods;
import java.time.LocalDate;
public class FoodProduct extends Product {
    public FoodProduct(String name, double price, LocalDate expirationDate, int quantityInStock) {
        super(name, price, quantityInStock, expirationDate);
    }
    @Override
    public String toString() {
        return "FoodProduct{" +
                "name='" + getName() + '\'' +
                ", price=" + getPrice() +
                ", quantityInStock=" + getQuantityInStock() +
                ", expirationDate=" + getExpirationDate() +
                '}';

    }
    @Override
    public void getInfo() {
        System.out.println("Information of FoodProduct: " +
                "Name: " + getName() +
                ", Price: " + getPrice() +
                ", Quantity in Stock: " + getQuantityInStock() +
                ", Expiration Date: " + getExpirationDate());
    }
}



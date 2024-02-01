package supermarket.goods;
import java.time.LocalDate;
public class Clothing extends Product {
    public Clothing(String name, double price, int quantityInStock, LocalDate expirationDate) {
        super(name, price, quantityInStock, expirationDate);
    }

    @Override
    public void getInfo() {
        System.out.println("Information of Clothing: " +
                "Name: " + getName() +
                ", Price: " + getPrice() +
                ", Quantity in Stock: " + getQuantityInStock());
    }

    @Override
    public String toString() {
        return "Clothing{" +
                "name='" + getName() + '\'' +
                ", price=" + getPrice() +
                ", quantityInStock=" + getQuantityInStock() +
                '}';
    }
}



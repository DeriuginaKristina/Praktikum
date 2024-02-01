package supermarket.goods;
import java.time.LocalDate;
class Groceries extends Product {
    public Groceries(String name, double price, int quantityInStock, LocalDate expirationDate) {
        super(name, price, quantityInStock, expirationDate);
    }

    @Override
    public void getInfo() {
        System.out.println("Information of Groceries: " +
                "Name: " + getName() +
                ", Price: " + getPrice() +
                ", Quantity in Stock: " + getQuantityInStock());
    }
}

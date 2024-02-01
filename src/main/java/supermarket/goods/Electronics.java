package supermarket.goods;
import java.time.LocalDate;
public class Electronics extends Product {
    private int warrantyYears;
    public Electronics(String name, double price, int quantityInStock, LocalDate expirationDate) {
        super(name, price, quantityInStock, expirationDate);
    }
    public int getWarrantyYears() {
        return warrantyYears;
    }
    public void setWarrantyYears(int warrantyYears) {
        this.warrantyYears = warrantyYears;
    }
    @Override
    public void getInfo() {
        System.out.println("Information of Electronics: " +
                "Name: " + getName() +
                ", Price: " + getPrice() +
                ", Quantity in Stock: " + getQuantityInStock() +
                ", Warranty Years: " + warrantyYears);
    }

    @Override
    public String toString() {
        return "Electronics{" +
                "name='" + getName() + '\'' +
                ", price=" + getPrice() +
                ", quantityInStock=" + getQuantityInStock() +
                ", expirationDate=" + getExpirationDate() +
                ", warrantyYears=" + warrantyYears +
                '}';
    }
}


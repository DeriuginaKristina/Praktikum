package supermarket.goods;
import java.time.LocalDate;
import java.util.StringJoiner;
public abstract class Product {
    private String name;
    private double price;
    private int quantityInStock;
    LocalDate expirationDate;

    public Product(String name, double price, int quantityInStock, LocalDate expirationDate) {
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.expirationDate = expirationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
           if (price >= 0) {
               this.price = price;
           } else {
               throw new IllegalArgumentException("Price cannot be negative.");
           }
       }
    public int getQuantityInStock() {
        return quantityInStock;
    }
    public void setQuantityInStock(int quantityInStock) {
        if (quantityInStock >= 0) {
            this.quantityInStock = quantityInStock;
        } else {
            throw new IllegalArgumentException("Quantity in stock cannot be negative.");
        }
    }
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isDecreased() {
        // Method to check if the quantity has decreased
        return quantityInStock <= 0;
    }
    @Override
    public String toString() {
        return new StringJoiner(", ", Product.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("price=" + price)
                .add("quantityInStock=" + quantityInStock)
                .add("expirationDate=" + expirationDate)
                .toString();
    }

    public abstract void getInfo();
}


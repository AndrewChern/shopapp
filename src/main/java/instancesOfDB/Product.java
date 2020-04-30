package instancesOfDB;

import java.util.Objects;

public class Product {

    private int code;
    private String name;
    private double price;
    private int amount;

    public Product() {
    }

    public Product(int code, String name, double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    //Constructor for list from DB
    public Product(int code, String name, double price, int amount) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public int getCode() {
        return code;
    }

    public int getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getCode() == product.getCode() &&
                Double.compare(product.getPrice(), getPrice()) == 0 &&
                Objects.equals(getName(), product.getName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getCode(), getName(), getPrice());
    }

    @Override
    public String toString() {
        return "Product{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", price in USD=" + price +
                ", amount=" + amount +
                '}';
    }
}

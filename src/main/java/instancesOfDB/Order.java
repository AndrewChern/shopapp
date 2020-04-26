package instancesOfDB;

import java.util.Objects;

public class Order {

    private int clientID;
    private int productID;
    private double totalPrice;
    private int totalAmount;
    private String orderDate;

    public Order() {

    }

    public Order(int clientID, int productID, Double totalPrice, int totalAmount, String orderDate) {
        this.clientID = clientID;
        this.productID = productID;
        this.totalPrice = totalPrice;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
    }

    public int getClientID() {
        return clientID;
    }

    public int getProductID() {
        return productID;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public String getOrderDate() {
        return orderDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return  getClientID() == order.getClientID() &&
                getProductID() == order.getProductID() &&
                getTotalAmount() == order.getTotalAmount() &&
                Objects.equals(getTotalPrice(), order.getTotalPrice()) &&
                Objects.equals(getOrderDate(), order.getOrderDate());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getClientID(), getProductID(), getTotalPrice(), getTotalAmount(), getOrderDate());
    }

    @Override
    public String toString() {
        return "Order{" +
                "clientID=" + clientID +
                ", productID=" + productID +
                ", totalPrice in USD =" + totalPrice +
                ", totalAmount=" + totalAmount +
                ", orderDate='" + orderDate + '\'' +
                '}';
    }
}

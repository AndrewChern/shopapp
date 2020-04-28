package entity;

import instancesOfDB.Client;
import instancesOfDB.Order;
import instancesOfDB.Product;

import java.util.ArrayList;

public class ListsFromDB {

    private ArrayList<Client> clientList = new ArrayList<>();
    private ArrayList<Product> productList = new ArrayList<>();
    private ArrayList<Order> orderList = new ArrayList<>();

    private static ListsFromDB listsFromDB = new ListsFromDB();

    public ListsFromDB() {
    }

    public ListsFromDB getListsFromDB() {
        return listsFromDB;
    }

    public ArrayList<Client> getClientList() {
        return clientList;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public void addClient (Client client){
        clientList.add(client);
    }

    public void addProduct (Product product){
        productList.add(product);
    }

    public void addOrder (Order order){
        orderList.add(order);
    }

    public void clearClientList (){
        clientList.clear();
    }

    public void clearProductList (){
        productList.clear();
    }

    public void clearOrderList (){
        orderList.clear();
    }
}

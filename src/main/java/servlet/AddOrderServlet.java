package servlet;

import entity.Clients;
import entity.ListsFromDB;
import entity.Orders;
import entity.Products;
import instancesOfDB.Client;
import instancesOfDB.Order;
import instancesOfDB.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "AddOrder", urlPatterns = {"/addOrder"})
public class AddOrderServlet extends HttpServlet {

    @lombok.SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        ListsFromDB listsFromDB = new ListsFromDB();
        listsFromDB.clearProductList();
        listsFromDB.clearClientList();
        listsFromDB.clearOrderList();

        Clients clients = new Clients();
        try {
            clients.getAllObjects();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayList<Client> clientList = listsFromDB.getClientList();
        req.setAttribute("clientList", clientList);

        Products products = new Products();
       try {
            products.getAllObjects();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayList<Product> productList = listsFromDB.getProductList();
        req.setAttribute("productList", productList);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/addOrder.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int clientID = Integer.parseInt(req.getParameter("clientID"));
        int productID = Integer.parseInt(req.getParameter("productID"));
        double totalPrice = Double.parseDouble(req.getParameter("totalPrice"));
        int totalAmount = Integer.parseInt(req.getParameter("totalAmount"));
        String orderDate = req.getParameter("orderDate");

        Order order = new Order(clientID, productID, totalPrice, totalAmount, orderDate);

        try {
            Orders orders = new Orders();
            orders.addObject(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("adminPanel.html");
    }
}

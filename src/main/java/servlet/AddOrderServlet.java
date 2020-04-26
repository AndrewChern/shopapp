package servlet;

import entity.Clients;
import entity.Orders;
import instancesOfDB.Client;
import instancesOfDB.Order;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddOrder", urlPatterns = {"/addOrder"})
public class AddOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("addOrder.html");
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

        resp.sendRedirect("addOrder.html");
    }
}

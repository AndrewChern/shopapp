package servlet;

import entity.Clients;
import entity.ListsFromDB;
import entity.Orders;
import entity.Products;
import instancesOfDB.Client;
import instancesOfDB.Order;
import instancesOfDB.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "StatisticsServlet", urlPatterns = {"/statistics"})
public class StatisticsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        ListsFromDB listsFromDB = new ListsFromDB();
        listsFromDB.getListsFromDB();
        listsFromDB.clearClientList();
        listsFromDB.clearOrderList();
        listsFromDB.clearProductList();

        try {
            Clients clients = new Clients();
            clients.getAllObjects();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Client> clientsList = listsFromDB.getClientList();
        req.setAttribute("clientsList", clientsList);

        try {
            Products products = new Products();
            products.getAllObjects();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Product> productsList = listsFromDB.getProductList();
        req.setAttribute("productsList", productsList);

        try {
            Orders orders = new Orders();
            orders.getAllObjects();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Order> ordersList = listsFromDB.getOrderList();
        req.setAttribute("ordersList", ordersList);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/statistics.jsp");
        dispatcher.forward(req, resp);

        //resp.sendRedirect("statistics.html");
    }
}

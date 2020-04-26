package servlet;

import entity.Products;
import instancesOfDB.Product;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddProduct", urlPatterns = {"/addProduct"})
public class AddProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("addProduct.html");
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        int amount = Integer.parseInt(req.getParameter("amount"));

        Product product = new Product();

        try {
            Products products = new Products();
            products.addObject(product, amount);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("addProduct.html");
    }
}

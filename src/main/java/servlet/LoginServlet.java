package servlet;

import entity.Clients;
import entity.CreateDB;
import entity.Orders;
import entity.Products;

import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Login", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private static final String LOGIN = "admin";
    private static final String PASS = "12321";

    static final String TEMPLATE = "<html>" +
            "<head><title>login</title></head>" +
            "<body><h1>%s</h1></body></html>";
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (LOGIN.equals(login) && PASS.equals(password)) {

            HttpSession session = req.getSession(true);
            session.setAttribute("user_login", login);

            //creating DB and all necessary tables after logging in

            try {
                CreateDB createDB = new CreateDB();
                createDB.createDB();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                Clients clients = new Clients();
                clients.initDB();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                Orders orders = new Orders();
                orders.initDB();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                Products products = new Products();
                products.initDB();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        // redirect to admin panels page
            resp.sendRedirect("adminPanel.html");

        } else {
        // throw exception message and redirect to start page
            String exception = "Wrong password or login";
            resp.getWriter().println(String.format(TEMPLATE, exception));
            resp.sendRedirect("index.html");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String param = req.getParameter("param");

        HttpSession session = req.getSession(false);
        if ("exit".equals(param))
            session.removeAttribute("user_login");

        resp.sendRedirect("index.html");
    }

}

package servlet;

import entity.Clients;
import instancesOfDB.Client;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddClient", urlPatterns = {"/addClient"})
public class AddClientServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("addClient.html");
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String secondName = req.getParameter("secondName");
        int phoneNumber = Integer.parseInt(req.getParameter("phoneNumber"));

        Client client = new Client(name, secondName, phoneNumber);

        try {
            Clients clients = new Clients();
            clients.addObject(client);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("addClient.html");
    }
}

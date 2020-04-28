package entity;

import instancesOfDB.Order;

import java.sql.*;

public class Orders implements DbService{

    public Orders() throws SQLException {
    }

    DbConnection dbConnection = new DbConnection();

    Connection conn=dbConnection.connect();

    @Override
    public void initDB ()throws SQLException {

        Statement st = conn.createStatement();

        try {
            st.execute("DROP TABLE IF EXISTS orders");
            st.execute("CREATE TABLE orders (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "clientID FOREIGN KEY(idClient) REFERENCES clients(id)," +
                    "productID FOREIGN KEY(idProduct) REFERENCES products(id)," +
                    "totalPrice DOUBLE DEFAULT NULL," +
                    "totalAmount INT DEFAULT NULL," +
                    "orderDate VARCHAR(100) NOT NULL)");
        } finally {
            st.close();
        }
    }

    @Override
    public void getAllObjects() throws SQLException {

        String column;
        int clientID=0;
        int productID=0;
        double totalPrice = 0.0;
        int totalAmount=0;
        String orderDate = "";
        ListsFromDB listsFromDB = new ListsFromDB();
        listsFromDB.getListsFromDB();

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM orders");

        try {
            ResultSet rs = ps.executeQuery();
            try {
                ResultSetMetaData md = rs.getMetaData();

                while (rs.next()) {
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        column = md.getColumnName(i);

                        if ("clientID".equals(column))
                            clientID = rs.getInt(i);

                        if ("productID".equals(column))
                            productID = rs.getInt(i);

                        if ("totalPrice".equals(column))
                            totalPrice = rs.getDouble(i);

                        if ("totalAmount".equals(column))
                            totalAmount = rs.getInt(i);

                        if ("orderDate".equals(column))
                            orderDate = rs.getString(i);
                    }
                    listsFromDB.addOrder(new Order(clientID, productID, totalPrice, totalAmount, orderDate));
                }
            } finally {
                rs.close();
            }
        } finally {
            ps.close();
        }
    }

    public void addObject(Order order) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO products (productUniqueCode, clientID, totalPrice, totalAmount, orderDate) VALUES(?, ?, ?, ?, ?)");
        try {
            ps.setInt(1, order.getProductID());
            ps.setInt(2, order.getClientID());
            ps.setDouble(3, order.getTotalPrice());
            ps.setInt(4, order.getTotalAmount());
            ps.setString(4, order.toString());

            ps.executeUpdate();
        } finally {
            ps.close();
        }
    }
}

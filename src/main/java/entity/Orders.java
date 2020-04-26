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
                    "productUniqueCode INT NOT NULL," +
                    "clientID INT NOT NULL," +
                    "totalPrice DOUBLE DEFAULT NULL," +
                    "totalAmount INT DEFAULT NULL," +
                    "orderDate VARCHAR(100) NOT NULL)");
        } finally {
            st.close();
        }
    }

    @Override
    public void getAllObjects() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM orders");

        try {
            ResultSet rs = ps.executeQuery();
            try {
                ResultSetMetaData md = rs.getMetaData();

                for (int i = 1; i <= md.getColumnCount(); i++)
                    System.out.print(md.getColumnName(i) + "\t\t");
                System.out.println();

                while (rs.next()) {
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        System.out.print(rs.getString(i) + "\t\t");
                    }
                    System.out.println();
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

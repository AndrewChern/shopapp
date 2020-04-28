package entity;

import instancesOfDB.Order;
import instancesOfDB.Product;

import java.sql.*;

public class Products implements DbService {

    public Products() throws SQLException {
    }

    DbConnection dbConnection = new DbConnection();

    Connection conn=dbConnection.connect();

    @Override
    public void initDB ()throws SQLException {

        Statement st = conn.createStatement();

        try {
            st.execute("DROP TABLE IF EXISTS products");
            st.execute("CREATE TABLE products (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(100) NOT NULL," +
                    "price DOUBLE DEFAULT NULL," +
                    "amount INT DEFAULT NULL)");
        } finally {
            st.close();
        }
    }

    @Override
    public void getAllObjects() throws SQLException {
        String column;
        String name = "";
        double price = 0.0;
        int amount=0;

        ListsFromDB listsFromDB = new ListsFromDB();
        listsFromDB.getListsFromDB();

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM products");

        try {
            ResultSet rs = ps.executeQuery();
            try {
                ResultSetMetaData md = rs.getMetaData();

                while (rs.next()) {
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        column = md.getColumnName(i);

                        if ("name".equals(column))
                            name = rs.getString(i);

                        if ("price".equals(column))
                            price = rs.getDouble(i);

                        if ("amount".equals(column))
                            amount = rs.getInt(i);

                    }
                    listsFromDB.addProduct(new Product(name, price, amount));
                }
            } finally {
                rs.close();
            }
        } finally {
            ps.close();
        }
    }

    public void addObject(Product product, int amount) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO products (name, price, amount) VALUES(?, ?, ?)");
        try {
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, amount);

            ps.executeUpdate();
        } finally {
            ps.close();
        }
    }
}

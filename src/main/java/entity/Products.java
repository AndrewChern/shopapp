package entity;

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
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM products");

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

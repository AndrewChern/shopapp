package entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    static Connection connection;

    DbProperties props = new DbProperties();

    public Connection connect ()throws SQLException {
        try {
            connection = DriverManager.getConnection(props.getUrl(), props.getUser(), props.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

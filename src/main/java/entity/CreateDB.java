package entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDB {

    public CreateDB() throws SQLException {
    }

    DbConnection dbConnection = new DbConnection();

    Connection conn=dbConnection.connect();

    public  void createDB(){

        try(Statement st = conn.createStatement()) {
            st.execute("DROP DATABASE IF EXISTS shop");
            st.execute("CREATE DATABASE shop");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

package entity;

import instancesOfDB.Client;

import java.sql.*;

public class Clients implements DbService{

    public Clients() throws SQLException {
    }

    DbConnection dbConnection = new DbConnection();

    Connection conn=dbConnection.connect();

    @Override
    public void initDB ()throws SQLException {

        Statement st = conn.createStatement();

        try {
            st.execute("DROP TABLE IF EXISTS clients");
            st.execute("CREATE TABLE clients (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(100) NOT NULL," +
                    "secondName VARCHAR(100) NOT NULL," +
                    "phoneNumber INT DEFAULT NULL)");
        } finally {
            st.close();
        }
    }

    @Override
    public void getAllObjects() throws SQLException {

        String column;
        int id=0;
        String name = "";
        String secondName = "";
        int phoneNumber=0;
        ListsFromDB listsFromDB = new ListsFromDB();
        listsFromDB.getListsFromDB();

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM clients");

        try {
            ResultSet rs = ps.executeQuery();
            try {
                ResultSetMetaData md = rs.getMetaData();

                while (rs.next()) {
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        column = md.getColumnName(i);

                        if ("id".equals(column))
                            id = rs.getInt(i);

                        if ("name".equals(column))
                            name = rs.getString(i);

                        if ("secondName".equals(column))
                            secondName = rs.getString(i);

                        if ("phoneNumber".equals(column))
                            phoneNumber = rs.getInt(i);
                    }
                    listsFromDB.addClient(new Client(id, name, secondName, phoneNumber));
                }
            } finally {
                rs.close();
            }
        } finally {
            ps.close();
        }
    }

    public void addObject(Client client) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO clients (name, secondName, phoneNumber) VALUES(?, ?, ?)");
        try {
            ps.setString(1, client.getName());
            ps.setString(2, client.getSecondName());
            ps.setInt(3, client.getPhoneNumber());

            ps.executeUpdate();
        } finally {
            ps.close();
        }
    }

}

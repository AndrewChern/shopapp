package entity;

import java.sql.SQLException;

public interface DbService {

    void initDB ()throws SQLException;

    void getAllObjects () throws SQLException;

}

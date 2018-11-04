package DB;

import App.PropertiesFileManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {


    static DBConnectionInfo info;
    private static String DB_DRIVER;
    private static String DB_CONNECTION;
    private static String DB_USER;
    private static String DB_PASSWORD;
    private static Connection connection;







    public DBConnection()throws DataBaseException {

        info = PropertiesFileManager.loadFile();
        DB_DRIVER = info.getDB_DRIVER();
        DB_CONNECTION = info.getDB_CONNECTION();
        DB_USER = info.getDB_USER();
        DB_PASSWORD = info.getDB_PASSWORD();

        connection = openConnection();

    }


    public Connection getConnection() {
        return connection;
    }

    private Connection openConnection() throws DataBaseException {
        try {
            Class.forName(DB_DRIVER);
            return DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
           throw new DataBaseException("Unable to open connection to database",e);
        }

    }


    public void closeDBConnection() throws DataBaseException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DataBaseException("Unable to close the connection to database", e);
            }
        }
    }
}
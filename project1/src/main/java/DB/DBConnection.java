package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/prj1?useUnicode=true"
            + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&"
            + "serverTimezone=UTC";

    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "java4web";
    private Connection connection;

    public DBConnection() {
        try {
            connection = openConnection();
        } catch (Exception e) {
            System.err.println("--- ! Could not connect to data base");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    private Connection openConnection()  {
        try {
            Class.forName(DB_DRIVER);
            return DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        }
        catch (ClassNotFoundException | SQLException e) {
            System.err.println("--- ! Could not connect to data base");
            return null;
        }

    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("--- ! Connection to the database could not be closed");
            }
        }
    }
}

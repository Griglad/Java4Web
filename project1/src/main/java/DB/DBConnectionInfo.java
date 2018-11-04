package DB;

public class DBConnectionInfo {
    private String DB_USER;
    private String DB_DRIVER;
    private String DB_PASSWORD;
    private String DB_CONNECTION;

    public String getDB_USER() {
        return DB_USER;
    }

    public String getDB_DRIVER() {
        return DB_DRIVER;
    }

    public String getDB_PASSWORD() {
        return DB_PASSWORD;
    }

    public String getDB_CONNECTION() {
        return DB_CONNECTION;
    }

    public void setDB_USER(String DB_USER) {
        this.DB_USER = DB_USER;
    }

    public void setDB_DRIVER(String DB_DRIVER) {
        this.DB_DRIVER = DB_DRIVER;
    }

    public void setDB_PASSWORD(String DB_PASSWORD) {
        this.DB_PASSWORD = DB_PASSWORD;
    }

    public void setDB_CONNECTION(String DB_CONNECTION) {
        this.DB_CONNECTION = DB_CONNECTION;
    }
}

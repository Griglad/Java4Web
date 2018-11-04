import DB.DBConnection;
import UI.MenuManager;

public class Main {

    public static void main(String[] args) {

        DBConnection dbConnection = new DBConnection();

        MenuManager menu= new MenuManager();
        menu.runMenu(dbConnection.getConnection());

        dbConnection.closeConnection();
    }
}

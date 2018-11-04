import DB.DBConnection;
import DB.DataBaseException;
import UI.MenuManager;

public class Main {

    public static void main(String[] args) {
        try {

            DBConnection dbConnection = new DBConnection();

            MenuManager menu = new MenuManager();
            menu.runMenu(dbConnection.getConnection());


            dbConnection.closeDBConnection();

        } catch (DataBaseException e) {
            System.err.println(e.getMessage());
        }

    }


}




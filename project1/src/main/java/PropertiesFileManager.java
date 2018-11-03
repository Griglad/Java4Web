import java.io.*;
import java.util.Properties;

public class PropertiesFileManager {

    public static DBConnectionInfo loadFile(){

        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("config.properties");

            prop.load(input);

            DBConnectionInfo dbConnectionInfo= new DBConnectionInfo();
            dbConnectionInfo.setDB_USER(prop.getProperty("DB_USER"));
            dbConnectionInfo.setDB_DRIVER(prop.getProperty("DB_DRIVER"));
            dbConnectionInfo.setDB_PASSWORD(prop.getProperty("DB_PASSWORD"));
            dbConnectionInfo.setDB_CONNECTION(prop.getProperty("DB_CONNECTION"));
            return dbConnectionInfo;

        } catch (IOException ex) {
            ex.printStackTrace();

        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }


}

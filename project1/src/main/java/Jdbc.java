import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class Jdbc {

    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_CONNECTION =  "jdbc:mysql://localhost:3306/prj1?useUnicode=true"
            + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&"
            + "serverTimezone=UTC";

    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "610680";
    private static Connection connection;


    public Jdbc() {
        try {
            connection = getDBConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static Connection getDBConnection() throws Exception {
        Class.forName(DB_DRIVER);
        return DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
    }


    public void closeDBConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }



    public static Vehicle selectVehicleByPlate(String plate)  {
        String show = "select id,plate,owner_id,insurance_exp_date  from vehicle where plate = ?";
        ResultSet resultSet = null;
        Vehicle veh = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(show)) {
            preparedStatement.setString(1, plate);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int owner_id = resultSet.getInt("owner_id");
                String plateNumber = resultSet.getString("plate");
                Date insurance_exp_date = resultSet.getDate("insurance_exp_date");

                veh = new Vehicle(id,owner_id,plateNumber,insurance_exp_date);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (veh==null){
            return null;
        }
        else {
            return veh;
        }
    }

    public Owner selectOwnerById(int id) throws SQLException {
        String show = "select id,last_name,first_name from owner where id = ?";
        ResultSet resultSet = null;
        Owner owner = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(show)) {
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int owner_id = resultSet.getInt("id");
                String lastName = resultSet.getString("last_name");
                String firstName = resultSet.getString("first_name");

                owner = new Owner(owner_id,lastName,firstName);
            }

            if (owner==null){
                return null;
            }
            else {
                return owner;
            }
        }
    }




    public ArrayList<Vehicle> getlistOfAllVehicles() throws SQLException {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        Vehicle veh = null;
        String show = "select *  from vehicle";
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(show))
        {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                int id = resultSet.getInt("id");
                int owner_id = resultSet.getInt("owner_id");
                String plateNumber = resultSet.getString("plate");
                Date insurance_exp_date = resultSet.getDate("insurance_exp_date");

                veh = new Vehicle(id,owner_id,plateNumber,insurance_exp_date);

                vehicles.add(veh);
            }
            return vehicles;

        }

    }

    public ArrayList<Vehicle> getVehiclesByOwnerId(int ownerId) throws SQLException {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        Vehicle veh = null;
        String show = "select *  from vehicle where owner_id=?";
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(show))
        {
            preparedStatement.setInt(1, ownerId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                int id = resultSet.getInt("id");
                int owner_id = resultSet.getInt("owner_id");
                String plateNumber = resultSet.getString("plate");
                Date insurance_exp_date = resultSet.getDate("insurance_exp_date");

                veh = new Vehicle(id,owner_id,plateNumber,insurance_exp_date);

                vehicles.add(veh);
            }
            return vehicles;
        }
    }
}

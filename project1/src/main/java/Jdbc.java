import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class Jdbc {

    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/prj1?useUnicode=true"
            + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&"
            + "serverTimezone=UTC";

    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "java4web";
    private static Connection connection;


    public Jdbc()  {
        try {
            connection = getDBConnection();
        } catch (Exception e) {
            System.out.println("--- ! Could not connect to data base");
        }

    }

    private Connection getDBConnection()  {
            try {
                Class.forName(DB_DRIVER);
                return DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            }
             catch (ClassNotFoundException | SQLException e) {
                 System.out.println("--- ! Could not connect to data base");
                 return null;
            }

    }


    public void closeDBConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("--- ! Connection to the database could not be closed");
            }
        }
    }


    public  Vehicle selectVehicleByPlate(String plate) {
        String show = "select id,plate,owner_id,insurance_exp_date from vehicle where plate = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(show)) {
            preparedStatement.setString(1, plate);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String plateNumber = resultSet.getString("plate");
                Date insurance_exp_date = resultSet.getDate("insurance_exp_date");

                int owner_id = resultSet.getInt("owner_id");
                Owner owner = getOwnerById(owner_id);
                return new Vehicle(id, plateNumber, owner, insurance_exp_date);
            }

            return null;

        } catch (SQLException e) {
            System.out.println("--- ! Data from table vehicle could not be fetched.");
            return null;
        }

    }

    public Owner getOwnerById(int id) {
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

                owner = new Owner(owner_id, lastName, firstName);
            }
            return owner;
        } catch (SQLException e) {
            System.out.println("--- ! Data from table owner could not be fetched.");
            return null;
        }
    }

    public ArrayList<Vehicle> getListOfAllVehicles() {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        Vehicle veh = null;
        String show = "select vehicle.*, owner.first_name, owner.last_name from vehicle " +
                "inner join owner on vehicle.owner_id=owner.id";
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(show)) {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int owner_id = resultSet.getInt("vehicle.owner_id");
                String owner_last_name = resultSet.getString("owner.last_name");
                String owner_first_name = resultSet.getString("owner.first_name");
                Owner owner = new Owner(owner_id,owner_last_name,owner_first_name);

                int id = resultSet.getInt("vehicle.id");
                String plateNumber = resultSet.getString("vehicle.plate");
                Date insurance_exp_date = resultSet.getDate("vehicle.insurance_exp_date");
                veh = new Vehicle(id, plateNumber, owner, insurance_exp_date);

                vehicles.add(veh);
            }
            return vehicles;

        } catch (SQLException e) {
            System.out.println("--- ! Data from table vehicle could not be fetched.");
            return null;
        }
    }

    public ArrayList<Vehicle> getVehiclesByOwnerId(int ownerId) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        Vehicle veh = null;
        String show = "select id, plate, insurance_exp_date from vehicle where owner_id=?";
        ResultSet resultSet = null;

        Owner owner = getOwnerById(ownerId);
        try (PreparedStatement preparedStatement = connection.prepareStatement(show)) {
            preparedStatement.setInt(1, ownerId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String plateNumber = resultSet.getString("plate");
                Date insurance_exp_date = resultSet.getDate("insurance_exp_date");


                veh = new Vehicle(id, plateNumber, owner, insurance_exp_date);

                vehicles.add(veh);
            }
            return vehicles;
        } catch (SQLException e) {
            System.out.println("--- ! Data from table vehicle could not be fetched.");
            return null;
        }
    }
}
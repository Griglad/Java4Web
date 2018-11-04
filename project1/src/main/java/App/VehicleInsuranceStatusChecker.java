package App;

import DB.DataBaseException;
import DB.VehicleFacade;
import Entities.Vehicle;

import java.sql.Connection;


public class VehicleInsuranceStatusChecker {

    public boolean isVehicleInsured(Vehicle vehicle) {

        return vehicle.isInsured();
    }

    public Vehicle getVehicleByPlate(Connection connection, String plateNumbers) throws DataBaseException {

        return new VehicleFacade().selectVehicleByPlate(connection, plateNumbers);
    }
}
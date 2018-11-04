package App;

import DB.OwnerFacade;
import DB.VehicleFacade;
import Entities.Owner;
import Entities.Vehicle;

import java.sql.Connection;
import java.util.ArrayList;

public class TotalFineCalculator {

    public int getTotalFine(ArrayList<Vehicle> vehicles, int fine){

        int sum = 0;
        for (Vehicle v:vehicles) {

            if(!v.isInsured())
                sum+=fine;
        }

        return sum;
    }

    public Owner getOwner(Connection connection, int ownerId){

        return new OwnerFacade().getOwnerById(connection, ownerId);
    }

    public ArrayList<Vehicle> getVehiclesByOwnerId(Connection connection, int ownerId){
        return new VehicleFacade().getVehiclesByOwnerId(connection,ownerId);
    }
}

package UI;

import App.TotalFineCalculator;
import DB.DataBaseException;
import Entities.Owner;

import java.sql.Connection;

class OwnerUIManager {

    Owner readOwnerId(Connection connection) throws DataBaseException {
        while(true){
            int ownerId = new UIManager().readNotNegativeInteger("---Please provide the owner id in order to calculate the total fine cost:");
            Owner owner = new TotalFineCalculator().getOwner(connection, ownerId);
            if(owner!=null){
                return owner;
            }else {
                System.out.println("The given owner id does not exist!");
            }
        }
    }

    void printOwnerWithNoVehicles(Owner owner){
        System.out.println("The owner '" + owner.getLastName() + " " + owner.getFirstName() + "'"
                + " with id '" + owner.getId() + "' has no vehicles.");
    }

    void printFine(Owner owner, int totalFine){
        System.out.println("---The total fine cost that owner '"+owner.getLastName() + " " + owner.getFirstName()
                + "' with id '" + String.valueOf(owner.getId()) + "' is:");
        System.out.printf("%d.%02d", totalFine/100, totalFine%100);
    }
}

package UI;

import App.AboutToExpireInsurances;
import App.VehicleInsuranceStatusChecker;
import App.TotalFineCalculator;
import DB.DataBaseException;
import Entities.Owner;
import Entities.Vehicle;
import Utils.Util;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuManager {

    public void runMenu(Connection connection) throws DataBaseException {

        printAvailableFeatures();

        int choice = getSelectedFeature();

        switch (choice) {
            case 1:
                runCheckInsuranceStatus(connection);
                break;
            case 2:
                runAboutToExpireInsurances(connection);
                break;
            case 3:
               runTotalOwnerFineCalculator(connection);
               break;
        }
    }

    private void printAvailableFeatures(){
        System.out.println(" ------------------------------------");
        System.out.println("|       First Project - Team 3       |");
        System.out.println(" ------------------------------------");
        System.out.println();
        System.out.println("---- Select functionality to perform:");
        System.out.println("* 1) Vehicle Insurance Status");
        System.out.println("* 2) Forecoming Expiries");
        System.out.println("* 3) Calculate Fines");
    }

    private int getSelectedFeature(){
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        boolean flag = true;

        while (flag) {
            choice = Util.readInt(scanner);
            if (choice > 0 && choice < 4) {
                flag = false;
            } else {
                System.out.println("You must select one of the three choices (1,2,3)");
            }
        }

        System.out.println("_____________________________________");
        return choice;
    }

    private void runCheckInsuranceStatus(Connection connection) throws DataBaseException {

        VehicleUIManager vUIManager = new VehicleUIManager();
        Vehicle vehicle = vUIManager.getVehicleByReadingPlate(connection);
        boolean isVehicleInsured = new VehicleInsuranceStatusChecker().isVehicleInsured(vehicle);
        vUIManager.printVehicleInsuranceStatus(isVehicleInsured);
    }

    private void runAboutToExpireInsurances(Connection connection) throws DataBaseException {
        UIManager uiManager = new UIManager();
        AboutToExpireInsurances ateInsurance = new AboutToExpireInsurances();

        int days = uiManager.readDaysOffset();
        boolean isSortSelected = uiManager.readYesNoOption("---Would you like to have the results sorted?");
        ArrayList<Vehicle> aboutToExpireList = new AboutToExpireInsurances().getListOfExpiringInsurances(connection,days,isSortSelected);

        int exportOption = uiManager.readExportOption();
        if(exportOption==1){
            ateInsurance.createCSVfile(aboutToExpireList);
        }
        else {
            new VehicleUIManager().printVehicles(aboutToExpireList, days);
        }
    }

    private void runTotalOwnerFineCalculator(Connection connection) throws DataBaseException {

        TotalFineCalculator totalFineCalculator = new TotalFineCalculator();
        UIManager uiManager = new UIManager();

        int fine = uiManager.readNotNegativeInteger("---Please provide the fine cost of an uninsured vehicle (cents):");

        Owner owner = new OwnerUIManager().readOwnerId(connection);

        ArrayList<Vehicle> vehicles = totalFineCalculator.getVehiclesByOwnerId(connection, owner.getId());

        if(!vehicles.isEmpty()){
            int sum = totalFineCalculator.getTotalFine(vehicles,fine);
            new OwnerUIManager().printFine(owner, sum);
        }
        else{
            new OwnerUIManager().printOwnerWithNoVehicles(owner);
        }
    }
}

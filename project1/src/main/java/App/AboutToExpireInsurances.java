package App;

import DB.VehicleFacade;
import Entities.Vehicle;
import Utils.Util;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

import static Utils.Util.isVehicleInsured;
import static Utils.Util.resultSorting;

public class AboutToExpireInsurances {

    public ArrayList<Vehicle> getListOfExpiringInsurances(Connection connection){

        Scanner scanner = new Scanner(System.in);

        int days = 0;

        boolean flag = true;
        while(flag){
            System.out.println("---Insert a number of days, to see the vehicles that their insurances will be expired:");
            days = Util.ReadInt(scanner, "---Wrong input. Please give an integer for number of days.");
            if(days>-1)
                flag = false;
            else
                System.out.println("---Given integer must not be negative!");
        }


        ArrayList<Vehicle> vehicleList = new VehicleFacade().getListOfAllVehicles(connection);
        ArrayList<Vehicle> aboutToExpireList = new ArrayList<>();


        for(Vehicle v:  vehicleList){
            if(isVehicleInsured(v) && !isVehicleInsured(v,days)){
                aboutToExpireList.add(v);
            }
        }

        resultSorting(aboutToExpireList);


        System.out.println("_____________________________________");
        System.out.println("---Enter Export Type:");
        System.out.println("* 1) File");
        System.out.println("* 2) Console");

        String exportTypeChoice = scanner.nextLine();


        if(exportTypeChoice.equals("1")){
            CsvFileCreator.createCSVfile(aboutToExpireList);
        }
        else if(exportTypeChoice.equals("2")){
            System.out.println("--- The list of plate number that their insurances are about to expire in "+ days +" days");
            System.out.println();

            printVehicles(aboutToExpireList);
        }
        else{
            System.out.println("You must select one of the two choices (1 or 2)");
            secondChoiceSelected();
        }

    }
}

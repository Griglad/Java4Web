package UI;

import App.VehicleInsuranceStatusChecker;
import Entities.Vehicle;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

class VehicleUIManager {

    Vehicle getVehicleByReadingPlate(Connection connection){
        while (true) {
            String plate = readPlate();
            Vehicle targetVehicle = new VehicleInsuranceStatusChecker().getVehicleByPlate(connection, plate);

            if (targetVehicle != null)
                return targetVehicle;
            else
                System.out.println("--- The given plate does not exist! Try another one...");
        }
    }


    String readPlate(){
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("---Please provide the vehicle's plate numbers:");
            String plateNumbers = scanner.nextLine();
            String regex = "^[a-zA-Z]{3}-\\d{4}$";
            if (plateNumbers.matches(regex)) {
                return plateNumbers;
            } else {
                System.out.println("--- The given plate does not follow the correct format!");
            }
        }
    }

    void printVehicleInsuranceStatus(boolean isInsured){
        if(isInsured){
            System.out.println("--- The insurance of the vehicle is valid.");
        }
        else{
            System.out.println("--- The insurance of the vehicle is expired.");
        }
    }

    void printVehicles(ArrayList<Vehicle> vehicles, int days){

        System.out.println("--- The list of plate number that their insurances are about to expire in "+ days +" days");
        System.out.println();

        String leftAlignFormat = "| %8s | %10d | %15s %-15s | %12s |%n";

        System.out.println("+----------+------------+---------------------------------+--------------+");
        System.out.println("| Plate No | Owner's ID |          Owner's Name           | Ins Exp Date |");
        System.out.println("+----------+------------+---------------------------------+--------------+");
        for (Vehicle v : vehicles) {
            System.out.format(leftAlignFormat, v.getPlate(), v.getOwner().getId(), v.getOwner().getLastName(), v.getOwner().getFirstName(), v.getExpiration_date());
        }
        System.out.println("+----------+------------+---------------------------------+--------------+");
    }
}

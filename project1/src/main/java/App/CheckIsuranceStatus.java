package App;

import DB.DBConnection;
import DB.VehicleFacade;
import Entities.Vehicle;

import java.sql.Connection;
import java.util.Scanner;

import static Utils.Util.isVehicleInsured;

public class CheckIsuranceStatus {

    public boolean getInsuranceStatus(Connection connection)

    {

        Scanner scanner = new Scanner(System.in);

        Vehicle targetVehicle = null;
        String plateNumbers = null;
        boolean flag = true;


        while (flag) {
            System.out.println("---Please provide the vehicle's plate numbers:");
            plateNumbers = scanner.nextLine();
            String regex = "^[a-zA-Z]{3}-\\d{4}$";
            if (plateNumbers.matches(regex)) {

                targetVehicle = new VehicleFacade().selectVehicleByPlate(connection,plateNumbers);
                if (targetVehicle != null)
                    flag = false;
                else
                    System.out.println("---The given plate does not exist! Try another one...");
            } else {
                System.out.println("---The given plate does not follow the correct format");
            }
        }
            return isVehicleInsured(targetVehicle);
            //System.out.println("--- The insurance of the vehicle with plate number '" + plateNumbers + "' is valid.");

            //System.out.println("--- The insurance of the vehicle with plate number '" + plateNumbers + "' is expired.");
        }
}
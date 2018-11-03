package UI;

import App.CheckIsuranceStatus;
import Utils.Util;

import java.sql.Connection;
import java.util.Scanner;

public class MenuManager {

    public void runMenu(Connection connection) {
        System.out.println(" ------------------------------------");
        System.out.println("|       First Project - Team 3       |");
        System.out.println(" ------------------------------------");
        System.out.println();
        System.out.println("---- Select functionality to perform:");
        System.out.println("* 1) Entities.Vehicle Insurance Status");
        System.out.println("* 2) Forecoming Expiries");
        System.out.println("* 3) Calculate Fines");
        Scanner scanner = new Scanner(System.in);

        int choice = 0;
        boolean flag = true;
        while (flag) {
            choice = Util.ReadInt(scanner);
            if (choice > 0 && choice < 4) {
                flag = false;
            } else {
                System.out.println("You must select one of the three choices (1,2,3)");
            }
        }

        System.out.println("_____________________________________");

        switch (choice) {
            case 1:
                if(new CheckIsuranceStatus().getInsuranceStatus(connection)==true){
                    System.out.println("--- The insurance of the vehicle is valid.");
                }
                else{
                    System.out.println("--- The insurance of the vehicle is expired.");

                }
            case 2:
                AboutToExpireInsurances
            case 3:
                //third choice
        }
    }

}

import DB.DBConnection;
import Entities.Owner;
import Entities.Vehicle;
import UI.MenuManager;
import Utils.Util;

import java.util.*;

public class Main {

    public static void main(String[] args) throws DataBaseNotFound,SQLeX {

        DBConnection dbConnection = new DBConnection();

        MenuManager menu= new MenuManager();
        menu.runMenu(dbConnection.getConnection());

        dbConnection.closeConnection();
    }
/*
    private static void startingMenu() throws DataBaseNotFound, SQLeX {
        System.out.println("---- Select functionality to perform:");
        System.out.println("* 1) Entities.Vehicle Insurance Status");
        System.out.println("* 2) Forecoming Expiries");
        System.out.println("* 3) Calculate Fines");
        Scanner scanner = new Scanner(System.in);

        String choice = scanner.nextLine();
        System.out.println("_____________________________________");

        if(choice.equals("1")){
            firstChoiceSelected();
        }
        else if(choice.equals("2")){
            secondChoiceSelected();
        }
        else if (choice.equals("3")){
            thirdChoiceSelected();

        }
        else{
            System.out.println("You must select one of the four choices (1,2,3)");
            startingMenu();
        }
    }

    private static void firstChoiceSelected() throws SQLeX, DataBaseNotFound {

        Scanner scanner = new Scanner(System.in);

        Jdbc jdbc = new Jdbc();
        Vehicle targetVehicle = null;
        String plateNumbers = null;
        boolean flag = true;


        while(flag){
            System.out.println("---Please provide the vehicle's plate numbers:");
            plateNumbers = scanner.nextLine();
            String regex="^[a-zA-Z]{3}-\\d{4}$";
            if(plateNumbers.matches(regex)) {
                targetVehicle = jdbc.selectVehicleByPlate(plateNumbers);
                if(targetVehicle!=null)
                    flag = false;
                else
                    System.out.println("---The given plate does not exist! Try another one...");
            }else {
                System.out.println("---The given plate does not follow the correct format");
            }
        }


        jdbc.closeDBConnection();

        if(isVehicleInsured(targetVehicle)){
            System.out.println("--- The insurance of the vehicle with plate number '"+plateNumbers+"' is valid.");
        }
        else {
            System.out.println("--- The insurance of the vehicle with plate number '" + plateNumbers + "' is expired.");
        }
    }


    private static void secondChoiceSelected(){
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

        Jdbc jdbc = new Jdbc();
        ArrayList<Vehicle> vehicleList = jdbc.getListOfAllVehicles();
        ArrayList<Vehicle> aboutToExpireList = new ArrayList<>();
        jdbc.closeDBConnection();


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

    private static void resultSorting(ArrayList<Vehicle> aboutToExpireList){

        Scanner scanner = new Scanner(System.in);
        System.out.println("---Would you like to have the results sorted?");
        System.out.println("--- y : yes   |   n: no");
        String toBeSorted = scanner.nextLine();

        if(toBeSorted.equals("y")){
            // Sort the result list
            Collections.sort(aboutToExpireList);
        }
        else if (toBeSorted.equals("n")){
            // No sorting algorithm will be applied
        }
        else{
            System.out.println("Please choose one of the two valid options: y / n");
            resultSorting(aboutToExpireList);
        }

    }

    private static void printVehicles(ArrayList<Vehicle> vehicles){

        String leftAlignFormat = "| %8s | %10d | %15s %-15s | %12s |%n";

        System.out.println("+----------+------------+---------------------------------+--------------+");
        System.out.println("| Plate No | Entities.Owner's ID |          Entities.Owner's Name           | Ins Exp Date |");
        System.out.println("+----------+------------+---------------------------------+--------------+");
        for(int i=0; i<vehicles.size(); i++){
            Vehicle v = vehicles.get(i);
            System.out.format(leftAlignFormat, v.getPlate(),v.getOwner().getId(), v.getOwner().getLastName(), v.getOwner().getFirstName() ,v.getExpiration_date());
        }
        System.out.println("+----------+------------+---------------------------------+--------------+");
    }

    private static void thirdChoiceSelected(){

        System.out.println("---Please provide the fine cost of an uninsured vehicle (cents):");
        Scanner scanner = new Scanner(System.in);

        int fine = Util.ReadInt(scanner);

        Jdbc jdbc = new Jdbc();
        ArrayList<Vehicle> vehicles = null;
        Owner owner = null;
        while(owner==null){
            System.out.println("---Please provide the owner id in order to calculate the total fine cost:");
            int ownerId = Util.ReadInt(scanner);
            owner = jdbc.getOwnerById(ownerId);

            if(owner==null){
                System.out.println("The given owner id does not exist!");
                continue;
            }

            vehicles = jdbc.getVehiclesByOwnerId(ownerId);

            if(vehicles.isEmpty()){
                System.out.println("The owner '" + owner.getLastName() + " " + owner.getFirstName() + "'"
                        + " with id '" + ownerId + "' has no vehicles.");
                System.exit(0);
            }
        }


        jdbc.closeDBConnection();

        int sum = 0;
        for (Vehicle v:vehicles) {

            if(!isVehicleInsured(v))
                sum+=fine;
        }

        System.out.println("---The total fine cost that owner '"+owner.getLastName() + " " + owner.getFirstName()
                + "' with id '" + String.valueOf(owner.getId()) + "' is:");
        System.out.printf("%d.%02d", sum/100, sum%100);

    }

   */


}

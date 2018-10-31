import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.Collections;

public class Main {

    static int a=5;

    public static void main(String[] args){

        System.out.println(" ------------------------------------");
        System.out.println("|       First Project - Team 3       |");
        System.out.println(" ------------------------------------");

        System.out.println();

        startingMenu();

    }

    public static void startingMenu(){
        System.out.println("---- Select functionality to perform:");
        System.out.println("* 1) Vehicle Insurance Status");
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

    public static void firstChoiceSelected(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("---Please provide the vehicle's plate numbers:");
        String plateNumbers = scanner.nextLine();

        String regex="^[a-zA-Z]{3}-\\d{4}$";

        if(plateNumbers.matches(regex)){
            Vehicle targetVehicle=null ;
            //Vehicle targetVehicle=returnTargetVeh(plateNumbers); // Return the insurance status of the given plate
            if(targetVehicle.getExpiration_date().compareTo(new Date())==1){
                System.out.println("--- The insurance of the vehicle with plate number "+plateNumbers+" is expired");
            }
            else{
                System.out.println("--- The insurance of the vehicle with plate number "+plateNumbers+" is valid");

            }
        }
        else{
            System.out.println("---The given plate does not follow the correct format");
            firstChoiceSelected();
        }


    }

    private static void secondChoiceSelected(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Insert an expiring date in the following format yyyy/mm/dd:");
        String expiringDate = scanner.nextLine();

        // meterepse se date logika

        ArrayList<Vehicle> aboutToExpireList=new ArrayList<Vehicle>();


        // Gemise tin lista kalodas jdbc

        resultSorting(aboutToExpireList);


        System.out.println("_____________________________________");
        System.out.println("---Enter Export Type:");
        System.out.println("* 1) File");
        System.out.println("* 2) Console");

        String choice = scanner.nextLine();


        if(choice.equals("1")){
            CsvFileCreator.createCSVfile(aboutToExpireList);
        }
        else if(choice.equals("2")){
            System.out.println("--- The list of plate number that their insurances are about to expire until "+ expiringDate);
            System.out.println();
            System.out.println(" Vehicle's ID | Plate Number | Owner ID | Insurance Expiration Date");
            for(int i=0; i<aboutToExpireList.size(); i++){
                System.out.println(aboutToExpireList.get(i).getId());
                System.out.println(aboutToExpireList.get(i).getPlate());
                System.out.println(aboutToExpireList.get(i).getOwner_id());
                System.out.println(aboutToExpireList.get(i).getExpiration_date());
            }

        }
        else{
            System.out.println("You must select one of the three choices (1,2,3)");
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


    private static void thirdChoiceSelected(){

        Scanner scanner = new Scanner(System.in);

        System.out.println();
        String motorbikeFine = scanner.nextLine();



    }

}

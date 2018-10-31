import java.util.ArrayList;
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

    public static void startingMenu(){ // The starting menu of the application
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

    public static void firstChoiceSelected(){ // Vehicle insurance status
        Scanner scanner = new Scanner(System.in);

        System.out.println("---Please provide the vehicle's plate numbers:");
        String plateNumbers = scanner.nextLine();

        String regex="^[a-zA-Z]{3}-\\d{4}$";

        if(plateNumbers.matches(regex)){ // If the given plate number matches the correct format
            //returnVehiclesInsStat(plateNumbers); // Return the insurance status of the given plate
        }
        else{
            System.out.println("---The given plate does not follow the correct format");
            firstChoiceSelected();
        }


    }

    private static void secondChoiceSelected(){  // Display vehicles’  that their Insurances are about to expire

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> aboutToExpireList=new ArrayList<String>();

        aboutToExpireList.add("abd-123");
        aboutToExpireList.add("abc-123");
        aboutToExpireList.add("abc-122");
        // Gemise tin lista kalodas jdbc

        resultSorting(aboutToExpireList); // Result check for sorting and sort execution


        System.out.println("_____________________________________");

        System.out.println("---Enter Export Type:");
        System.out.println("* 1) File");
        System.out.println("* 2) Console");

        String choice = scanner.nextLine();


        if(choice.equals("1")){ // Create a csv file
            //createFile();
        }
        else if(choice.equals("2")){ // Print in console
            System.out.println(aboutToExpireList.get(0));
            System.out.println(aboutToExpireList.get(1));
            System.out.println(aboutToExpireList.get(2));
        }
        else{
            System.out.println("You must select one of the three choices (1,2,3)");
            secondChoiceSelected();
        }

    }

    private static void resultSorting(ArrayList<String> aboutToExpireList){ // If requested, sort the results

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


    private static void thirdChoiceSelected(){ // Fine calculation per owner

        Scanner scanner = new Scanner(System.in);

        System.out.println();
        String motorbikeFine = scanner.nextLine();



    }

}

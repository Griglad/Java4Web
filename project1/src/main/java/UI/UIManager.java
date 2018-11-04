package UI;

import Utils.Util;

import java.util.Scanner;

class UIManager {

    int readDaysOffset(){
        Scanner scanner = new Scanner(System.in);
        int days;

        while(true){
            System.out.println("---Insert a number of days, to see the vehicles that their insurances will be expired:");
            days = Util.readInt(scanner, "---Wrong input. Please give an integer for number of days.");
            if(days>-1)
                return days;
            else
                System.out.println("---Given integer must not be negative!");
        }
    }

    private void printAvailableOutputOptions(){
        System.out.println("_____________________________________");
        System.out.println("---Enter Export Type:");
        System.out.println("* 1) File");
        System.out.println("* 2) Console");
    }

    int readExportOption(){
        while(true){
            printAvailableOutputOptions();

            Scanner scanner = new Scanner(System.in);
            int exportTypeChoice = Util.readInt(scanner);

            if(exportTypeChoice>0 && exportTypeChoice< 3)
                return exportTypeChoice;
            else
                System.out.println("You must select one of the two choices (1 or 2)");
        }
    }

    boolean readYesNoOption(String message){

        while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.println(message);
            System.out.println("--- y : yes   |   n: no");
            String toBeSorted = scanner.nextLine();

            if(toBeSorted.equalsIgnoreCase("y") || toBeSorted.equalsIgnoreCase("n")) {
                return toBeSorted.equalsIgnoreCase("y");
            }
            else{
                System.out.println("Please choose one of the two valid options: y / n");
            }
        }
    }

    int readNotNegativeInteger(String message){
        while(true){
            System.out.println(message);
            Scanner scanner = new Scanner(System.in);

            int num = Util.readInt(scanner);
            if(num>-1)
                return num;
            else
                System.out.println("Number must not be negative!");
        }
    }
}

import java.sql.SQLException;
import java.util.*;

public class Main {

    static int a=5;

    public static void main(String[] args) throws DataBaseNotFound,SQLeX {

        System.out.println(" ------------------------------------");
        System.out.println("|       First Project - Team 3       |");
        System.out.println(" ------------------------------------");

        System.out.println();

        startingMenu();

    }

    private static void startingMenu() throws DataBaseNotFound, SQLeX {
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

    private static void firstChoiceSelected() throws SQLeX, DataBaseNotFound {
        Jdbc jdbc = new Jdbc();
        Scanner scanner = new Scanner(System.in);

        System.out.println("---Please provide the vehicle's plate numbers:");
        String plateNumbers = scanner.nextLine();

        String regex="^[a-zA-Z]{3}-\\d{4}$";

        if(plateNumbers.matches(regex)){

            Vehicle targetVehicle=jdbc.selectVehicleByPlate(plateNumbers);

            if(targetInsExpired(targetVehicle)==true){
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

    private static boolean targetInsExpired(Vehicle targetVehicle){
        if(targetVehicle.getExpiration_date().compareTo(new Date())==1){
            return true;
        }
        else{
            return false;
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


    private static void thirdChoiceSelected() throws DataBaseNotFound {

        System.out.println("---Please provide the fine cost of an uninsured vehicle (cents):");
        Scanner scanner = new Scanner(System.in);
        int fine = Integer.valueOf(scanner.nextLine());

        System.out.println("---Please provide the owner's id in order to calculate the total fine cost:");
        int ownerId = Integer.valueOf(scanner.nextLine());

        Jdbc jdbc = new Jdbc();

        ArrayList<Vehicle> vehicles = null;
        try {
            vehicles = jdbc.getVehiclesByOwnerId(ownerId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int sum = 0;
        for (Vehicle v:vehicles) {

            if(!isVehicleInsured(v))
                sum+=fine;
        }

        System.out.println("The total fine cost that owner with id " + String.valueOf(ownerId) + " is: " + String.valueOf(sum));

        try {
            jdbc.closeDBConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println();
        String motorbikeFine = scanner.nextLine();



    }

    private static boolean isVehicleInsured(Vehicle v, int daysOffset){

        if(v.getExpiration_date()==null)
            return false;
        else{
            Calendar dateForComparison = Calendar.getInstance();
            if(daysOffset!=0)
                dateForComparison.add(Calendar.DATE, daysOffset);

            Calendar insuranceExpDate = Calendar.getInstance();
            insuranceExpDate.setTime(v.getExpiration_date());

            return v.getExpiration_date()!=null && !Util.isAfterDate(insuranceExpDate,dateForComparison);
        }
    }

    private static boolean isVehicleInsured(Vehicle v){
        return isVehicleInsured(v, 0);
    }

}

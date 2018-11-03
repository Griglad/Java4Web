package Utils;

import Entities.Vehicle;

import java.util.*;

public final class Util {

    public static boolean isBeforeDate(Calendar dateToCheck, int daysOffsetFromToday){

        Calendar dateForComparison = Calendar.getInstance();
        if(daysOffsetFromToday==0)
            dateForComparison.add(Calendar.DATE, -1);
        else
            dateForComparison.add(Calendar.DATE, daysOffsetFromToday-1);

        return dateToCheck.before(dateForComparison);
    }

    public static Calendar toCalendar(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static Integer ReadInt(Scanner scanner, String message){

        boolean flag = true;
        int num =0;
        do
        {
            try
            {
                num = scanner.nextInt();
                flag=false;
            }
            catch (InputMismatchException exception)
            {
                if(message==null)
                    System.out.println("Please give an integer.");
                else
                    System.out.println(message);

            }finally {
                scanner.nextLine();
            }
        }
        while (flag);
        return num;
    }

    public static boolean isVehicleInsured(Vehicle v, int daysOffset){

        if(v.getExpiration_date()==null)
            return false;
        else{
            return !Util.isBeforeDate(Util.toCalendar(v.getExpiration_date()),daysOffset);
        }
    }

    public static boolean isVehicleInsured(Vehicle v){
        return isVehicleInsured(v, 0);
    }

    public static void resultSorting(ArrayList<Vehicle> aboutToExpireList){

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

//    public static Integer ReadInt(Scanner scanner, String message){
//
//        boolean flag = true;
//        int num =0;
//        do
//        {
//
//            if(scanner.hasNextLine()){
//                num = scanner.nextInt();
//                flag = false;
//            }else{
//                if(message==null)
//                    System.out.println("Please give an integer.");
//                else
//                    System.out.println(message);
//                scanner.nextLine();
//            }
//        }
//        while (flag);
//        return num;
//    }

    public static Integer ReadInt(Scanner scanner){

        return ReadInt(scanner,null);
    }
}

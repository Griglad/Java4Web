package Utils;

import java.util.*;

public final class Util {

    public static boolean isBeforeDate(Calendar dateToCheck, int daysOffsetFromToday) {

        Calendar dateForComparison = Calendar.getInstance();
        if (daysOffsetFromToday == 0)
            dateForComparison.add(Calendar.DATE, -1);
        else
            dateForComparison.add(Calendar.DATE, daysOffsetFromToday - 1);

        return dateToCheck.before(dateForComparison);
    }

    public static Calendar toCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static Integer readInt(Scanner scanner) {

        return readInt(scanner, null);
    }

    public static Integer readInt(Scanner scanner, String message) {

        boolean flag = true;
        int num = 0;
        do {
            try {
                num = scanner.nextInt();
                flag = false;
            } catch (InputMismatchException exception) {
                if (message == null)
                    System.out.println("Please give an integer.");
                else
                    System.out.println(message);

            } finally {
                scanner.nextLine();
            }
        }
        while (flag);
        return num;
    }
}
import java.util.Calendar;
import java.util.Date;

public final class Util {

    public static boolean isAfterDate(Calendar dateToCheck, Calendar dateForComparison){
        return dateToCheck.after(dateForComparison);
    }
}

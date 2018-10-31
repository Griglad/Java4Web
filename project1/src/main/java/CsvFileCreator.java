import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class CsvFileCreator{


    public static void  createCSVfile(ArrayList<Vehicle> insToExpireList)  {


        PrintWriter pw=null;
        String fileName="InsToExpire.csv";

        try {
            pw = new PrintWriter(new File(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("--! The file could not be created");
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Vehicle's ID");
        sb.append(',');
        sb.append("Plate Number");
        sb.append(',');
        sb.append("Owner ID");
        sb.append(',');
        sb.append("Insurance Expiration Date");
        sb.append('\n');

        for(int i=0;i<insToExpireList.size();i++) {
            sb.append(insToExpireList.get(i));
            sb.append(',');
            sb.append(insToExpireList.get(i));
            sb.append(',');
            sb.append(insToExpireList.get(i));
            sb.append(',');
            sb.append(insToExpireList.get(i));
            sb.append('\n');

        }
        pw.write(sb.toString());
        pw.close();
        System.out.println("--- The file "+fileName+" has been created on "+System.getProperty("user.dir"));

    }

}

import Entities.Vehicle;

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
        sb.append("Entities.Vehicle's ID");
        sb.append(',');
        sb.append("Plate Number");
        sb.append(',');
        sb.append("Entities.Owner's ID");
        sb.append(',');
        sb.append("Entities.Owner's Last Name");
        sb.append(',');
        sb.append("Entities.Owner's First Name");
        sb.append(',');
        sb.append("Insurance Expiration Date");
        sb.append('\n');

        for(int i=0;i<insToExpireList.size();i++) {
            sb.append(insToExpireList.get(i).getId());
            sb.append(',');
            sb.append(insToExpireList.get(i).getPlate());
            sb.append(',');
            sb.append(insToExpireList.get(i).getOwner().getId());
            sb.append(',');
            sb.append(insToExpireList.get(i).getOwner().getLastName());
            sb.append(',');
            sb.append(insToExpireList.get(i).getOwner().getFirstName());
            sb.append(',');
            sb.append(insToExpireList.get(i).getExpiration_date());
            sb.append('\n');

        }
        pw.write(sb.toString());
        pw.close();
        System.out.println("--- The file "+fileName+" has been created on "+System.getProperty("user.dir"));

    }

}

package App;

import DB.VehicleFacade;
import Entities.Vehicle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;

public class AboutToExpireInsurances {

    public ArrayList<Vehicle> getListOfExpiringInsurances(Connection connection, int days, boolean isSortSelected){

        ArrayList<Vehicle> aboutToExpireList = new ArrayList<>();
        ArrayList<Vehicle> vehicleList = new VehicleFacade().getListOfAllVehicles(connection);

        for(Vehicle v:  vehicleList){
            if(v.isInsured() && !v.isInsured(days)){
                aboutToExpireList.add(v);
            }
        }

        if(isSortSelected)
            Collections.sort(aboutToExpireList);

        return aboutToExpireList;
    }

    public void  createCSVfile(ArrayList<Vehicle> insToExpireList)  {

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

        for (Vehicle v : insToExpireList) {
            sb.append(v.getId());
            sb.append(',');
            sb.append(v.getPlate());
            sb.append(',');
            sb.append(v.getOwner().getId());
            sb.append(',');
            sb.append(v.getOwner().getLastName());
            sb.append(',');
            sb.append(v.getOwner().getFirstName());
            sb.append(',');
            sb.append(v.getExpiration_date());
            sb.append('\n');

        }
        if (pw != null) {
            pw.write(sb.toString());
            pw.close();
            System.out.println("--- The file "+fileName+" has been created on "+System.getProperty("user.dir"));
        }else{
            System.err.println("--- ! Something went wrong with the exporting to csv file.");
        }
    }
}

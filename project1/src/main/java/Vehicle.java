import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


public class Vehicle implements Comparable<Vehicle>{
    private String plate;
    private int owner_id;
    private int id;
    private Date expiration_date;

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getPlate() {
        return plate;
    }

    public Date getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(Date expiration_date) {
        this.expiration_date = expiration_date;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Vehicle(int id, int owner_id, String plate, Date expiration_date) {
        this.plate = plate;
        this.owner_id = owner_id;
        this.id = id;
        this.expiration_date = expiration_date;


    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "plate='" + plate + '\'' +
                ", owner_id=" + owner_id +
                ", id=" + id +
                ", expiration_date=" + expiration_date +
                '}';
    }

    public int compareTo(Vehicle o) {// kane to implemenation

        return this.plate.compareTo(o.plate);

    }
}



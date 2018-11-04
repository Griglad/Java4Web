package Entities;

import Utils.Util;

import java.util.Date;


public class Vehicle implements Comparable<Vehicle>{
    private String plate;
    private Owner owner;
    private int id;
    private Date expiration_date;

    public String getPlate() {
        return plate;
    }

    public Date getExpiration_date() {
        return expiration_date;
    }

    public Owner getOwner() {
        return owner;
    }

    public int getId() {
        return id;
    }


    public Vehicle(int id,  String plate,Owner owner, Date expiration_date) {
        this.plate = plate;
        this.owner = owner;
        this.id = id;
        this.expiration_date = expiration_date;
    }

    public boolean isInsured(int daysOffset){

        if(getExpiration_date()==null)
            return false;
        else{
            return !Util.isBeforeDate(Util.toCalendar(getExpiration_date()),daysOffset);
        }
    }

    public boolean isInsured(){
        return isInsured(0);
    }

    @Override
    public String toString() {
        return "Entities.Vehicle{" +
                "plate='" + plate + '\'' +
                ", " + owner.toString() +
                ", id=" + id +
                ", expiration_date=" + expiration_date +
                '}';
    }

    public int compareTo(Vehicle o) {

        return this.plate.compareTo(o.plate);

    }
}



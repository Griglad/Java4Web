package Entities;

import Utils.Util;

import java.util.Date;


public class Vehicle implements Comparable<Vehicle>{
    private String plate;
    private Owner owner;
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

    public Owner getOwner() {
        return owner;
    }

    public void setOwner_id(Owner owner) {
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Vehicle(int id,  String plate,Owner owner, Date expiration_date) {
        this.plate = plate;
        this.owner = owner;
        this.id = id;
        this.expiration_date = expiration_date;


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

    public int compareTo(Vehicle o) {// kane to implemenation

        return this.plate.compareTo(o.plate);

    }

}



public class Owner {


    private int id;
    private String last_name;
    private String first_name;

    public Owner(int id, String last_name, String first_name) {
        this.id = id;
        this.last_name = last_name;
        this.first_name = first_name;
    }


    public int getId() {
        return id;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "last_name='" + last_name + '\'' +
                ", first_name='" + first_name + '\'' +
                '}';
    }

}
import java.sql.SQLException;

class SQLeX extends SQLException{



    public SQLeX()
    {
        super("No Data Found in Select ",new  SQLException());


    }







}
class DataBaseNotFound extends ClassNotFoundException
{



    public DataBaseNotFound(){


        super("DataBase to connect is not found",new ClassNotFoundException());



    }




}


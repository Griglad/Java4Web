package DB;

import Entities.Owner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OwnerFacade {

    public Owner getOwnerById(Connection dbConnection, int id)throws DataBaseException {
        String show = "select id,last_name,first_name from owner where id = ?";
        Owner owner = null;
        try (PreparedStatement preparedStatement = dbConnection.prepareStatement(show)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int owner_id = resultSet.getInt("id");
                String lastName = resultSet.getString("last_name");
                String firstName = resultSet.getString("first_name");

                owner = new Owner(owner_id, lastName, firstName);
            }
            return owner;
        } catch (SQLException e) {

            throw new DataBaseException("Unable to get vehicles by owner id",e);
        }
    }
}

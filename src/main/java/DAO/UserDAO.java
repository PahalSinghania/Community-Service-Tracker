package DAO;
import Model.*;
import java.sql.*;

import static DAO.superDAO.registerDriver;

public class UserDAO {

    public static void addUser(AccessType accessType, String firstName, String lastName, String userName,
                               String email) {
        try {
            // Register Driver
            PreparedStatement statement = registerDriver().prepareStatement(
                    "INSERT INTO `user`(`first_name`,`last_name`, `email`, `username`, `access_type`) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, email);
            statement.setString(4, userName);
            statement.setString(5, accessType.name());
            statement.executeUpdate();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.err.println("Problem Connecting!");
        }
    }
}

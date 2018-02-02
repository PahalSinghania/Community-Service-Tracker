package DAO;
import java.sql.*;

public class superDAO {

    public static Connection registerDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String myUrl = "jdbc:mysql://localhost:3306/nuACES";
            Connection connection = DriverManager.getConnection(myUrl, "root", ""); // Password for ubuntu root is teamgreat; input as second string

            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

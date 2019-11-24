package Database;

import java.sql.*;

public class DBconnect {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    /**
     * Method that establishes connection to the mysql database.
     */

    public DBconnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://projects-db.ewi.tudelft.nl/projects_Snake1?"
                            + "useUnicode=true&characterEncoding=utf8&use"
                            + "SSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "pu_Snake1", "tHWLSWJqg57E");
            statement = connection.createStatement();
        } catch (Exception exception) {
            System.out.println("Error: " + exception);
        }
    }

    /**
     * Sample query method to get all the records in the user table.
     */

    public void getData() {
        try {
            String query = "SELECT * FROM users";
            resultSet = statement.executeQuery(query);
            System.out.println("Records:");
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                System.out.println("Username: " + username + "      password: " + password);
            }

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    

}
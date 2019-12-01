package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import lombok.Getter;
import lombok.Setter;

public class DBconnect {

    @Getter @Setter private  Connection connection;
    @Getter @Setter private  Statement statement;
    @Getter @Setter private  ResultSet resultSet;

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
    public ResultSet getData() {
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

        return resultSet;
    }

    /**
     * This method checks the database if the entered username and password are valid.
     * @param username - the username
     * @param password - the password
     * @return - true iff login is successful
     */
    public boolean loginData(String username, String password) {
        try {
            String checkUser = "SELECT * FROM users WHERE username='" + username
                    + "' && password='" + password + "'";
            resultSet = statement.executeQuery(checkUser);
            if (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    /**
     * This method checks if a username is already taken.
     * New users are registered by adding username and password to the database.
     * @param username - the username
     * @param password - the password
     * @return - true iff user is successfully registered
     */
    public boolean registerUser(String username, String password) {
        try {
            String usernameCheck = "SELECT * FROM users WHERE username='" + username + "'";
            resultSet = statement.executeQuery(usernameCheck);
            if (resultSet.next()) {
                return false;
            }
            String insertUser = "INSERT INTO users (username,password) VALUES ('" + username
                    + "','" + password + "')";
            statement.executeUpdate(insertUser);
            String checkUser = "SELECT * FROM users WHERE username='" + username
                    + "' && password='" + password + "'";
            resultSet = statement.executeQuery(checkUser);
            if (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

}
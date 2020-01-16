package database;

import gui.controller.PasswordHash;
import java.sql.*;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;




public class DBconnect {

    @Getter @Setter private Connection connection;
    @Getter @Setter private Statement statement;
    @Getter @Setter private ResultSet resultSet;
    @Getter @Setter private PreparedStatement preparedStatement;

    /**
     * Method that establishes connection to the mysql database.
     */

    public DBconnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            StringBuilder url = new StringBuilder();
            url.append("jdbc:mysql://projects-db.ewi.tudelft.nl/projects_Snake1?");
            url.append("useUnicode=true&characterEncoding=utf8&use");
            url.append("SSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
            connection = DriverManager.getConnection(url.toString(),
                "pu_Snake1", "tHWLSWJqg57E");
            statement = connection.createStatement();
        } catch (Exception exception) {
            System.out.println("DBconnect" + exception);
        }
    }

    /**
     * This method closes connections with the database.
     */
    public void closeConnections() {
        try {
            resultSet.close();
        } catch (Exception e) {
            System.out.println("closeConnections" + e);
        }
        try {
            preparedStatement.close();
        } catch (Exception e) {
            System.out.println("closeConnections" + e);
        }
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println("closeConnections" + e);
        }
    }

    /**
     * Sample query method to get all the records in the user table.
     */
    public ResultSet getData() {
        try {
            String query = "SELECT * FROM users";
            resultSet = preparedStatement.executeQuery(query);
            System.out.println("Records:");
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                System.out.println("Username: " + username + "      password: " + password);
            }

        } catch (Exception exception) {
            System.out.println("getData" + exception);
        } finally {
            closeConnections();
        }
        return resultSet;
    }

    /**
     * This method checks the database if the entered username and password are valid.
     * @param username - the username
     * @param password - the password
     * @param pwdHash - the hash of the password, if it already exists
     * @return - true iff login is successful
     */
    public boolean authenticate(String username, String password, PasswordHash pwdHash) {
        if (pwdHash == null) {
            pwdHash = new PasswordHash(password);
        }
        try {
            String checkUser = "SELECT password FROM users WHERE username = ?";
            preparedStatement = connection.prepareStatement(checkUser);
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();
            resultSet.first();
            if (pwdHash.validatePassword(resultSet.getString("password"))) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("authenticate" + e);
        } finally {
            closeConnections();
        }
        return false;
    }

    /**
     * Checks if the user exists.
     * @param username - username of the player
     * @return - true iff user exists else false
     */
    public boolean usernameCheck(String username) {
        try {
            String usernameCheck = "SELECT username FROM users WHERE username = ?";
            preparedStatement = connection.prepareStatement(usernameCheck);
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("usernameCheck" + e);
        } finally {
            closeConnections();
        }
        return false;
    }

    /**
     * This method checks if a username is already taken.
     * New users are registered by adding username and password to the database.
     * @param username - the username
     * @param password - the password
     * @param pwdHash - the hash of the password, if it already exists
     * @return - true iff user is successfully registered
     */
    public boolean registerUser(String username, String password, PasswordHash pwdHash) {

        if (pwdHash == null) {
            pwdHash = new PasswordHash(password);
        }
        try {
            if (usernameCheck(username)) {
                return false;
            }
            String hashed = pwdHash.createHash();
            String insertUser = "INSERT INTO users (username,password) VALUES (?,?)";
            preparedStatement = connection.prepareStatement(insertUser);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,hashed);
            preparedStatement.executeUpdate();
            if (pwdHash.validatePassword(hashed)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("registerUser" + e);
        } finally {
            closeConnections();
        }
        return false;
    }


    /**
     * This method saves the score of the user when the game ends.
     * @param username - the username of the player
     * @param score - the score of the user for that game
     */
    public void saveScore(String username, int score, String nickname) {
        try {
            if (usernameCheck(username)) {
                String insertScore = "INSERT INTO scores (username,score,nickname) VALUES (?,?,?)";
                preparedStatement = connection.prepareStatement(insertScore);
                preparedStatement.setString(1,username);
                preparedStatement.setInt(2,score);
                preparedStatement.setString(3,nickname);
                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("saveScore" + e);
        } finally {
            closeConnections();
        }
    }

    /**
     * This method returns the scores in descending order.
     * It also returns the usernames associated to it.
     * @param list - arraylist of usernames and scores.
     */
    public void getGlobalScores(ArrayList<GlobalDetails> list) {
        try {
            int position = 1;
            String highScores = "SELECT username,score FROM scores ORDER BY score DESC";
            preparedStatement = connection.prepareStatement(highScores);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new GlobalDetails(
                        position,
                        resultSet.getString("username"),
                        resultSet.getInt("score")));
                position += 1;
            }
        } catch (Exception e) {
            System.out.println("getGlobalScores" + e);
        } finally {
        //    closeConnections();
        }
    }

    /**
     * Returns the highest scores of the current user in descending order.
     * Also returns the nicknames associated to the score.
     * @param list2 - list of personal scores and nicknames.
     * @param username - username of the current user.
     */
    public void getPersonalScores(ArrayList<PersonalDetails> list2, String username) {
        try {
            int position = 1;
            String personalScores = "SELECT nickname,score FROM scores WHERE username = ? "
                    + "ORDER BY score DESC";
            preparedStatement = connection.prepareStatement(personalScores);
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list2.add(new PersonalDetails(
                        position,
                        resultSet.getInt("score"),
                        resultSet.getString("nickname")));
                position += 1;
            }
        } catch (Exception e) {
            System.out.println("getPersonalScores " + e);
        } finally {
            closeConnections();
        }
    }



}
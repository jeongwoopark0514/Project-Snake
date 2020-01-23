package database;

import com.mysql.cj.jdbc.MysqlDataSource;
import gui.controller.PasswordHash;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class DBconnect {

    @Getter
    @Setter
    private Connection connection;
    @Getter
    @Setter
    private Statement statement;
    @Getter
    @Setter
    private ResultSet resultSet;
    @Getter
    @Setter
    private PreparedStatement preparedStatement;
    private String prefix = "Error: ";
    private int globalPosition = 1;
    private int personalPosition = 1;

    private static DBconnect INSTANCE;
    @Setter
    private MysqlDataSource ds;

    /**
     * First time this method is called an instance of DBconnect will be created. Subsequent
     * times reference to this instance will be returned.
     * Implements the Singleton design pattern.
     *
     * @return Reference to database.
     */
    public static DBconnect getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DBconnect();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }

    /**
     * Instantiate the datasource which can be used to create a connection.
     */
    public DBconnect() {
        ds = new MysqlDataSource();
        StringBuilder url = new StringBuilder();
        url.append("jdbc:mysql://projects-db.ewi.tudelft.nl/projects_Snake1?");
        url.append("useUnicode=true&characterEncoding=utf8&use");
        url.append("SSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
        ds.setUrl(url.toString());
        ds.setUser("pu_Snake1");
        ds.setPassword("tHWLSWJqg57E");
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
            System.out.println(prefix + exception);
        }

        return resultSet;
    }

    /**
     * Open the connection of the set datasource.
     */
    public void openConnection() {
        try {
            connection = ds.getConnection();
        } catch (SQLException e) {
            System.out.println(prefix + e);
        }
    }

    /**
     * Close the connection with the database.
     */
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(prefix + e);
        }
    }

    /**
     * This method checks the database if the entered username and password are valid.
     *
     * @param username - the username
     * @param password - the password
     * @param pwdHash  - the hash of the password, if it already exists
     * @return - true iff login is successful
     */
    public boolean authenticate(String username, String password, PasswordHash pwdHash) {

        if (pwdHash == null) {
            pwdHash = new PasswordHash(password);
        }

        try {
            String checkUser = "SELECT password FROM users WHERE username = ?";
            preparedStatement = connection.prepareStatement(checkUser);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            resultSet.first();
            if (pwdHash.validatePassword(resultSet.getString("password"))) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(prefix + e);
        }
        return false;
    }

    /**
     * Checks if the user exists.
     *
     * @param username - username of the player
     * @return - true iff user exists else false
     */
    public boolean usernameCheck(String username) {
        try {
            String usernameCheck = "SELECT username FROM users WHERE username = ?";
            preparedStatement = connection.prepareStatement(usernameCheck);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(prefix + e);
        }
        return false;
    }

    /**
     * This method checks if a username is already taken.
     * New users are registered by adding username and password to the database.
     *
     * @param username - the username
     * @param password - the password
     * @param pwdHash  - the hash of the password, if it already exists
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
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, hashed);
            preparedStatement.executeUpdate();

            if (pwdHash.validatePassword(hashed)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(prefix + e);
        }
        return false;
    }

    /**
     * Store a created cookie in the database, together with the username.
     *
     * @param username the username of the user.
     * @param cookie   the actual cookie that needs to be stored.
     * @return true if successfully stored, false if not.
     */
    public boolean storeCookie(String username, String cookie) {
        try {
            String insertCookie = "INSERT INTO sessions (cookie, username) VALUES (?,?)";
            preparedStatement = connection.prepareStatement(insertCookie);
            preparedStatement.setString(1, cookie);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(prefix + e);
            return false;
        }
    }

    /**
     * Get the username from the database, given a specific cookie.
     *
     * @param cookie the cookie to retrieve the username with.
     * @return returns the username if found or null of not.
     */
    public String getUsername(String cookie) {
        try {
            String getCookie = "SELECT username FROM sessions WHERE cookie = ?";
            preparedStatement = connection.prepareStatement(getCookie);
            preparedStatement.setString(1, cookie);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("username");
            }
        } catch (Exception e) {
            System.out.println(prefix + e);
        }
        return null;
    }


    /**
     * This method saves the score of the user when the game ends.
     *
     * @param username - the username of the player
     * @param score    - the score of the user for that game
     */
    public void saveScore(String username, int score, String nickname) {
        try {
            if (usernameCheck(username)) {
                String insertScore = "INSERT INTO scores (username,score,nickname) VALUES (?,?,?)";
                preparedStatement = connection.prepareStatement(insertScore);
                preparedStatement.setString(1, username);
                preparedStatement.setInt(2, score);
                preparedStatement.setString(3, nickname);
                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(prefix + e);
        }
    }

    /**
     * This return the scores in descending order together with the usernames.
     *
     * @param list the list of usernames and scores.
     */
    public void getGlobalScores(ArrayList<GlobalDetails> list) {
        try {
            String highScores = "SELECT username,score FROM scores ORDER BY score DESC";
            preparedStatement = connection.prepareStatement(highScores);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new GlobalDetails(
                    globalPosition,
                    resultSet.getString("username"),
                    resultSet.getInt("score")));
                globalPosition += 1;
            }
        } catch (Exception e) {
            System.out.println(prefix + e);
        }
        globalPosition = 1;
    }

    /**
     * Returns the highest scores of the current user in descending order.
     * Also returns the nicknames associated to the score.
     *
     * @param list     - list of personal scores and nicknames.
     * @param username - username of the current user.
     */
    public void getPersonalScores(ArrayList<PersonalDetails> list, String username) {
        try {
            String personalScores = "SELECT nickname,score FROM scores WHERE username = ? "
                + "ORDER BY score DESC";
            preparedStatement = connection.prepareStatement(personalScores);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new PersonalDetails(
                    personalPosition,
                    resultSet.getInt("score"),
                    resultSet.getString("nickname")));
                personalPosition += 1;
            }
            personalPosition = 1;
        } catch (Exception e) {
            System.out.println(prefix + e);
        }
    }

    /**
     * Remove a sessions from the database (Logout),
     * by using the username.
     *
     * @param username the username of the user to remove (logout).
     */
    public void removeSession(String username) {
        try {
            String sql = "DELETE FROM sessions WHERE username = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(prefix + e);
        }
    }
}
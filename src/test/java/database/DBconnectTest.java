package database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBconnectTest {

    private DBconnect dbconnect;
    @Mock
    private Connection connection;
    @Mock
    private Statement statement;
    @Mock
    private ResultSet resultSet;

    @BeforeEach
    void setUp() throws SQLException {
        dbconnect = new DBconnect();
        connection = Mockito.mock(Connection.class);
        statement = Mockito.mock(Statement.class);
        resultSet = Mockito.mock(ResultSet.class);
        dbconnect.setConnection(connection);
        dbconnect.setStatement(statement);
        dbconnect.setResultSet(resultSet);
    }

    @Test
    void connectionTest() {
        Assertions.assertNotNull(dbconnect);
    }

    @Test
    void getDataTest() throws SQLException {
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);

        String username = "username";
        String password = "password";

        Mockito.when(resultSet.getString("username")).thenReturn(username);
        Mockito.when(resultSet.getString("password")).thenReturn(password);
        Mockito.when(statement.executeQuery("SELECT * FROM users")).thenReturn(resultSet);

        ResultSet re = dbconnect.getData();
        Assertions.assertEquals(re, resultSet);
    }

    @Test
    void loginDataTestTrue() throws SQLException {
        String username = "username";
        String password = "password";

        String checkUser = "SELECT * FROM users WHERE username='" + username
                + "' && password='" + password + "'";

        Mockito.when(resultSet.getString("username")).thenReturn(username);
        Mockito.when(resultSet.getString("password")).thenReturn(password);
        Mockito.when(statement.executeQuery(checkUser)).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true);

        Assertions.assertTrue(dbconnect.loginData(username,password));
    }

    @Test
    void loginDataTestFalse() throws SQLException {
        String username = "newuser";
        String password = "newpassword";

        String checkUser = "SELECT * FROM users WHERE username='" + "username"
                + "' && password='" + "password" + "'";

        Mockito.when(resultSet.getString("username")).thenReturn(username);
        Mockito.when(resultSet.getString("password")).thenReturn(password);
        Mockito.when(statement.executeQuery(checkUser)).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(false);

        Assertions.assertFalse(dbconnect.loginData("username","password"));
    }

    @Test
    void registerUserTest() throws SQLException {
        String username = "username";
        String password = "password";

        String usernameCheck = "SELECT * FROM users WHERE username='" + username + "'";
        String checkUser = "SELECT * FROM users WHERE username='" + username
                + "' && password='" + password + "'";
        String insertUser = "INSERT INTO users (username,password) VALUES ('" + username
                + "','" + password + "')";

        Mockito.when(statement.executeUpdate(insertUser)).thenReturn(0);
        Mockito.when(resultSet.next()).thenReturn(false).thenReturn(true);
        Mockito.when(statement.executeQuery(usernameCheck)).thenReturn(resultSet);
        Mockito.when(statement.executeQuery(checkUser)).thenReturn(resultSet);

        Assertions.assertTrue(dbconnect.registerUser(username,password));
        Assertions.assertFalse(dbconnect.registerUser(username,password));
    }

}

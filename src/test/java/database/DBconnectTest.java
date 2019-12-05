package database;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class DBconnectTest {

    private transient DBconnect dbconnect;
    @Mock
    private transient Connection connection;
    @Mock
    private transient Statement statement;
    @Mock
    private transient ResultSet resultSet;

    private transient String defaultUser;
    private transient String defaultPassword;
    private transient String checkUser;

    private transient ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private transient PrintStream originalOut = System.out;
    private transient String error = "Error";

    @BeforeEach
    void setUp() throws SQLException {
        System.setOut(new PrintStream(outContent));
        dbconnect = new DBconnect();
        connection = Mockito.mock(Connection.class);
        statement = Mockito.mock(Statement.class);
        resultSet = Mockito.mock(ResultSet.class);
        dbconnect.setConnection(connection);
        dbconnect.setStatement(statement);
        dbconnect.setResultSet(resultSet);
        defaultUser = "username";
        defaultPassword = "password";
        checkUser = "SELECT * FROM users WHERE username='" + defaultUser
                + "' && password='" + defaultPassword + "'";
    }

    @AfterEach
    void cleanUp() {
        System.setOut(originalOut);
    }

    @Test
    void connectionErrorError() throws SQLException {
        Mockito.when(connection.createStatement()).thenThrow(SQLException.class);
        dbconnect.loginData(defaultUser, defaultPassword);
        boolean contains = outContent.toString().contains(error);
        Assertions.assertTrue(contains);
    }

    @Test
    void connectionTest() {
        Assertions.assertNotNull(dbconnect);
    }

    @Test
    void getDataError() throws SQLException {
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenThrow(SQLException.class);
        dbconnect.getData();
        boolean contains = outContent.toString().contains(error);
        Assertions.assertTrue(contains);
    }

    @Test
    void getDataTest() throws Exception {
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);

        Mockito.when(resultSet.getString(defaultUser)).thenReturn(defaultUser);
        Mockito.when(resultSet.getString(defaultPassword)).thenReturn(defaultPassword);
        Mockito.when(statement.executeQuery("SELECT * FROM users")).thenReturn(resultSet);

        Assertions.assertEquals(dbconnect.getData(), resultSet);
    }

    @Test
    void loginDataError() throws SQLException {
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenThrow(SQLException.class);
        dbconnect.loginData(defaultUser, defaultPassword);
        boolean contains = outContent.toString().contains(error);
        Assertions.assertTrue(contains);
    }

    @Test
    void loginDataTestTrue() throws SQLException {

        Mockito.when(resultSet.getString(defaultUser)).thenReturn(defaultUser);
        Mockito.when(resultSet.getString(defaultPassword)).thenReturn(defaultPassword);
        Mockito.when(statement.executeQuery(checkUser)).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true);

        Assertions.assertTrue(dbconnect.loginData(defaultUser,defaultPassword));
    }

    @Test
    void loginDataTestFalse() throws SQLException {
        String username = "newuser";
        String password = "newpassword";

        Mockito.when(resultSet.getString(defaultUser)).thenReturn(username);
        Mockito.when(resultSet.getString(defaultPassword)).thenReturn(password);
        Mockito.when(statement.executeQuery(checkUser)).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(false);

        Assertions.assertFalse(dbconnect.loginData(defaultUser,defaultPassword));
    }

    @Test
    void registerUserError() throws SQLException {
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenThrow(SQLException.class);
        dbconnect.registerUser(defaultUser, defaultPassword);
        boolean contains = outContent.toString().contains(error);
        Assertions.assertTrue(contains);
    }

    @Test
    void registerUserTest() throws SQLException {

        String usernameCheck = "SELECT * FROM users WHERE username='" + defaultUser + "'";
        String insertUser = "INSERT INTO users (username,password) VALUES ('" + defaultUser
                + "','" + defaultPassword + "')";

        Mockito.when(statement.executeUpdate(insertUser)).thenReturn(0);
        Mockito.when(resultSet.next()).thenReturn(false).thenReturn(true);
        Mockito.when(statement.executeQuery(usernameCheck)).thenReturn(resultSet);
        Mockito.when(statement.executeQuery(checkUser)).thenReturn(resultSet);

        Assertions.assertTrue(dbconnect.registerUser(defaultUser,defaultPassword));
        Assertions.assertFalse(dbconnect.registerUser(defaultUser,defaultPassword));
    }

}

package database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;



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
        MockitoAnnotations.initMocks(this);
        System.setOut(new PrintStream(outContent));
        dbconnect = new DBconnect();
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
    void connectionTest() {
        assertNotNull(dbconnect);
    }

    @Test
    void getDataError() throws SQLException {
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenThrow(SQLException.class);
        dbconnect.getData();
        boolean contains = outContent.toString().contains(error);
        assertTrue(contains);
    }

    @Test
    void getDataTest() throws Exception {
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);

        Mockito.when(resultSet.getString(defaultUser)).thenReturn(defaultUser);
        Mockito.when(resultSet.getString(defaultPassword)).thenReturn(defaultPassword);
        Mockito.when(statement.executeQuery("SELECT * FROM users")).thenReturn(resultSet);

        assertEquals(dbconnect.getData(), resultSet);
    }

    @Test
    void loginDataError() throws SQLException {
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenThrow(SQLException.class);
        dbconnect.loginData(defaultUser, defaultPassword);
        boolean contains = outContent.toString().contains(error);
        assertTrue(contains);
    }

    @Test
    void loginDataTestTrue() throws SQLException {

        Mockito.when(resultSet.getString(defaultUser)).thenReturn(defaultUser);
        Mockito.when(resultSet.getString(defaultPassword)).thenReturn(defaultPassword);
        Mockito.when(statement.executeQuery(checkUser)).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true);

        assertTrue(dbconnect.loginData(defaultUser,defaultPassword));
    }

    @Test
    void loginDataTestFalse() throws SQLException {
        String username = "newuser";
        String password = "newpassword";

        Mockito.when(resultSet.getString(defaultUser)).thenReturn(username);
        Mockito.when(resultSet.getString(defaultPassword)).thenReturn(password);
        Mockito.when(statement.executeQuery(checkUser)).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(false);

        assertFalse(dbconnect.loginData(defaultUser,defaultPassword));
    }

    @Test
    void registerUserError() throws SQLException {
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenThrow(SQLException.class);
        dbconnect.registerUser(defaultUser, defaultPassword);
        boolean contains = outContent.toString().contains(error);
        assertTrue(contains);
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

        assertTrue(dbconnect.registerUser(defaultUser,defaultPassword));
        assertFalse(dbconnect.registerUser(defaultUser,defaultPassword));
    }

    @Test
    void registerUserTestBothFalse() throws SQLException {

        String usernameCheck = "SELECT * FROM users WHERE username='" + defaultUser + "'";
        String insertUser = "INSERT INTO users (username,password) VALUES ('" + defaultUser
                + "','" + defaultPassword + "')";

        Mockito.when(statement.executeUpdate(insertUser)).thenReturn(0);
        Mockito.when(resultSet.next()).thenReturn(false).thenReturn(false);
        Mockito.when(statement.executeQuery(usernameCheck)).thenReturn(resultSet);
        Mockito.when(statement.executeQuery(checkUser)).thenReturn(resultSet);

        assertFalse(dbconnect.registerUser(defaultUser,defaultPassword));
        assertFalse(dbconnect.registerUser(defaultUser,defaultPassword));
    }

}

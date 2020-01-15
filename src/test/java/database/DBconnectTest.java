package database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import gui.controller.PasswordHash;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    @Mock
    private transient PreparedStatement preparedStatement;
    @Mock
    private transient PasswordHash pwdHash;

    private transient String defaultUser;
    private transient String defaultPassword;

    private transient ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private transient PrintStream originalOut = System.out;
    private transient String error = "Error";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        System.setOut(new PrintStream(outContent));
        dbconnect = DBconnect.getInstance();
        dbconnect.setConnection(connection);
        dbconnect.setStatement(statement);
        dbconnect.setResultSet(resultSet);
        dbconnect.setPreparedStatement(preparedStatement);
        defaultUser = "username";
        defaultPassword = "password";
    }

    @AfterEach
    void cleanUp() throws SQLException {
        System.setOut(originalOut);
        dbconnect.getConnection().close();
    }

    @Test
    void connectionTest() {
        assertNotNull(dbconnect);
    }

    @Test
    void getInstanceReturnsReferenceToSameInstance() {
        DBconnect dbconnect2 = DBconnect.getInstance();
        assertEquals(dbconnect2, dbconnect);
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
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);

        assertEquals(dbconnect.getData(), resultSet);
    }

    @Test
    void loginDataError() throws SQLException {
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenThrow(SQLException.class);
        dbconnect.authenticate(defaultUser, defaultPassword, pwdHash);
        boolean contains = outContent.toString().contains(error);
        assertTrue(contains);
    }

    @Test
    void authenticateTestTrue() throws InvalidKeySpecException,
            NoSuchAlgorithmException, SQLException {

        Mockito.when(connection.prepareStatement(Mockito.anyString()))
                .thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(resultSet.first()).thenReturn(true);
        Mockito.when(resultSet.getString(Mockito.anyString())).thenReturn(defaultPassword);
        Mockito.when(pwdHash.validatePassword(Mockito.anyString())).thenReturn(true);
        assertTrue(dbconnect.authenticate(defaultUser,defaultPassword, pwdHash));
    }

    @Test
    void authenticateTestFalse() throws InvalidKeySpecException,
            NoSuchAlgorithmException, SQLException {

        Mockito.when(connection.prepareStatement(Mockito.anyString()))
                .thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(resultSet.first()).thenReturn(true);
        Mockito.when(resultSet.getString(Mockito.anyString())).thenReturn(defaultPassword);
        Mockito.when(pwdHash.validatePassword(Mockito.anyString())).thenReturn(false);

        assertFalse(dbconnect.authenticate(defaultUser,defaultPassword, pwdHash));
        assertFalse(dbconnect.authenticate(defaultUser,defaultPassword, null));
    }

    @Test
    void registerUserError() throws SQLException {
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenThrow(SQLException.class);
        dbconnect.registerUser(defaultUser, defaultPassword, pwdHash);
        boolean contains = outContent.toString().contains(error);
        assertTrue(contains);
    }

    @Test
    void registerUserInvalidPasswordTest() throws InvalidKeySpecException,
            NoSuchAlgorithmException {

        Mockito.when(pwdHash.validatePassword(Mockito.anyString())).thenReturn(false);

        assertFalse(dbconnect.registerUser(defaultUser,defaultPassword, pwdHash));
        assertFalse(dbconnect.registerUser(defaultUser,defaultPassword, null));
    }

    @Test
    void registerUserAlreadyExistsTest() throws SQLException {

        Mockito.when(connection.prepareStatement(Mockito.anyString()))
                .thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true);
        assertFalse(dbconnect.registerUser(defaultUser,defaultPassword, pwdHash));
    }

    @Test
    void registerUserSuccessfulTest() throws SQLException,
            InvalidKeySpecException, NoSuchAlgorithmException {

        Mockito.when(connection.prepareStatement(Mockito.anyString()))
                .thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(false);
        Mockito.when(pwdHash.createHash()).thenReturn(defaultPassword);
        Mockito.when(connection.prepareStatement(Mockito.anyString()))
                .thenReturn(preparedStatement);
        Mockito.when(pwdHash.validatePassword(Mockito.anyString())).thenReturn(true);

        assertTrue(dbconnect.registerUser(defaultUser,defaultPassword, pwdHash));
    }

    @Test
    void registerUserBadHashTest() throws SQLException,
            InvalidKeySpecException, NoSuchAlgorithmException {

        Mockito.when(connection.prepareStatement(Mockito.anyString()))
                .thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(false);
        Mockito.when(pwdHash.createHash()).thenReturn(defaultPassword);
        Mockito.when(connection.prepareStatement(Mockito.anyString()))
                .thenReturn(preparedStatement);
        Mockito.when(pwdHash.validatePassword(Mockito.anyString())).thenReturn(false);

        assertFalse(dbconnect.registerUser(defaultUser,defaultPassword, pwdHash));
    }
}

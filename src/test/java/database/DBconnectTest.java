package database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mysql.cj.jdbc.MysqlDataSource;
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
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class DBconnectTest {

    private DBconnect dbconnect;
    @Mock
    private Connection connection;
    @Mock
    private Statement statement;
    @Mock
    private ResultSet resultSet;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private PasswordHash pwdHash;
    @Mock
    private MysqlDataSource ds;

    private String defaultUser;
    private String defaultPassword;
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private PrintStream originalOut = System.out;
    private String error = "Error";
    private String name = "name";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        System.setOut(new PrintStream(outContent));
        dbconnect = DBconnect.getInstance();
        dbconnect.setDs(ds);
        dbconnect.setConnection(connection);
        dbconnect.setStatement(statement);
        dbconnect.setResultSet(resultSet);
        dbconnect.setPreparedStatement(preparedStatement);
        defaultUser = "username";
        defaultPassword = "password";
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
    void getInstanceReturnsReferenceToSameInstance() {
        DBconnect dbconnect2 = DBconnect.getInstance();
        assertEquals(dbconnect2, dbconnect);
    }

    @Test
    void getDataError() throws SQLException {
        when(statement.executeQuery(Mockito.anyString())).thenThrow(SQLException.class);
        dbconnect.getData();
        boolean contains = outContent.toString().contains(error);
        assertTrue(contains);
    }

    @Test
    void getDataTest() throws Exception {
        when(resultSet.next()).thenReturn(true).thenReturn(false);

        when(resultSet.getString(defaultUser)).thenReturn(defaultUser);
        when(resultSet.getString(defaultPassword)).thenReturn(defaultPassword);
        when(preparedStatement.executeQuery(Mockito.anyString())).thenReturn(resultSet);

        assertEquals(dbconnect.getData(), resultSet);
    }

    @Test
    void loginDataError() throws SQLException {
        when(statement.executeQuery(Mockito.anyString())).thenThrow(SQLException.class);
        dbconnect.authenticate(defaultUser, defaultPassword, pwdHash);
        boolean contains = outContent.toString().contains(error);
        assertTrue(contains);
    }

    @Test
    void authenticateTestTrue() throws InvalidKeySpecException,
        NoSuchAlgorithmException, SQLException {

        when(connection.prepareStatement(Mockito.anyString()))
            .thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.first()).thenReturn(true);
        when(resultSet.getString(Mockito.anyString())).thenReturn(defaultPassword);
        when(pwdHash.validatePassword(Mockito.anyString())).thenReturn(true);
        assertTrue(dbconnect.authenticate(defaultUser, defaultPassword, pwdHash));
    }

    @Test
    void authenticateTestFalse() throws InvalidKeySpecException,
        NoSuchAlgorithmException, SQLException {

        when(connection.prepareStatement(Mockito.anyString()))
            .thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.first()).thenReturn(true);
        when(resultSet.getString(Mockito.anyString())).thenReturn(defaultPassword);
        when(pwdHash.validatePassword(Mockito.anyString())).thenReturn(false);

        assertFalse(dbconnect.authenticate(defaultUser, defaultPassword, pwdHash));
        assertFalse(dbconnect.authenticate(defaultUser, defaultPassword, null));
    }

    @Test
    void registerUserError() throws SQLException {
        when(statement.executeQuery(Mockito.anyString())).thenThrow(SQLException.class);
        dbconnect.registerUser(defaultUser, defaultPassword, pwdHash);
        boolean contains = outContent.toString().contains(error);
        assertTrue(contains);
    }

    @Test
    void registerUserInvalidPasswordTest() throws InvalidKeySpecException,
        NoSuchAlgorithmException {

        when(pwdHash.validatePassword(Mockito.anyString())).thenReturn(false);

        assertFalse(dbconnect.registerUser(defaultUser, defaultPassword, pwdHash));
        assertFalse(dbconnect.registerUser(defaultUser, defaultPassword, null));
    }

    @Test
    void registerUserAlreadyExistsTest() throws SQLException {

        when(connection.prepareStatement(Mockito.anyString()))
            .thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        assertFalse(dbconnect.registerUser(defaultUser, defaultPassword, pwdHash));
    }

    @Test
    void registerUserSuccessfulTest() throws SQLException,
        InvalidKeySpecException, NoSuchAlgorithmException {

        when(connection.prepareStatement(Mockito.anyString()))
            .thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);
        when(pwdHash.createHash()).thenReturn(defaultPassword);
        when(connection.prepareStatement(Mockito.anyString()))
            .thenReturn(preparedStatement);
        when(pwdHash.validatePassword(Mockito.anyString())).thenReturn(true);

        assertTrue(dbconnect.registerUser(defaultUser, defaultPassword, pwdHash));
    }

    @Test
    void registerUserBadHashTest() throws SQLException,
        InvalidKeySpecException, NoSuchAlgorithmException {

        when(connection.prepareStatement(Mockito.anyString()))
            .thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);
        when(pwdHash.createHash()).thenReturn(defaultPassword);
        when(connection.prepareStatement(Mockito.anyString()))
            .thenReturn(preparedStatement);
        when(pwdHash.validatePassword(Mockito.anyString())).thenReturn(false);

        assertFalse(dbconnect.registerUser(defaultUser, defaultPassword, pwdHash));
    }

    @Test
    void storeCookieTestSuccess() throws SQLException {
        when(connection.prepareStatement(Mockito.anyString()))
            .thenReturn(preparedStatement);
        assertTrue(dbconnect.storeCookie("test", "test"));
    }

    @Test
    void storeCookieTestFail() {
        assertFalse(dbconnect.storeCookie("fail", "error"));
    }

    @Test
    void getCookieTestSuccess() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("username")).thenReturn(name);
        assertEquals(name, dbconnect.getUsername("chocolate-chip"));
    }

    @Test
    void getCookieTestFail() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);
        assertNull(dbconnect.getUsername("chocolate-chip"));
    }

    @Test
    void getCookieTestError() {
        dbconnect.getUsername("dry-cookie");
        boolean contains = outContent.toString().contains(error);
        assertTrue(contains);
    }

    @Test
    void openConnectionTest() throws SQLException {
        dbconnect.openConnection();
        verify(ds).getConnection();
    }

    @Test
    void openConnectionTestFail() throws SQLException {
        doThrow(SQLException.class).when(ds).getConnection();
        dbconnect.openConnection();
        boolean contains = outContent.toString().contains(error);
        assertTrue(contains);
    }

    @Test
    void closeConnectionTest() throws SQLException {
        dbconnect.closeConnection();
        verify(connection).close();
    }

    @Test
    void closeConnectionTestFail() throws SQLException {
        doThrow(SQLException.class).when(connection).close();
        dbconnect.closeConnection();
        boolean contains = outContent.toString().contains(error);
        assertTrue(contains);
    }

    @Test
    void removeSessionTest() throws SQLException {
        when(connection.prepareStatement(Mockito.anyString()))
            .thenReturn(preparedStatement);
        dbconnect.removeSession(name);
        verify(preparedStatement).executeUpdate();
    }

    @Test
    void removeSessionFail() throws SQLException {
        doThrow(SQLException.class).when(connection).prepareStatement(anyString());
        dbconnect.removeSession(name);
        boolean contains = outContent.toString().contains(error);
        assertTrue(contains);
    }

    @Test
    void saveScoreWrongNameTest() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);
        dbconnect.saveScore(name, 10, "nick");
        verify(preparedStatement, times(0)).executeUpdate();
    }

    @Test
    void saveScoreTest() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        dbconnect.saveScore(name, 10, "nick");
        verify(preparedStatement).executeUpdate();
    }

    @Test
    void saveScoreFail() throws SQLException {
        doReturn(preparedStatement).doThrow(SQLException.class)
            .when(connection).prepareStatement(anyString());
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        dbconnect.saveScore(name, 10, "nick");
        verify(preparedStatement, times(0)).executeUpdate();
    }

    @Test
    void getGlobalScoresTest() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString("username")).thenReturn(name);
        when(resultSet.getInt("score")).thenReturn(10);
        ArrayList<Details> list = new ArrayList<>();
        dbconnect.getGlobalScores(list);
        assertEquals(1, list.size());
    }

    @Test
    void getGlobalScoresFail() throws SQLException {
        doThrow(SQLException.class).when(connection).prepareStatement(anyString());
        ArrayList<Details> list = new ArrayList<>();
        dbconnect.getGlobalScores(list);
        boolean contains = outContent.toString().contains(error);
        assertTrue(contains);
    }

    @Test
    void getPersonalScoresTest() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString("nickname")).thenReturn(name);
        when(resultSet.getInt("score")).thenReturn(10);
        ArrayList<Details> list = new ArrayList<>();
        dbconnect.getPersonalScores(list, name);
        assertEquals(1, list.size());
    }

    @Test
    void getPersonalScoresFail() throws SQLException {
        doThrow(SQLException.class).when(connection).prepareStatement(anyString());
        ArrayList<Details> list = new ArrayList<>();
        dbconnect.getPersonalScores(list, name);
        boolean contains = outContent.toString().contains(error);
        assertTrue(contains);
    }

}

package database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SessionManagerTest {
    private SessionManager manager;
    private DBconnect dbconnect;
    private PrintWriter writer;
    private String name = "name";

    @BeforeEach
    void setUp() {
        manager = SessionManager.getInstance();
        dbconnect = mock(DBconnect.class);
        manager.setDbconnect(dbconnect);
        writer = mock(PrintWriter.class);
    }

    @Test
    void getInstanceTestNew() {
        assertNotNull(manager);
    }

    @Test
    void getInstanceTestExists() {
        SessionManager manager2 = SessionManager.getInstance();
        assertEquals(manager, manager2);
    }

    @Test
    void saveCookieTestNull() {
        SessionManager manager = mock(SessionManager.class,
            withSettings().defaultAnswer(CALLS_REAL_METHODS));
        doReturn(null).when(manager).createCookie(any());
        assertFalse(manager.saveCookie(writer, name));
    }

    @Test
    void saveCookieTestSuccess() {
        when(dbconnect.storeCookie(anyString(), anyString())).thenReturn(true);
        assertTrue(manager.saveCookie(writer, name));
    }

    @Test
    void saveCookieTestFail() {
        when(dbconnect.storeCookie(anyString(), anyString())).thenReturn(false);
        assertFalse(manager.saveCookie(writer, name));
    }

    @Test
    void retrieveCookieTest() throws IOException {
        //This is a mock so no actual reading is going on.
        BufferedReader reader = mock(BufferedReader.class); //NOPMD
        when(dbconnect.getUsername(anyString())).thenReturn(name);
        when(reader.readLine()).thenReturn("cookie-dish");
        assertEquals(name, manager.retrieveUserData(reader));
        reader.close();
    }

    @Test
    void retrieveCookieTestError() throws IOException {
        //This is a mock so no actual reading is going on.
        BufferedReader reader = mock(BufferedReader.class); //NOPMD
        doThrow(FileNotFoundException.class).when(reader).readLine();
        assertNull(manager.retrieveUserData(reader));
    }

    @Test
    void logOutTest() {
        //This is a mock which doesn't need to be closed.
        PrintWriter writer = mock(PrintWriter.class); //NOPMD
        manager.logOut(name, writer);
        verify(dbconnect).removeSession(anyString());
        verify(writer).write("");
    }
}
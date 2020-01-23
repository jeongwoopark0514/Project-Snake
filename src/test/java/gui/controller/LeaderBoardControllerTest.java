package gui.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.withSettings;

import database.DBconnect;
import database.SessionManager;
import gui.Gui;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LeaderBoardControllerTest {
    private LeaderBoardController controller;
    private DBconnect database;
    private Gui gui;

    @BeforeEach
    void setUp() {
        database = mock(DBconnect.class);
        gui = mock(Gui.class);
        SessionManager manager = mock(SessionManager.class);
        controller = mock(LeaderBoardController.class,
            withSettings().defaultAnswer(CALLS_REAL_METHODS));
        controller.setDatabase(database);
        controller.setManager(manager);
        controller.gui = gui;
    }

    @Test
    void openDatabaseTest() {
        controller.openDB();
        verify(database).openConnection();
    }

    @Test
    void goBackMainTest() throws IOException {
        controller.goBackMain();
        verify(gui).switchScene(anyString());
    }

    @Test
    void quitButtonTest() {
        controller.quitButton();
        verify(gui).quit();
    }
}
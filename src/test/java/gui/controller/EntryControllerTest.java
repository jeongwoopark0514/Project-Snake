package gui.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import database.DBconnect;
import database.SessionManager;
import gui.Gui;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EntryControllerTest {
    private EntryController entryController;
    private Gui gui;
    private SessionManager manager;
    private DBconnect database;

    @BeforeEach
    void setUp() {
        gui = mock(Gui.class);
        manager = mock(SessionManager.class);
        database = mock(DBconnect.class);
        entryController = new EntryController();
        entryController.gui = gui;
        entryController.setManager(manager);
        entryController.setDatabase(database);
    }

    @Test
    void changeToLeaderBoardTest() {
        try {
            doNothing().when(gui).switchScene(anyString());
            entryController.changeToLeaderBoard();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void startSnakeTest() throws LineUnavailableException {
        doNothing().when(gui).startSnakeGame();
        entryController.startGame();
    }

    @Test
    void changeToLoginTest() {
        try {
            when(manager.getUsername()).thenReturn("any");
            doNothing().when(gui).switchScene(anyString());
            entryController.changeToLogin();
            verify(manager).logOut(anyString(), any());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void changeToSettingTest() {
        try {
            doNothing().when(gui).switchScene("setting");
            entryController.changeToSettings();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void quitButtonTest() {
        entryController.quitButton();
        verify(gui).quit();
    }
}
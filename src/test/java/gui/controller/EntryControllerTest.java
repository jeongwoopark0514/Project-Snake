package gui.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import gui.Gui;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import org.junit.jupiter.api.Test;

class EntryControllerTest {

    @Test
    void changeToLeaderBoardTest() {
        try {
            Gui gui = mock(Gui.class);
            EntryController entryController = new EntryController();
            entryController.gui = gui;
            doNothing().when(gui).switchScene("whatever");
            entryController.changeToLeaderBoard();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void startSnakeTest() throws LineUnavailableException {
        Gui gui = mock(Gui.class);
        EntryController entryController = new EntryController();
        entryController.gui = gui;
        doNothing().when(gui).startSnakeGame();
        entryController.startGame();
    }

    @Test
    void changeToLoginTest() {
        try {
            Gui gui = mock(Gui.class);
            EntryController entryController = new EntryController();
            entryController.gui = gui;
            doNothing().when(gui).switchScene("whatever");
            entryController.changeToLogin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void changeToSettingTest() {
        try {
            Gui gui = mock(Gui.class);
            EntryController entryController = new EntryController();
            entryController.gui = gui;
            doNothing().when(gui).switchScene("setting");
            entryController.changeToSettings();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
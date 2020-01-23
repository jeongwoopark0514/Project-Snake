package gui.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import database.DBconnect;
import gui.Gui;
import gui.GuiButton;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ScoreControllerTest {

    private Gui gui;
    private GuiButton guiButton;
    private ScoreController controller;
    private DBconnect dbconnect;

    @BeforeEach
    void setUp() {
        gui = mock(Gui.class);
        guiButton = mock(GuiButton.class);
        dbconnect = mock(DBconnect.class);
        controller = new ScoreController();
        controller.gui = gui;
        controller.guiButton = guiButton;
        controller.setDatabase(dbconnect);
    }

    @Test
    void scoreSaveTestEmpty() {
        doNothing().when(dbconnect).openConnection();
        doNothing().when(dbconnect).closeConnection();
        when(gui.getText(any())).thenReturn("");
        controller.scoreSave();
        String empty = "Empty field(s)";
        verify(gui).showAlert(any(), eq(empty));
    }

    @Test
    void scoreSaveTestNonEmpty() {
        doNothing().when(dbconnect).openConnection();
        doNothing().when(dbconnect).closeConnection();
        when(gui.getText(any())).thenReturn("yes");
        controller.scoreSave();
        verify(gui).showAlert("Your score was saved", "Success!");
    }

    @Test
    void goBackMainTest() {
        try {
            doNothing().when(gui).switchScene(anyString());
            controller.goBackMain();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void quitButtonTest() {
        controller.quitButton();
        verify(gui).quit();
    }
}
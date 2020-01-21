package gui.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import database.DBconnect;
import gui.Gui;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class ScoreControllerTest {

    private String empty = "Empty field(s)";

    @Test
    void scoreSaveTestEmpty() {
        ScoreController scoreController = new ScoreController();
        DBconnect dbconnect = mock(DBconnect.class);
        scoreController.setDatabase(dbconnect);
        doNothing().when(dbconnect).openConnection();
        doNothing().when(dbconnect).closeConnection();
        Gui gui = mock(Gui.class);
        scoreController.gui = gui;
        when(gui.getText(any())).thenReturn("");
        scoreController.scoreSave();
        verify(gui).showAlert(any(), eq(empty));
    }

    @Test
    void scoreSaveTestNonEmpty() {
        ScoreController scoreController = new ScoreController();
        DBconnect dbconnect = mock(DBconnect.class);
        scoreController.setDatabase(dbconnect);
        Gui gui = mock(Gui.class);
        scoreController.gui = gui;
        doNothing().when(dbconnect).openConnection();
        doNothing().when(dbconnect).closeConnection();
        when(gui.getText(any())).thenReturn("yes");
        scoreController.scoreSave();
        verify(gui).showAlert("Your score was saved", "Success!");
    }

    @Test
    void goBackMainTest() {
        try {
            Gui gui = mock(Gui.class);
            ScoreController scoreController = new ScoreController();
            scoreController.gui = gui;
            doNothing().when(gui).switchScene("whatever");
            scoreController.goBackMain();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
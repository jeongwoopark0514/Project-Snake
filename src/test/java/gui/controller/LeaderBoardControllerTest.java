package gui.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import gui.Gui;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class LeaderBoardControllerTest {

    
    
    
    @Test
    void goBackMainTest() {
        try {
            Gui gui = mock(Gui.class);
            LeaderBoardController leaderBoardController = new LeaderBoardController();
            leaderBoardController.gui = gui;
            doNothing().when(gui).switchScene("whatever");
            leaderBoardController.goBackMain();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
package gui.controller;

import gui.Gui;

import java.io.IOException;

/**
 * This is the controller of LeaderBoard.
 * This controller class is in progress.
 */
public class LeaderBoardController {

    public transient Gui gui = new Gui();

    public void goBackMain() throws IOException {
        gui.switchScene("src/main/resources/fxml/entry.fxml");
    }

}

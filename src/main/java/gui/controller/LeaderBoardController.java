package gui.controller;

import gui.Gui;

import java.io.IOException;

/**
 * This is the controller of LeaderBoard.
 * This class will contain all the methods that control leader board, and will be kept separate
 * from GUI logic.
 * This controller class is in progress. Will be modified a lot in the next sprint.
 */
public class LeaderBoardController {

    public transient Gui gui = new Gui();

    /**
     * This methods go back to the entry page when you click goBack button.
     * @throws IOException Exception if the file does not exist.
     */
    public void goBackMain() throws IOException {
        gui.switchScene("src/main/resources/fxml/entry.fxml");
    }

}

package gui.controller;

import gui.Gui;

import java.io.IOException;

/**
 * This class does not need to be tested.
 * This is all GUI class.
 */
public class EntryController {

    public transient Gui gui = new Gui();

    /**
     * When you click start button, move to game screen.
     */
    public void startGame() {
        gui.startSnakeGame();
    }

    /**
     * When you click, leaderboard button, move the page to leaderboard.
     */
    public void changeToLeaderBoard() throws IOException {
        gui.switchScene("src/main/resources/fxml/leaderboard.fxml");
    }
}

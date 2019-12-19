package gui.controller;

import gui.Gui;

import java.io.IOException;

/**
 * This is 'EntryController' class. It controls the entry page after login page.
 * This class calls methods from Gui class which contains only GUI logic.
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

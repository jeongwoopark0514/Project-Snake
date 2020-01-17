package gui.controller;

import gui.Gui;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;


/**
 * This is 'EntryController' class. It controls the entry page after login page.
 * This class calls methods from Gui class which contains only GUI logic.
 */
public class EntryController {

    public Gui gui = new Gui();

    /**
     * When you click start button, move to game screen.
     */
    public void startGame() throws LineUnavailableException {
        gui.startSnakeGame();
    }

    /**
     * When you click, leaderboard button, move the page to leaderboard.
     */
    public void changeToLeaderBoard() throws IOException {
        gui.switchScene("src/main/resources/fxml/leaderboard.fxml");
    }

    /**
     * No testing required because impossible to test system.exit.
     */
    public void quitButton() {
        gui.quit();
    }

    /**
     *  Sign out the game and go back to login page.
     */
    public void changeToLogin() throws IOException {
        gui.switchScene("src/main/resources/fxml/login.fxml");
    }

    /**
     * Change the scene to settings.
     */
    public void changeToSettings() throws IOException {
        gui.switchScene("src/main/resources/fxml/setting.fxml");
    }
}

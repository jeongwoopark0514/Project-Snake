package gui.controller;

import database.DBconnect;
import gui.Gui;

import java.io.IOException;

/**
 * This class is for controller of score screen.
 * This class will be used later in the future. (in progress).
 */
public class ScoreController {

    public transient Gui gui = new Gui();
    private DBconnect database = new DBconnect();

    public String username = "Rohan";
    public String nickname = "nickname";

    /**
     * Method that saves the nickname and score of a users game.
     * @param score - the score of the current game
     * @throws IOException - exception
     */
    public void scoreSave(int score) throws IOException {
        if (nickname.equals("")) {
            System.out.println("SCORE NOT SAVED");
            gui.showAlert("Enter a nickname", "Empty field(s)");
        } else {
            database.saveScore(username, score, nickname);
            System.out.println("Score Saved");
            gui.showAlert("Your score was saved", "Success!");
            gui.switchScene("src/main/resources/fxml/entry.fxml");
        }
    }

    /**
     * No testing required because impossible to test system.exit.
     */
    public void quitButton() {
        gui.quit();
    }
}

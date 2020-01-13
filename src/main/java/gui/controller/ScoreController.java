package gui.controller;

import database.DBconnect;
import gui.Gui;

import java.io.IOException;

/**
 * This class is for controller of score screen.
 * This class will be used later in the future. (in progress).
 */
public class ScoreController {

    private transient Gui gui = new Gui();
    private LoginController loginController = new LoginController();
    private DBconnect database = new DBconnect();

    public String username = "Rohan";
    public String nickname = "nickname";

    /**
     * Method that saves the nickname and score of a users game.
     * @param score - the score of the current game
     * @throws IOException
     */
    public void scoreSave(int score) throws IOException {
        if (nickname.equals("")) {
            System.out.println("SCORE NOT SAVED");
            gui.showAlert("Enter a nickname", "Empty field(s)");
        }else if(database.usernameCheck(username) != true){
            System.out.println("SCORE NOT SAVED");
            gui.showAlert("Please enter the correct username", "Error!");
        }else {
            database.saveScore(username, score, nickname);
            System.out.println("Score Saved");
            gui.showAlert("Your score was saved", "Success!");
            gui.switchScene("src/main/resources/fxml/entry.fxml");
        }
    }
}

package gui.controller;

import database.DBconnect;
import database.SessionManager;
import game.Game;
import gui.Gui;
import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

/**
 * This class is for controller of score screen.
 * This class will be used later in the future. (in progress).
 */
public class ScoreController {

    public transient Gui gui = new Gui();

    @Getter
    @Setter
    private DBconnect database = DBconnect.getInstance();
    @Getter
    @Setter
    private SessionManager manager = SessionManager.getInstance();
    public Game game;

    public transient TextField nickname ;


    /**
     * Method that stores the nickname and the score after each game.
     * @throws IOException - Exception.
     */
    public void scoreSave() {
        database.openConnection();
//        if (gui.getText(nickname).equals("")) {
//            System.out.println("SCORE NOT SAVED");
//            gui.showAlert("Enter a nickname", "Empty field(s)");
//        } else {
            database.saveScore(manager.getUsername(),game.getScore(), "nickname");
            System.out.println("Score Saved");
            gui.showAlert("Your score was saved", "Success!");
//        }
        database.closeConnection();
    }

    /**
     * This methods go back to the entry page when you click goBack button.
     */
    public void goToScore() throws IOException {
        gui.switchScene("src/main/resources/fxml/score.fxml");
    }

    /**
     * No testing required because impossible to test system.exit.
     */
    public void quitButton() {
        gui.quit();
    }
}

package gui.controller;

import database.DBconnect;
import database.SessionManager;
import game.Game;
import gui.Gui;

import java.io.IOException;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.Setter;


/**
 * This class is for controller of score screen.
 * This class will be used later in the future. (in progress).
 */
public class ScoreController {

    public transient Gui gui = new Gui();
    public int score;

    @Getter
    @Setter
    private DBconnect database = DBconnect.getInstance();
    @Getter
    @Setter
    private SessionManager manager = SessionManager.getInstance();
    public Game game;

    public transient TextField nickname;

    public transient Text scoreText = new Text();


    /**
     * Method that stores the nickname and the score after each game.
     * @throws IOException - Exception.
     */
    public void scoreSave() {
        database.openConnection();
        if (gui.getText(nickname).equals("")) {
            System.out.println("SCORE NOT SAVED");
            gui.showAlert("Enter a nickname", "Empty field(s)");
        } else {
            System.out.println(manager.getUsername());
            System.out.println(scoreText.getText());
            database.saveScore(manager.getUsername(),
                    Integer.parseInt(scoreText.getText()),
                    nickname.getText());
            System.out.println("Score Saved");
            gui.showAlert("Your score was saved", "Success!");
        }
        database.closeConnection();
    }

    /**
     * This methods go back to the entry page when you click goBack button.
     */
    public void goBackMain() throws IOException {
        gui.switchScene("src/main/resources/fxml/entry.fxml");
    }

    /**
     * No testing required because impossible to test system.exit.
     */
    public void quitButton() {
        gui.quit();
    }
}

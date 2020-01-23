package gui.controller;

import database.DBconnect;
import database.SessionManager;
import gui.Gui;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.Setter;


/**
 * This class is for controller of score screen.
 * This class will be used later in the future. (in progress).
 */
public class ScoreController {
    public Gui gui = new Gui();

    @Getter
    @Setter
    private DBconnect database = DBconnect.getInstance();
    @Getter
    @Setter
    private SessionManager manager = SessionManager.getInstance();

    public TextField nickname;
    public Button saveButton;
    public Text scoreText = new Text();


    /**
     * Method that stores the nickname and the score after each game.
     */
    public void scoreSave() {
        database.openConnection();
        if (gui.getText(nickname).equals("")) {
            gui.showAlert("Enter a nickname", "Empty field(s)");
        } else {
            database.saveScore(manager.getUsername(),
                gui.getScoreFromText(scoreText),
                gui.getText(nickname));
            gui.disableButton(saveButton);
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

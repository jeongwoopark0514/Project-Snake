package gui.controller;

import database.DBconnect;
import database.SessionManager;
import gui.Gui;
import gui.GuiButton;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.Setter;


/**
 * Controller for the score screen.
 * Contains methods for extracting the score from the screen and saving this to the database.
 */
public class ScoreController {
    public Gui gui = new Gui();
    public GuiButton guiButton = new GuiButton();
    public TextField nickname;
    public Button saveButton;
    public Text scoreText = new Text();
    @Getter
    @Setter
    private DBconnect database = DBconnect.getInstance();
    @Getter
    @Setter
    private SessionManager manager = SessionManager.getInstance();

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
            guiButton.disableButton(saveButton);
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

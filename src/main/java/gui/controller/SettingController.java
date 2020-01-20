package gui.controller;

import game.Settings;
import gui.Gui;
import java.io.IOException;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 * This class is a controller class for settings.
 */
public class SettingController {
    public Gui gui = new Gui();

    public RadioButton jungleRadio;
    public RadioButton nightRadio;
    public RadioButton basicRadio;
    public ToggleGroup background;

    public RadioButton easyRadio;
    public RadioButton difficultRadio;
    public RadioButton insaneRadio;
    public ToggleGroup difficulty;

    public RadioButton greenRadio;
    public RadioButton yellowRadio;
    public RadioButton greyRadio;
    public ToggleGroup snakeColor;

    public RadioButton appleOrangeRadio;
    public RadioButton mellonBananaRadio;
    public ToggleGroup pellets;

    /**
     * This method goes back to the Entry page from Settings page.
     * @throws IOException exception for file. (exists or not).
     */
    public void goBackEntry() throws IOException {
        gui.switchScene("src/main/resources/fxml/Entry.fxml");
    }


    /**
     * Changes the background of the game based on which one is selected.
     */
    public void changeBackground() {
        RadioButton selected = (RadioButton)background.getSelectedToggle();
        if (selected.equals(jungleRadio)) {
            Settings.setBackground("/image/jungle_bg.png");
        } else if (selected.equals(nightRadio)) {
            Settings.setBackground("/image/night_bg.png");
        } else if (selected.equals(basicRadio)) {
            Settings.setBackground("");
        }
    }

    /**
     * Changes the difficulty of the game.
     */
    public void changeDifficulty() {
        RadioButton selected = (RadioButton)difficulty.getSelectedToggle();
        if (selected.equals(easyRadio)) {
            Settings.setGameMode(0);
        } else if (selected.equals(difficultRadio)) {
            Settings.setGameMode(1);
        } else if (selected.equals(insaneRadio)) {
            Settings.setGameMode(2);
        }
    }

    /**
     * Changes the color of the snake.
     */
    public void changeSnakeColor() {
        RadioButton selected = (RadioButton)snakeColor.getSelectedToggle();
        if (selected.equals(greenRadio)) {
            Settings.setSnakeColor("green");
        } else if (selected.equals(yellowRadio)) {
            Settings.setSnakeColor("yellow");
        } else if (selected.equals(greyRadio)) {
            Settings.setSnakeColor("grey");
        }
    }

    /**
     * Changes the set of pellets to be used.
     */
    public void changePellets() {
        RadioButton selected = (RadioButton)pellets.getSelectedToggle();
        if (selected.equals(appleOrangeRadio)) {
            Settings.setPellets("apple-orange");
        } else if (selected.equals(mellonBananaRadio)) {
            Settings.setPellets("mellon-banana");
        }
    }
}

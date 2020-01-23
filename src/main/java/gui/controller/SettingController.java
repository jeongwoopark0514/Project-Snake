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
    public RadioButton melonBananaRadio;
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
        gui.setGroup(background);
        if (gui.equalsButton(jungleRadio)) {
            Settings.setBackground("/image/jungle_image.png");
        } else if (gui.equalsButton(nightRadio)) {
            Settings.setBackground("/image/night_image.png");
        } else if (gui.equalsButton(basicRadio)) {
            Settings.setBackground("");
        }
    }

    /**
     * Changes the difficulty of the game.
     */
    public void changeDifficulty() {
        gui.setGroup(difficulty);
        if (gui.equalsButton(easyRadio)) {
            Settings.setGameMode(0);
        } else if (gui.equalsButton(difficultRadio)) {
            Settings.setGameMode(1);
        } else if (gui.equalsButton(insaneRadio)) {
            Settings.setGameMode(2);
        }
    }

    /**
     * Changes the color of the snake.
     */
    public void changeSnakeColor() {
        gui.setGroup(snakeColor);
        if (gui.equalsButton(greenRadio)) {
            Settings.setSnakeColor("green");
        } else if (gui.equalsButton(yellowRadio)) {
            Settings.setSnakeColor("yellow");
        } else if (gui.equalsButton(greyRadio)) {
            Settings.setSnakeColor("grey");
        }
    }

    /**
     * Changes the set of pellets to be used.
     */
    public void changePellets() {
        gui.setGroup(pellets);
        if (gui.equalsButton(appleOrangeRadio)) {
            Settings.setPellets("apple-orange");
        } else if (gui.equalsButton(melonBananaRadio)) {
            Settings.setPellets("melon-banana");
        }
    }
}

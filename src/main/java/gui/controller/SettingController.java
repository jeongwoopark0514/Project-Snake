package gui.controller;

import game.GameSettings;
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
    public ToggleGroup themes;

    /**
     * This method goes back to the Entry page from Settings page.
     * @throws IOException exception for file. (exists or not).
     */
    public void goBackEntry() throws IOException {
        gui.switchScene("src/main/resources/fxml/Entry.fxml");
    }


    /**
     * Changes the theme of the game based on which one is selected.
     */
    public void changeThemes() {
        RadioButton selected = (RadioButton)themes.getSelectedToggle();
        if (selected.equals(jungleRadio)) {
            GameSettings.background = "/image/jungle_bg.png";
        } else if (selected.equals(nightRadio)) {
            GameSettings.background = "/image/night.png";
        } else if (selected.equals(basicRadio)) {
            GameSettings.background = "basic";
        }
    }
}

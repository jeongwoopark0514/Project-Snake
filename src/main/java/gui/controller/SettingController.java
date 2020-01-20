package gui.controller;

import gui.Gui;
import javafx.scene.control.RadioButton;

import java.io.IOException;

/**
 * This class is a controller class for settings.
 */
public class SettingController {
    public Gui gui = new Gui();

    public RadioButton jungleRadio;
    public RadioButton nightRadio;

    /**
     * This method goes back to the Entry page from Settings page.
     * @throws IOException exception for file. (exists or not).
     */
    public void goBackEntry() throws IOException {
        gui.switchScene("src/main/resources/fxml/Entry.fxml");
    }


    public void changeToJungle() {

    }

    public void changeToNight() {
    }
}

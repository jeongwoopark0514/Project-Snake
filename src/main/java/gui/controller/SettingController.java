package gui.controller;

import gui.Gui;

import java.io.IOException;

/**
 * This class is a controller class for settings.
 */
public class SettingController {
    public transient Gui gui = new Gui();

    /**
     * This method goes back to the Entry page from Settings page.
     * @throws IOException exception for file. (exists or not).
     */
    public void goBackEntry() throws IOException {
        gui.switchScene("src/main/resources/fxml/Entry.fxml");
    }
}

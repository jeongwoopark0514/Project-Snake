package gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

/**
 * Contains all the methods needed for controller logic.
 */
public class Gui {


    /**
     * This method pops up an alert box that gives notifications.
     *
     * @param message - message of the alert box
     * @param title   - title of the alert
     */
    public void showAlert(String message, String title) {
        AlertBox.display(message, title);
    }

    /**
     * This method changes the url for the respective scenes.
     *
     * @param path - the url for the scene
     * @throws IOException When path not available.
     */
    public void switchScene(String path) throws IOException {
        final URL url = new File(path).toURI().toURL();
        final Parent entryParent = FXMLLoader.load(url);
        MainRunner.stage.setScene(new Scene(entryParent, 1000, 600));
    }

    /**
     * Getting text from textfield.
     * @param any Textfield
     * @return content of textfield
     */
    public String getText(TextField any) {
        return any.getText();
    }

}


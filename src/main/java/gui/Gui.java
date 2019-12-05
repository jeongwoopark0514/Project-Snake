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

    /**
     * Test if the items are null.
     * @param any1 first string to check
     * @param any2 second string to check
     * @param any3 third string to check
     * @return boolean result
     */
    public boolean threeAllCorrect(TextField any1, TextField any2, TextField any3) {
        return getText(any1).equals("")
            || getText(any2).equals("")
            || getText(any3).equals("");
    }

    /**
     * Test if the items are equal.
     * @param any1 first string to check
     * @param any2 second string to check
     * @return boolean result
     */
    public boolean registerAndConfirm(TextField any1, TextField any2) {
        return getText(any1).equals(getText(any2));
    }

    /**
     * Check if either of them are null or not.
     * @param any1 first textfield to check
     * @param any2 second textfield to check
     * @return boolean result
     */
    public boolean loginUserOrPassEmpty(TextField any1, TextField any2) {
        return getText(any1).equals("") || getText(any2).equals("");
    }
}


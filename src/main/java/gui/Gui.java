package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Gui {


    /**
     * This method pops up an alert box that gives notifications
     * @param message - message of the alert box
     * @param title - title of the alert
     */
    public void showAlert(String message, String title) {
        AlertBox.display(message,title);
    }

    /**
     * This method changes the url for the respective scenes
     * @param path - the url for the scene
     * @throws IOException
     */
    public void switchScene(String path) throws IOException {
        final URL url = new File(path).toURI().toURL();
        final Parent entryParent = FXMLLoader.load(url);
        MainRunner.stage.setScene(new Scene(entryParent, 1000, 600));
    }
}


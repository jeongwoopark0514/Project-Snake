package gui;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertBox {

    /**
     * Displays the string and the alert box.
     *
     * @param message the message being displayed
     */

    public void display(String message, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Displays the warning alert box.
     *
     * @param message the message being displayed
     * @param title title of the alert box
     */

    public void displayWarning(String message, String title) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Displays the alert box when you want to quit the game.
     * @param message the message to ask.
     * @param title title of the alert box
     */
    public static void displayQuit(String message, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
        if (ButtonType.OK == alert.getResult()) {
            System.exit(0); //NOPMD
        }
    }
}

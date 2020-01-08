package gui;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertBox {

    /**
     * Displays the string and the alert box.
     *
     * @param message the message being displayed
     */

    public static void display(String message, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();

        //comment out because the code above is shorter and does the same job
        //        Stage window = new Stage();
        //
        //        window.initModality(Modality.APPLICATION_MODAL);
        //        window.setTitle(title);
        //        window.setMinWidth(400);
        //
        //        Label label = new Label(message);
        //
        //        Button close = new Button("Ok");
        //        close.setOnAction(e -> window.close());
        //        close.setPadding(new Insets(10, 10, 10, 10));
        //
        //        VBox layout = new VBox(10);
        //        layout.getChildren().addAll(label, close);
        //        layout.setAlignment(Pos.CENTER);
        //
        //        Scene scene = new Scene(layout);
        //        window.setScene(scene);
        //        window.showAndWait();
    }

    /**
     * Displays the warning alert box.
     *
     * @param message the message being displayed
     * @param title title of the alert box
     */

    public static void displayWarning(String message, String title) {
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

package gui;

import javafx.scene.control.Alert;

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
     */

    public static void displayWarning(String message, String title) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

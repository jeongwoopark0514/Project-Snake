package gui;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Gui {
    public void showAlert(String message, String title) {
        AlertBox.display("One or multiple fields have not been filled in!",
            "Empty field(s)");
    }

    public void switchScene() throws IOException {
        final URL url = new File("src/main/resources/fxml/entry.fxml").toURI().toURL();
        final Parent entryParent = FXMLLoader.load(url);
        MainRunner.stage.setScene(new Scene(entryParent, 1000, 600));
    }
}


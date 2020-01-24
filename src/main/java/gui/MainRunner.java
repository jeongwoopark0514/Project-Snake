package gui;

import database.DBconnect;
import database.SessionManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * MainRunner class that starts the application.
 */
public class MainRunner extends Application {

    public static Stage stage;

    public static void main(String[] args) {
        Application.launch(args);
    }

    /**
     * Starts the application.
     *
     * @param primaryStage the initial stage that should be shown.
     * @throws IOException iff the file does not exist or if the url is invalid
     */
    @Override
    public void start(Stage primaryStage) throws IOException {

        initialize(primaryStage);

        final URL url2;
        final Parent entry;
        //This is a false positive,
        // since the reader is actually closed inside the SessionManger class.
        BufferedReader reader = new BufferedReader(new FileReader("cookie.txt")); //NOPMD
        DBconnect database = DBconnect.getInstance();
        database.openConnection();
        if (SessionManager.getInstance().retrieveUserData(reader) != null) {
            url2 = new File("src/main/resources/fxml/entry.fxml").toURI().toURL();
            entry = FXMLLoader.load(url2);
        } else {
            url2 = new File("src/main/resources/fxml/login.fxml").toURI().toURL();
            entry = FXMLLoader.load(url2);
        }
        database.closeConnection();

        manageScene(entry);
    }

    /**
     * Sets up the main screen that is shown when starting the application.
     *
     * @param primaryStage the initial stage that should be shown.
     */
    private void initialize(Stage primaryStage) {
        stage = primaryStage;
        final URL url;
        final Parent parent;
        try {
            url = new File("src/main/resources/fxml/splash.fxml").toURI().toURL();
            parent = FXMLLoader.load(url);
            Scene splash = new Scene(parent, 1000, 600);
            stage.setScene(splash);
        } catch (Exception e) {
            e.printStackTrace();
        }

        stage.getIcons().add(new Image("image/transparent_logo.png"));
        stage.setTitle("Snake");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Sets up the entry scene of the application.
     *
     * @param entry is either the login screen (if user is not logged in)
     *              or the splash screen (if user is already logged in)
     */
    private void manageScene(Parent entry) {
        //first suppressed PMD error since it is not a big bug,
        //but will fix if it is fixable, or worth fixable
        final Scene entryScene = new Scene(entry, 1000, 600); //NOPMD
        final PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> stage.setScene(entryScene));
        pause.play();
    }

}

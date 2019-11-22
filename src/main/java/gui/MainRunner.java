package gui;

import java.io.File;
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
public class MainRunner extends Application { //NOPMD

    private Stage stage;

    public static void main(String[] args) { //NOPMD
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException { //NOPMD
        stage = primaryStage;
        final URL url = new File("src/main/resources/fxml/splash.fxml").toURI().toURL();
        final Parent parent = FXMLLoader.load(url);

        stage.getIcons().add(new Image("image/transparent_logo.png"));
        stage.setTitle("Snake");
        stage.setResizable(false);
        final Scene splash = new Scene(parent, 1000,600);
        stage.setScene(splash);
        stage.show();

        final URL url2 = new File("src/main/resources/fxml/login.fxml").toURI().toURL();
        final Parent login = FXMLLoader.load(url2);

        //first suppressed PMD error since it is not a big bug,
        //but will fix if it is fixable, or worth fixable
        final Scene loginScene = new Scene(login, 1000, 600); //NOPMD
        final PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> stage.setScene(loginScene));
        pause.play();
    }

    
}

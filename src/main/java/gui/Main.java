package gui;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL url = new File("src/main/resources/fxml/splash.fxml").toURI().toURL();
        Parent parent = FXMLLoader.load(url);
        primaryStage.setTitle("Snake");
        primaryStage.setResizable(false);
        Scene splash = new Scene(parent, 1000,600);
        primaryStage.setScene(splash);
        primaryStage.show();

        URL url2 = new File("src/main/resources/fxml/login.fxml").toURI().toURL();
        Parent login = FXMLLoader.load(url2);
        Scene loginScene = new Scene(login, 1000, 600);
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> primaryStage.setScene(loginScene));
        pause.play();
    }
}

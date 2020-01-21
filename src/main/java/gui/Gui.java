package gui;

import static game.Directions.DOWN;
import static game.GameSettings.BACKGROUND_COLOR;
import static game.GameSettings.HEIGHT;
import static game.GameSettings.TEXT_COLOR;
import static game.GameSettings.WIDTH;

import game.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import lombok.Getter;
import lombok.Setter;


/**
 * Contains all the methods needed for controller logic.
 */
public class Gui {

    @Getter @Setter
    public FXMLLoader loader;

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
     * This method pops up a warning alert box that gives notifications.
     *
     * @param message - message of the alert box (about warning)
     * @param title   - title of the alert
     */
    public void showWarningAlert(String message, String title) {
        AlertBox.displayWarning(message, title);
    }


    /**
     * This method changes the url for the respective scenes.
     *
     * @param path - the url for the scene
     * @throws IOException When path not available.
     */
    public void switchScene(String path) throws IOException {
        final URL url = new File(path).toURI().toURL();
        loader = new FXMLLoader();
        loader.setLocation(url);
        Parent entryParent = loader.load();
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
     * Make GUI Scene for the snake game.
     */
    public void startSnakeGame() {
        final Canvas canvas = new Canvas(WIDTH, HEIGHT);
        final GraphicsContext gc = canvas.getGraphicsContext2D();

        // Text element to show score.
        final Text score = new Text();
        score.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        score.setFill(TEXT_COLOR);
        score.setX(1060);
        score.setY(60);

        // Text element to show score.
        final Text pauseText = new Text();
        pauseText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        pauseText.setFill(TEXT_COLOR);
        pauseText.setX(1060);
        pauseText.setY(120);

        // Pause button
        Button pauseButton = new Button("Pause");
        pauseButton.setLayoutX(1068);
        pauseButton.setLayoutY(350);
        pauseButton.setPrefSize(70,40);

        // Stop button
        Button stopButton = new Button("stop");
        stopButton.setLayoutX(1068);
        stopButton.setLayoutY(420);
        stopButton.setPrefSize(70,40);

        // Add elements to scene
        Pane root = new Pane();
        root.getChildren().add(canvas);
        root.getChildren().add(score);
        root.getChildren().add(pauseButton);
        root.getChildren().add(stopButton);
        root.getChildren().add(pauseText);
        root.getStylesheets().add("/css/GameButton.css");
        if (!Settings.getBackground().equals("")) {
            BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO,
                    BackgroundSize.AUTO, false,false,false,false);
            root.setBackground(new Background(new BackgroundImage(
                    new Image(Settings.getBackground()),
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    backgroundSize)));
        } else {
            BackgroundFill bgf = new BackgroundFill(Color.BLACK, null, null);
            Background bg = new Background(bgf);
            root.setBackground(bg);
        }

        Scene scene = new Scene(root, WIDTH, HEIGHT, BACKGROUND_COLOR);

        Painter painter = new Painter(gc);

        Snake snake = new Snake(new BodyPart(10, 10,
            GameSettings.SNAKE_COLOR, GameSettings.SNAKE_HEAD), DOWN);
        Game game = new Game(scene, painter, canvas, snake, score, pauseText);

        // Add action listener to pause button.
        pauseButton.setOnAction(event -> {
            game.pause();
        });

        stopButton.setOnAction(event -> {
            game.stop();
        });

        snake.setGame(game);

        game.start();

        MainRunner.stage.setScene(scene);
    }

    /**
     * Quit the game by closing the window.
     */
    public void quit() {
        AlertBox.displayQuit("Do you really want to quit? ", "Game over");
    }

    public void setText(Text text, String setting) {
        text.setText(setting);
    }

    public int getScoreFromText(Text text) {
        return Integer.parseInt(text.getText());
    }


}


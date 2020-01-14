package gui;

import static game.Directions.DOWN;
import static game.GameSettings.BACKGROUND_COLOR;
import static game.GameSettings.HEIGHT;
import static game.GameSettings.TEXT_COLOR;
import static game.GameSettings.WIDTH;

import game.BodyPart;
import game.Game;
import game.GameSettings;
import game.Painter;
import game.Snake;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

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
     * Make GUI Scene for the snake game.
     */
    public void startSnakeGame() {
        final Canvas canvas = new Canvas(WIDTH, HEIGHT);
        final GraphicsContext gc = canvas.getGraphicsContext2D();

        // Creates a score node that is added to the scene.
        final Text score = new Text();
        score.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        score.setFill(TEXT_COLOR);
        score.setX(1060);
        score.setY(60);

        Button startButton = new Button("start");
        startButton.setLayoutX(1068);
        startButton.setLayoutY(350);
        startButton.setPrefSize(70,40);

        Button stopButton = new Button("stop");
        stopButton.setLayoutX(1068);
        stopButton.setLayoutY(420);
        stopButton.setPrefSize(70,40);

        Group root = new Group();
        Scene scene = new Scene(root, WIDTH, HEIGHT, BACKGROUND_COLOR);

        root.getChildren().add(canvas);
        root.getChildren().add(score);
        root.getChildren().add(startButton);
        root.getChildren().add(stopButton);
        root.getStylesheets().add("/css/GameButton.css");

        Painter painter = new Painter(gc);

        Snake snake = new Snake(new BodyPart(10, 10,
            GameSettings.SNAKE_COLOR, GameSettings.SNAKE_HEAD), DOWN);
        Game game = new Game(scene, painter, canvas, snake, score);

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

}


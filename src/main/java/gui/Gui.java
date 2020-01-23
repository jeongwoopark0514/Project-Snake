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
import game.Settings;
import game.Snake;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
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

    @Getter
    @Setter
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
     *
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

        List<Button> buttons = createButtons();
        List<Text> textElements = createText();

        // Add elements to scene
        Pane root = new Pane();
        root.getChildren().add(canvas);
        root.getChildren().add(buttons.get(0));
        root.getChildren().add(buttons.get(1));
        root.getChildren().add(textElements.get(0));
        root.getChildren().add(textElements.get(1));
        root.getStylesheets().add("/css/GameButton.css");

        if (!Settings.getBackground().equals("")) {
            BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO,
                BackgroundSize.AUTO, false, false, false, false);
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

        Painter painter = new Painter(gc);
        Scene scene = new Scene(root, WIDTH, HEIGHT, BACKGROUND_COLOR);

        Snake snake = new Snake(new BodyPart(10, 10,
            GameSettings.SNAKE_COLOR, GameSettings.SNAKE_HEAD), DOWN);
        Game game = new Game(scene, painter, canvas, snake, textElements);

        // Add action listener to pause button.
        buttons.get(0).setOnAction(event -> {
            game.pause();
        });

        buttons.get(1).setOnAction(event -> {
            game.stop();
        });

        snake.setGame(game);

        game.start();

        MainRunner.stage.setScene(scene);
    }

    /**
     * Quits the game by closing the window.
     */
    public void quit() {
        AlertBox.displayQuit("Do you really want to quit? ", "Game over");
    }

    public void setText(Text text, String setting) {
        text.setText(setting);
    }

    /**
     * Creates a pause and stop button.
     *
     * @return list with pause and stop button.
     */
    private List<Button> createButtons() {
        // Pause button
        Button pauseButton = new Button("Pause");
        pauseButton.setLayoutX(1068);
        pauseButton.setLayoutY(350);
        pauseButton.setPrefSize(70, 40);

        // Stop button
        Button stopButton = new Button("Stop");
        stopButton.setLayoutX(1068);
        stopButton.setLayoutY(420);
        stopButton.setPrefSize(70, 40);

        return Arrays.asList(pauseButton, stopButton);
    }

    /**
     * Creates text elements for score and pause.
     *
     * @return list with text elements for score and pause.
     */
    private List<Text> createText() {
        // Text element to show score.
        Text scoreText = new Text();
        scoreText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        scoreText.setFill(TEXT_COLOR);
        scoreText.setX(1060);
        scoreText.setY(60);

        // Text element to show score.
        Text pauseText = new Text();
        pauseText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        pauseText.setFill(TEXT_COLOR);
        pauseText.setX(1060);
        pauseText.setY(120);

        return Arrays.asList(scoreText, pauseText);
    }

}


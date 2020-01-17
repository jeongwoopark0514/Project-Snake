package game;

import static game.Directions.DOWN;
import static game.GameSettings.BACKGROUND_COLOR;
import static game.GameSettings.HEIGHT;
import static game.GameSettings.TEXT_COLOR;
import static game.GameSettings.WIDTH;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.sound.sampled.LineUnavailableException;

/**
 * This class is only used for testing purposes. This way we can directly go
 * to the game screen and we do not have to click through splash and register screen.
 */
public class SnakeApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Method to start the game directly without starting screen.
     *
     * @param primaryStage stage to use.
     */
    @Override
    public void start(Stage primaryStage) {
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
        Group root = new Group();
        root.getChildren().add(canvas);
        root.getChildren().add(score);
        root.getChildren().add(pauseButton);
        root.getChildren().add(stopButton);
        root.getChildren().add(pauseText);
        root.getStylesheets().add("/css/GameButton.css");

        Scene scene = new Scene(root, WIDTH, HEIGHT, BACKGROUND_COLOR);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Snake");
        primaryStage.show();

        Painter painter = new Painter(gc);

        Snake snake = new Snake(new BodyPart(10, 10,
            GameSettings.SNAKE_COLOR, GameSettings.SNAKE_HEAD), DOWN);

        try {
            Game game = new Game(scene, painter, canvas, snake, score, pauseText);
            snake.setGame(game);
            game.start();
            // Add action listener to pause button.
            pauseButton.setOnAction(event -> {
                game.pause();
            });
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

    }
}

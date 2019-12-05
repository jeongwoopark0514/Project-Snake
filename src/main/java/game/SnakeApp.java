package game;

import static game.Directions.DOWN;
import static game.GameSettings.BACKGROUND_COLOR;
import static game.GameSettings.HEIGHT;
import static game.GameSettings.WIDTH;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

/**
 * This class is for debugging purposes so not actual production code.
 */
public class SnakeApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Method to start the game directly without starting screen.
     * @param primaryStage stage to use.
     */
    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, WIDTH, HEIGHT, BACKGROUND_COLOR);

        final Canvas canvas = new Canvas(WIDTH, HEIGHT);

        final GraphicsContext gc = canvas.getGraphicsContext2D();

        root.getChildren().add(canvas);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Snake");
        primaryStage.show();
        Painter painter = new Painter(gc);
        Snake snake = new Snake(new Point(10, 10), DOWN);
        Game game = new Game(scene, painter, canvas, snake);
        snake.setGame(game);

        game.start();
    }
}

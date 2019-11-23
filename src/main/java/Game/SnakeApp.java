package Game;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import static Game.Directions.LEFT;
import static Game.GameSettings.BACKGROUND_COLOR;
import static Game.GameSettings.HEIGHT;
import static Game.GameSettings.WIDTH;

public class SnakeApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene s = new Scene(root, WIDTH, HEIGHT, BACKGROUND_COLOR);

        final Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        root.getChildren().add(canvas);

        primaryStage.setScene(s);
        primaryStage.setTitle("Snake");
        primaryStage.show();

        Snake snake = new Snake(new Point(5, 5), LEFT);
        Painter.paintSnake(gc, snake);

        Painter.paintFruit(gc, new Point(8, 3));
    }
}

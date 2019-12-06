package gui.controller;

import static game.Directions.DOWN;
import static game.GameSettings.BACKGROUND_COLOR;
import static game.GameSettings.HEIGHT;
import static game.GameSettings.WIDTH;

import game.Game;
import game.Painter;
import game.Point;
import game.Snake;
import gui.MainRunner;
import java.io.IOException;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * This class does not need to be tested.
 * This is all GUI class.
 */
public class EntryController {
    /**
     * When you click start button, move to game screen.
     *
     * @throws IOException IOexception thrown for null file.
     */
    public void startGame() throws IOException {
        Group root = new Group();
        Scene scene = new Scene(root, WIDTH, HEIGHT, BACKGROUND_COLOR);
        final Canvas canvas = new Canvas(WIDTH, HEIGHT);
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        Painter painter = new Painter(gc);
        Snake snake = new Snake(new Point(10, 10), DOWN);
        Game game = new Game(scene, painter, canvas, snake);
        snake.setGame(game);
        game.start();
        MainRunner.stage.setScene(scene);

    }
}

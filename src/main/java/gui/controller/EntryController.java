package gui.controller;

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
import gui.MainRunner;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * This class does not need to be tested.
 * This is all GUI class.
 */
public class EntryController {
    /**
     * When you click start button, move to game screen.
     */
    public void startGame() {
        final Canvas canvas = new Canvas(WIDTH, HEIGHT);
        final GraphicsContext gc = canvas.getGraphicsContext2D();

        // Creates a score node that is added to the scene.
        final Text score = new Text();
        score.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        score.setFill(TEXT_COLOR);
        score.setX(50);
        score.setY(130);
        score.setText("Score: 0");

        Group root = new Group();
        Scene scene = new Scene(root, WIDTH, HEIGHT, BACKGROUND_COLOR);

        root.getChildren().add(canvas);
        root.getChildren().add(score);

        Painter painter = new Painter(gc);
        Snake snake = new Snake(new BodyPart(10, 10, GameSettings.SNAKE_COLOR, null), DOWN);

        Game game = new Game(scene, painter, canvas, snake, score);
        snake.setGame(game);
        game.start();
        MainRunner.stage.setScene(scene);
    }
}

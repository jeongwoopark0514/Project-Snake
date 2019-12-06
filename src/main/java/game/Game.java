package game;

import static game.GameSettings.X_MAX;
import static game.GameSettings.Y_MAX;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import lombok.Getter;
import lombok.NonNull;

/**
 * Main game control class.
 */
public class Game {
    @Getter
    private final transient Scene scene;
    @Getter
    private final transient Painter painter;
    @Getter
    private final transient Canvas canvas;
    @Getter
    private final transient Snake snake;
    private transient Point fruit;
    @Getter
    private final transient ScheduledExecutorService scheduler =
        Executors.newScheduledThreadPool(1);
    private transient ScheduledFuture<?> loop;

    /**
     * Constructor.
     *
     * @param scene   Scene related to the game
     * @param painter Painter class that paints all the objects on the screen
     * @param canvas  Canvas canvas to paint on
     * @param snake   Snake the actual snake that moves trough the game
     */
    public Game(Scene scene, Painter painter, Canvas canvas, Snake snake) {
        this.scene = scene;
        this.canvas = canvas;
        this.snake = snake;
        this.painter = painter;
        init();
    }

    /**
     * Starts the game.
     * Draws a fruit on random place in the game window and then starts the game loop.
     */
    public void start() {
        drawFruit();
        gameLoop();
    }

    /**
     * TODO: TO BE IMPLEMENTED.
     * Stops the game.
     */
    public void stop() {
        //This is just for the prototype the actual game will not use this,
        //therefore it needs to be suppressed.
        System.exit(0); //NOPMD
    }

    ///**
    // * TODO: TO BE IMPLEMENTED.
    // * Pauzes the game.
    // */
    //public void pause() {
    //}

    /**
     * Defines a method move that clears current position of the snake on the canvas,
     * moves the snake and than paints the new position of the snake. Also checks if new
     * snake collides with the fruit.
     * Move is scheduled to execute every 100 milliseconds.
     */
    private void gameLoop() {
        Runnable move = () -> {
            painter.unpaintSnake(snake);
            snake.move();
            if (collides(snake.getHead(), fruit)) {
                drawFruit();
                Logger.getLogger("Collision detected.");
            }
            painter.paintSnake(snake);
        };

        loop = scheduler.scheduleAtFixedRate(move, 0, 100, MILLISECONDS);
    }

    /**
     * Initializes the game by setting the on-key-pressed listeners (for arrow buttons) and
     * sets focus on canvas.
     */
    private void init() {
        setOnKeyPressedListener();
        canvas.requestFocus();
        paintWalls();
    }

    /**
     * Simple method to paint walls on the borders of the map.
     */
    private void paintWalls() {
        for (int i = 0; i < GameSettings.Y_MAX; i++) {
            painter.paintWall(new Point(0, i));
        }
        for (int i = 0; i < GameSettings.X_MAX; i++) {
            painter.paintWall(new Point(i, 0));
        }
        for (int i = 0; i < GameSettings.Y_MAX; i++) {
            painter.paintWall(new Point(GameSettings.X_MAX - 1, i));
        }
        for (int i = 0; i < GameSettings.X_MAX; i++) {
            painter.paintWall(new Point(i, GameSettings.Y_MAX - 1));
        }
    }

    /**
     * Draws a piece of fruit on the game window.
     */
    private void drawFruit() {
        int x = ThreadLocalRandom.current().nextInt(1, X_MAX - 2);
        int y = ThreadLocalRandom.current().nextInt(1, Y_MAX - 2);

        painter.paintFruit(fruit = new Point(x, y));
    }

    /**
     * Checks whether two points collide with each other. This is
     * equivalent to check if two points are equal.
     *
     * @param p1 Point 1
     * @param p2 Point 2
     * @return True if two points are equal.
     */
    private boolean collides(@NonNull Point p1, @NonNull Point p2) {
        return p1.equals(p2);
    }

    /**
     * Adds event listeners for arrow keys.
     */
    private void setOnKeyPressedListener() {
        canvas.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case LEFT:
                    snake.changeDirection(Directions.LEFT);
                    break;
                case RIGHT:
                    snake.changeDirection(Directions.RIGHT);
                    break;
                case UP:
                    snake.changeDirection(Directions.UP);
                    break;
                case DOWN:
                    snake.changeDirection(Directions.DOWN);
                    break;
                default:
                    break;
            }
        });
    }
}

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
 * A game.
 * TODO: Write a better description of this class.
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
     * @param scene   Scene
     * @param painter Painter
     * @param canvas  Canvas
     * @param snake   Snake
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

    ///**
    //* TODO: TO BE IMPLEMENTED.
    //* Stops the game.
    //*/
    // public void stop() {
    //}

    ///**
    // * TODO: TO BE IMPLEMENTED.
    // * Pauzes the game.
    // */
    //public void pauze() {
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
    }

    /**
     * Draws a piece of fruit on the game window.
     */
    private void drawFruit() {
        int x = ThreadLocalRandom.current().nextInt(0, X_MAX - 1);
        int y = ThreadLocalRandom.current().nextInt(0, Y_MAX - 1);

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

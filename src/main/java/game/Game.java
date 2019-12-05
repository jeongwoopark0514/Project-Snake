package game;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;

/**
 * A game.
 * TODO: Write a better description of this class.
 */
public class Game {
    private final transient Scene scene;
    private final transient Painter painter;
    private final transient Canvas canvas;
    private final transient Snake snake;

    private final transient ScheduledExecutorService scheduler =
        Executors.newScheduledThreadPool(1);

    private transient ScheduledFuture<?> loop;

    /**
     * Constructor.
     *
     * @param scene  Scene
     * @param painter Painter
     * @param canvas Canvas
     * @param snake  Snake
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
     */
    public void start() {
        gameLoop();
    }

    /**
     * TODO: TO BE IMPLEMENTED.
     * Stops the game.
     */
    public void stop() {
    }

    /**
     * TODO: TO BE IMPLEMENTED.
     * Pauzes the game.
     */
    public void pause() {
    }

    /**
     * Defines a method move that clears current position of the snake on the canvas,
     * moves the snake and than paints the new position of the snake.
     * Move is scheduled to execute every 100 milliseconds.
     */
    private void gameLoop() {
        Runnable move = () -> {
            painter.unpaintSnake(snake);
            snake.move();
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

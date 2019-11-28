package game;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Game {
    private final transient Scene scene;
    private final transient GraphicsContext gc;
    private final transient Canvas canvas;
    private final transient Snake snake;
    private final transient Painter painter;

    private final transient ScheduledExecutorService scheduler =
        Executors.newScheduledThreadPool(1);

    private transient ScheduledFuture<?> loop;

    /**
     * Constructor.
     *
     * @param scene  Scene
     * @param gc     GraphicalContext
     * @param canvas Canvas
     * @param snake  Snake
     */
    public Game(Scene scene, GraphicsContext gc, Canvas canvas, Snake snake) {
        this.scene = scene;
        this.gc = gc;
        this.canvas = canvas;
        this.snake = snake;
        this.painter = new Painter();
        init();
    }

    public void start() {
        gameLoop();
    }

    public void stop() {
    }

    public void pauze() {
    }

    private void gameLoop() {
        Runnable move = () -> {
            painter.unpaintSnake(gc, snake);
            snake.move();
            painter.paintSnake(gc, snake);
        };

        loop = scheduler.scheduleAtFixedRate(move, 0, 100, MILLISECONDS);
    }

    private void init() {
        setOnKeyPressedListener();
        canvas.requestFocus();
    }

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

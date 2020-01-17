package game;

import static game.GameSettings.MIN_PELLETS;
import static game.GameSettings.X_MAX;
import static game.GameSettings.Y_MAX;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.text.Text;
import javax.sound.sampled.LineUnavailableException;
import lombok.Getter;
import lombok.Setter;

/**
 * Main game control class.
 */
public class Game {
    @Getter
    private final Scene scene;
    @Getter
    private final Painter painter;
    @Getter
    private final Canvas canvas;
    @Getter
    private final Snake snake;
    @Getter
    private final ScheduledExecutorService scheduler =
        Executors.newScheduledThreadPool(1);
    //Made the fruits a list to provide the option to add multiple fruits.
    @Getter
    @Setter
    private List<Fruit> fruits;
    private List<Wall> walls;
    @Getter
    private int score;
    private Text scoreText;
    private Board board;
    private CollisionManager collisionManager;

    /**
     * The constructor of the game object.
     * The Game class runs the main structure of the game,
     * it can create elements needed to play and also manages the score and the controls.
     *
     * @param scene     the scene in which the game is painted.
     * @param painter   the painter that paints all the elements.
     * @param canvas    the canvas to paint on.
     * @param snake     the snake that represents the player on the board.
     * @param scoreText the element representing the player score.
     */
    public Game(Scene scene, Painter painter, Canvas canvas, Snake snake, Text scoreText)
        throws LineUnavailableException {
        this.scene = scene;
        this.canvas = canvas;
        this.snake = snake;
        this.painter = painter;
        this.fruits = new ArrayList<>();
        this.scoreText = scoreText;
        this.score = 0;

        //This would only be an error if we had subclasses extending from the game class,
        //but since this is not the case this doesn't actually pose a risk.
        init();//NOPMD
    }

    /**
     * Initializes the game by setting the on-key-pressed listeners (for arrow buttons) and
     * sets focus on canvas.
     * Also creates the walls, the initial fruits, add everything to the board,
     * and makes sure that the elements are all drawn.
     * Also initializes the collisionManager,
     * which is used to determine if the snake collides with other objects.
     */
    private void init() throws LineUnavailableException {
        canvas.requestFocus();
        setOnKeyPressedListener();
        createWalls();

        // collect all tile elements in ArrayList
        List<Tile> elements = new ArrayList<>();
        elements.addAll(fruits);
        elements.addAll(walls);
        elements.add(snake.getHead());
        // build the board
        this.board = new BoardBuilder()
            .withDimensions(X_MAX, Y_MAX)
            .withBackground("image/background.png")
            .withElements(elements)
            .build();

        checkFruits();
        collisionManager = new CollisionManager(board, snake, this);
        painter.paintBoard(board);
    }

    void checkFruits() {
        if (fruits.size() < MIN_PELLETS) {
            Fruit newFruit = createFruit();
            fruits.add(newFruit);
            board.updateTile(newFruit.getX(), newFruit.getY(), newFruit);
        }
    }

    /**
     * Starts the game, meaning it will start running the game loop.
     */
    public void start() {
        gameLoop();
    }

    /**
     * Used for stopping a game.
     */
    public void stop() {
        scheduler.shutdown();
    }

    ///**
    // * TODO: TO BE IMPLEMENTED.
    // * Pauses the game.
    // */
    //public void pause() {
    //}

    /**
     * This is the main loop of the game.
     * It makes sure to check for collision, redraw elements that have changed,
     * and moves the snake.
     * The loop will e executed on scheduled intervals to make sure the game keeps running.
     */
    private void gameLoop() {
        // PMD sees this as a DU-Anomaly, this would mean that move is undefined when leaving scope.
        // But this is not actually the case since it is used in the scheduler.
        Runnable move = () -> { //NOPMD
            painter.unPaint(snake.getBody());
            Tile tail = snake.getTail();
            board.updateTile(tail.getX(), tail.getY(), null);
            snake.move();
            try {
                if (collisionManager.check()) {
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            painter.writeScore(scoreText, score);
            Tile head = snake.getHead();
            board.updateTile(head.getX(), head.getY(), head);
            painter.paint(snake.getBody());
        };

        scheduler.scheduleAtFixedRate(move, 0, 1000, MILLISECONDS);
    }

    /**
     * Increase the score of the player.
     *
     * @param value the value to increase the score with.
     */
    void increaseScore(int value) {
        score += value;
    }

    /**
     * Simple method to create walls objects to be put onto the map.
     */
    private void createWalls() {
        walls = new ArrayList<>();
        for (int i = 0; i < GameSettings.Y_MAX; i++) {
            walls.add(new Wall(0, i, GameSettings.WALL_COLOR, null));
        }
        for (int i = 0; i < GameSettings.X_MAX; i++) {
            walls.add(new Wall(i, 0, GameSettings.WALL_COLOR, null));
        }
        for (int i = 0; i < GameSettings.Y_MAX; i++) {
            walls.add(new Wall(GameSettings.X_MAX - 1, i, GameSettings.WALL_COLOR, null));
        }
        for (int i = 0; i < GameSettings.X_MAX; i++) {
            walls.add(new Wall(i, GameSettings.Y_MAX - 1, GameSettings.WALL_COLOR, null));
        }
    }

    /**
     * Create a piece of fruit to be put onto the map.
     * The method makes sure that the place the fruits spawns is actually empty.
     */
    public Fruit createFruit() {
        int x = ThreadLocalRandom.current().nextInt(1, X_MAX - 2);
        int y = ThreadLocalRandom.current().nextInt(1, Y_MAX - 2);
        Tile tile = board.getTile(x, y);
        if (tile != null) {
            return createFruit();
        } else {
            return new Fruit(x, y, GameSettings.FRUIT_COLOR, null, 10);
        }
        //Image sprite = new Image("/image/apple_pellet.png");
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

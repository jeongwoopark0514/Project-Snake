package game;

import static game.GameSettings.X_MAX;
import static game.GameSettings.Y_MAX;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.text.Text;
import lombok.Getter;

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
    @Getter
    private final transient ScheduledExecutorService scheduler =
        Executors.newScheduledThreadPool(1);
    //Made the fruits a list to provide the option to add multiple fruits.
    @Getter
    private transient List<Fruit> fruits;
    @Getter
    private transient List<Wall> walls;
    private transient int score;
    private transient Text scoreText;
    private transient Board board;
    private transient BoardFactory factory;


    /**
     * Constructor.
     *
     * @param scene   Scene related to the game
     * @param painter Painter class that paints all the objects on the screen
     * @param canvas  Canvas canvas to paint on
     * @param snake   Snake the actual snake that moves trough the game
     */
    public Game(Scene scene, Painter painter, Canvas canvas, Snake snake, Text scoreText) {
        this.scene = scene;
        this.canvas = canvas;
        this.snake = snake;
        this.painter = painter;
        this.fruits = new ArrayList<>();
        this.scoreText = scoreText;
        this.score = 0;
        this.factory = new BoardFactory("/image/background.png");
        factory.addSnake(snake);
        init();
    }

    /**
     * Starts the game.
     * Draws a fruit on random place in the game window and then starts the game loop.
     */
    public void start() {
        createFruit();
        factory.addFruit(fruits.get(0));
        board = factory.createBoard(X_MAX, Y_MAX);
        System.out.println(board.board.get(0).get(0));
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
            painter.unPaint(snake.getBody());
            snake.move();
            manageFruits();
            checkWalls();
            checkBody();
            painter.paint(snake.getBody());
        };

        scheduler.scheduleAtFixedRate(move, 0, 100, MILLISECONDS);
    }

    /**
     * Test collision of the snake with all fruits on the map.
     * If the fruit was hit, make sure it gets removed.
     * Make sure there is always a fruit on the map and repaint all fruits on the map.
     */
    private void manageFruits() {
        for (int i = 0; i < fruits.size(); i++) {
            Fruit fruit = (Fruit) fruits.get(i);
            if (snake.getHead().checkSameCoords(fruit)) {
                score += fruit.getValue();
                scoreText.setText("Score: " + score);
                fruits.remove(i);
                painter.unPaint(fruit);
                snake.grow();
                Logger.getLogger("Collision detected.");
                break;
            }
        }
        if (fruits.size() < GameSettings.MIN_PELLETS) {
            createFruit();
        }
        painter.paint(fruits);
    }

    /**
     * Method that check collision of the snake with all walls on the map,
     * calls the stop method if a wall was hit.
     */
    private void checkWalls() {
        for (Wall wall : walls) {
            if (snake.getHead().checkSameCoords(wall)) {
                stop();
            }
        }
    }

    /**
     * Method that check collision of the snake with its own body,
     * calls the stop method if a wall was hit.
     */
    private void checkBody() {
        for (Tile bp : snake.getBody()) {
            if (!bp.equals(snake.getHead()) && !bp.equals(snake.getBody().get(1))
                    && snake.getHead().checkSameCoords(bp)) {
                stop();
            }
        }
    }

    /**
     * Initializes the game by setting the on-key-pressed listeners (for arrow buttons) and
     * sets focus on canvas.
     * Also draws the walls on the board.
     */
    private void init() {
        canvas.requestFocus();
        setOnKeyPressedListener();
        createWalls();
        factory.addWalls(walls);
        painter.paint(walls);
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
     */
    private void createFruit() {
        //Image sprite = new Image("/image/apple_pellet.png");
        int x = ThreadLocalRandom.current().nextInt(1, X_MAX - 2);
        int y = ThreadLocalRandom.current().nextInt(1, Y_MAX - 2);
        Fruit fruit = new Fruit(x, y, GameSettings.FRUIT_COLOR, null, 10);
        fruits.add(fruit);
    }

    /**
     * Adds event listeners for arrow keys.
     */
    private void setOnKeyPressedListener() {
        canvas.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case LEFT:
                    snake.changeDirection(Directions.LEFT);
                    this.snake.setDirection(Directions.LEFT);
                    break;
                case RIGHT:
                    snake.changeDirection(Directions.RIGHT);
                    this.snake.setDirection(Directions.RIGHT);
                    break;
                case UP:
                    snake.changeDirection(Directions.UP);
                    this.snake.setDirection(Directions.UP);
                    break;
                case DOWN:
                    snake.changeDirection(Directions.DOWN);
                    this.snake.setDirection(Directions.DOWN);
                    break;
                default:
                    break;
            }
        });
    }
}

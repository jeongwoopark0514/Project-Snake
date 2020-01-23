package game;

import static game.GameSettings.MIN_PELLETS;
import static game.GameSettings.X_MAX;
import static game.GameSettings.Y_MAX;

import gui.Gui;
import gui.controller.ScoreController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.Setter;

/**
 * Main game control class.
 */
public class Game {
    @Getter
    private final Painter painter;
    @Getter
    private final Canvas canvas;
    @Getter
    private final Snake snake;
    @Getter
    @Setter
    private List<Fruit> fruits;
    private List<Wall> walls;
    @Getter
    private int score;
    private Text scoreText;
    private CollisionManager collisionManager;
    @Getter
    @Setter
    private boolean isPaused;
    private Text pauseText;
    private Board board;
    @Getter
    @Setter
    private AnimationTimer timer;
    private Gui gui;


    /**
     * The constructor of the game object.
     * The Game class runs the main structure of the game,
     * it can create elements needed to play and also manages the score and the controls.
     *
     * @param painter      the painter that paints all the elements.
     * @param canvas       the canvas to paint on.
     * @param snake        the snake that represents the player on the board.
     * @param textElements list of text elements for Score and Pause indication.
     */
    public Game(Painter painter, Canvas canvas, Snake snake, List<Text> textElements) {
        this.canvas = canvas;
        this.snake = snake;
        this.painter = painter;
        this.fruits = new ArrayList<>();
        this.scoreText = textElements.get(0);
        this.pauseText = textElements.get(1);
        this.score = 0;
        this.isPaused = false;
        this.gui = new Gui();

        //This would only be an error if we had subclasses extending from the game class,
        //but since this is not the case this doesn't actually pose a risk.
        init();//NOPMD
    }

    /**
     * Initializes the game by setting the on-key-pressed listeners (for arrow buttons) and sets
     * focus on canvas. Also creates the walls, the initial fruits, add everything to the board,
     * and makes sure that the elements are all drawn. Also initializes the collisionManager,
     * which is used to determine if the snake collides with other objects.
     */
    private void init() {
        canvas.requestFocus();
        setOnKeyPressedListener();
        createWalls();
        System.out.println(Settings.getBackground());
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
        Platform.runLater(() -> {
            try {
                timer.stop();
                gui.switchScene("src/main/resources/fxml/scoreBoard.fxml");
                ScoreController scoreController = gui.loader.getController();
                gui.setText(scoreController.scoreText, score + "");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Pauses the game. If game is in paused state the game will start running again.
     */
    public void pause() {
        if (!isPaused) {
            timer.stop();
            pauseText.setText("Paused");
        } else {
            timer.start();
            pauseText.setText("");
        }
        isPaused = !isPaused;
        canvas.requestFocus();
    }

    /**
     * This is the main loop of the game.
     * It makes sure to check for collision, redraw elements that have changed,
     * and moves the snake.
     * The loop will e executed on scheduled intervals to make sure the game keeps running.
     */
    private void gameLoop() {
        final LongProperty lastUpdateTime = new SimpleLongProperty(0);
        final LongProperty frameCount = new SimpleLongProperty(0);
        this.timer = new AnimationTimer() {
            @Override
            public void handle(long timestamp) {
                if (frameCount.get() % 7 == 0) {
                    if (lastUpdateTime.get() > 0) {
                        painter.unPaint(snake.getBody());
                        Tile tail = snake.getTail();
                        board.updateTile(tail.getX(), tail.getY(), null);
                        snake.move();
                        if (collisionManager.check()) {
                            return;
                        }
                        painter.writeScore(scoreText, score);
                        Tile head = snake.getHead();
                        board.updateTile(head.getX(), head.getY(), head);
                        painter.paint(snake.getBody());
                    }
                }
                frameCount.setValue(frameCount.get() + 1);
                lastUpdateTime.set(timestamp);
            }
        };
        timer.start();
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

        int difficultGameMode = 1;
        if (Settings.getGameMode() == difficultGameMode) {
            extraWalls();
        }
        int insaneGameMode = 2;
        if (Settings.getGameMode() == insaneGameMode) {
            extraWalls();
            extraWalls();
            extraWalls();
        }
    }

    /**
     * Adds extra walls for the difficult and insane game modes.
     */
    private void extraWalls() {
        for (int i = 0; i <= 20; i++) {
            int random1 = new Random().nextInt((X_MAX));
            int random2 = new Random().nextInt((Y_MAX));

            walls.add(new Wall(random1, random2, GameSettings.WALL_COLOR, null));
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
            Fruit fruit = new Fruit(x, y, GameSettings.FRUIT_COLOR, null, 10);
            fruit.randomize(new Random());
            return fruit;
        }
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

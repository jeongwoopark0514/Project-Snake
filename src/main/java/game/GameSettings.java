package game;

import javafx.scene.paint.Color;

/**
 * Game settings.
 */
public class GameSettings {
    // WIDTH and HEIGHT should be multiple of CELL_SIZE
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 500;
    static final int CELL_SIZE = 25;
    static final int X_MAX = WIDTH / CELL_SIZE;
    static final int Y_MAX = HEIGHT / CELL_SIZE;
    static final int MIN_PELLETS = 1;

    public static final Color BACKGROUND_COLOR = Color.BLACK;
    public static final Color SNAKE_COLOR = Color.GREEN;
    public static final Color FRUIT_COLOR = Color.YELLOW;
    public static final Color WALL_COLOR = Color.GRAY;
    public static final Color TEXT_COLOR = Color.WHITE;

}

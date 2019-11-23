package Game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static Game.GameSettings.CELL_SIZE;
import static Game.GameSettings.FRUIT_COLOR;
import static Game.GameSettings.SNAKE_COLOR;

/**
 * Might want to change it into an object. Easier to manage the graphics context.
 */
public class Painter {
    /**
     *
     * @param gc
     * @param point
     * @param fill
     */
    public static void paint(GraphicsContext gc, Point point, Color fill) {
        // paint the cell
        gc.setFill(fill);
        gc.fillRect(point.getX() * CELL_SIZE, point.getY() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    /**
     *
     * @param gc
     * @param snake
     */
    public static void paintSnake(GraphicsContext gc, Snake snake) {
        for (Point point : snake.getBody()) {
            paint(gc, point, SNAKE_COLOR);
        }
    }

    /**
     *
     * @param gc
     * @param point
     */
    public static void paintFruit(GraphicsContext gc, Point point) {
        paint(gc, point, FRUIT_COLOR);
    }

    /**
     * Corrects points that are outside of the dimensions of the field.
     * @param point
     * @return
     */
    private static Point wrap(Point point) {
        return new Point(0, 0);
    }
}

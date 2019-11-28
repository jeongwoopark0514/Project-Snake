package game;

import static game.GameSettings.CELL_SIZE;
import static game.GameSettings.FRUIT_COLOR;
import static game.GameSettings.SNAKE_COLOR;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


/**
 * Used for painting shapes on a canvas.
 */
public class Painter {
    /**
     * General method to draw something on the canvas.
     *
     * @param gc    GraphicsContext
     * @param point Coordinate
     * @param fill  Color to fill with
     */
    private static void paint(GraphicsContext gc, Point point, Color fill) {
        // paint the cell
        gc.setFill(fill);
        gc.fillRect(point.getX() * CELL_SIZE, point.getY() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    /**
     * Removes color from point in grid at coordinate point.
     *
     * @param gc    GraphicsContext
     * @param point Coordinate
     */
    private static void unpaint(GraphicsContext gc, Point point) {
        gc.clearRect(point.getX() * CELL_SIZE, point.getY() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    /**
     * Specific method to draw a snake.
     *
     * @param gc    GraphicsContext
     * @param snake Snake
     */
    public static void paintSnake(GraphicsContext gc, Snake snake) {
        Point point = snake.getBody().get(0);
        paint(gc, point, SNAKE_COLOR);
    }

    /**
     * Calls unpaint on all points in the canvas that represent that snake.
     *
     * @param gc    GraphicsContext
     * @param snake Snake
     */
    public static void unpaintSnake(GraphicsContext gc, Snake snake) {
        Point point = snake.getBody().get(0);
        unpaint(gc, point);
    }

    /**
     * Specific method to draw a piece of fruit.
     *
     * @param gc    GraphicsContext
     * @param point Coordinate
     */
    public static void paintFruit(GraphicsContext gc, Point point) {
        if (point == null) {
            return;
        }
        paint(gc, point, FRUIT_COLOR);
    }

    /**
     * Corrects points that are outside of the dimensions of the field.
     *
     * @param point Point.
     * @return
     */
    private static Point wrap(Point point) {
        return new Point(0, 0);
    }
}

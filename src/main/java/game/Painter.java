package game;

import static game.GameSettings.CELL_SIZE;
import static game.GameSettings.FRUIT_COLOR;
import static game.GameSettings.SNAKE_COLOR;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;


/**
 * Used for painting shapes on a canvas, which usually represents the game screen.
 */
public class Painter {
    @Getter
    @Setter
    private GraphicsContext gc;

    /**
     * Constructor.
     *
     * @param gc GraphicalContext for this Painter object.
     */
    public Painter(GraphicsContext gc) {
        this.gc = gc;
    }

    /**
     * General method to draw something on the canvas.
     *
     * @param point Coordinate
     * @param fill  Color to fill with
     */
    private void paint(Point point, Color fill) {
        // paint the cell
        gc.setFill(fill);
        gc.fillRect(point.getX() * CELL_SIZE, point.getY() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    /**
     * Removes color from point in grid at coordinate point.
     *
     * @param point Coordinate
     */
    private void unpaint(Point point) {
        gc.clearRect(point.getX() * CELL_SIZE, point.getY() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    /**
     * Specific method to draw a snake.
     *
     * @param snake Snake
     */
    public void paintSnake(Snake snake) {
        Point point = snake.getBody().get(0);
        paint(point, SNAKE_COLOR);
    }

    /**
     * Calls unpaint on all points in the canvas that represent that snake.
     *
     * @param snake Snake
     */
    public void unpaintSnake(Snake snake) {
        Point point = snake.getBody().get(0);
        unpaint(point);
    }

    /**
     * Specific method to draw a piece of fruit.
     *
     * @param point Coordinate
     */
    public void paintFruit(Point point) {
        if (point == null) {
            return;
        }
        paint(point, FRUIT_COLOR);
    }

    ///**
    //* TODO: TO BE IMPLEMENTED
    //* Corrects points that are outside of the dimensions of the field.
    //*
    //* @param point Point.
    //* @return A wrapped point.
    //*/
    //private Point wrap(Point point) {
    //    return new Point(0, 0);
    //}
}

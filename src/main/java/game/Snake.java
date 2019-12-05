package game;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Snake.
 * TODO: Write a better description of this class.
 */
public class Snake {
    @Getter
    private List<Point> body = new LinkedList<>();
    @Getter
    @Setter
    private int directionX;
    @Getter
    @Setter
    private int directionY;
    @Getter
    @Setter
    private Point head;
    @Getter
    @Setter
    private Directions direction;
    @Getter
    @Setter
    private Game game;

    private static int BORDER_START = 1;
    private transient String miss = "Miss";

    /**
     * Constructor.
     *
     * @param start     Initial coordinate of the snake.
     * @param direction Initial direction of the snake.
     */
    public Snake(Point start, Directions direction) {
        this.body.add(start);
        this.changeDirection(direction);
        this.head = start;
    }

    /**
     * Changes fields directionX and directionY according to the
     * given parameter.
     * UP:      x = 0       y = -1
     * DOWN:    x = 0       y = 1
     * LEFT:    x = -1      y = 0
     * RIGHT:   x = 1       y = 0
     *
     * @param dir Enum type of direction (UP, DOWN, LEFT or RIGHT)
     */
    public final void changeDirection(Directions dir) {
        this.direction = dir;
        switch (dir) {
            case UP:
                this.directionX = 0;
                this.directionY = -1;
                break;
            case DOWN:
                this.directionX = 0;
                this.directionY = 1;
                break;
            case LEFT:
                this.directionX = -1;
                this.directionY = 0;
                break;
            case RIGHT:
                this.directionX = 1;
                this.directionY = 0;
                break;
            default:
                break;
        }
    }

    /**
     * Moves snake one square into its current direction.
     */
    public void move() {
        Point point = body.get(0);
        checkWall(point);
        point.translate(directionX, directionY);
    }

    /**
     * TODO: TO BE IMPLEMENTED.
     */
    public void grow() {
        this.body.add(new Point(1, 1));
    }

    /**
     * Method to check if a given point is a wall.
     * TODO: Extend this to properly check wall collision instead of just checking border walls
     *
     * @param location the location to check for a wall.
     */
    void checkWall(Point location) {
        switch (direction) {
            case UP:
                if (location.getY() == BORDER_START) { //Upper border
                    System.out.print("Hit upper");
                    game.stop();
                } else {
                    System.out.print(miss);
                }
                break;
            case DOWN:
                if (location.getY() + 2 == GameSettings.Y_MAX) { //Bottom border
                    System.out.print("Hit bottom");
                    game.stop();
                } else {
                    System.out.print(miss);
                }
                break;
            case LEFT:
                if (location.getX() == BORDER_START) { //Left border
                    System.out.print("Hit left");
                    game.stop();
                } else {
                    System.out.print(miss);
                }
                break;
            default:
                if (location.getX() + 2 == GameSettings.X_MAX) { //Right border
                    System.out.print("Hit right");
                    game.stop();
                } else {
                    System.out.print(miss);
                }
                break;
        }
    }
}

package game;

import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Snake.
 */
public class Snake {
    @Getter private List<Point> body = new LinkedList<>();
    @Getter @Setter private int directionX;
    @Getter @Setter private int directionY;
    @Getter @Setter private Point head;

    /**
     * Constructor.
     *
     * @param start Coordinate to initialize the snake with.
     * @param direction Direction to initialize the snake with.
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
     * @param down Enum type of direction (UP, DOWN, LEFT or RIGHT)
     */
    public final void changeDirection(Directions down) {
        switch (down) {
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
     * Moves snake one square into current direction.
     */
    public void move() {
        Point point = body.get(0);
        point.translate(directionX, directionY);
    }

    /**
     * To implement.
     */
    public void grow() {
        this.body.add(new Point(1, 1));
    }
}
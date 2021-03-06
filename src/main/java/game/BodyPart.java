package game;

import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;

/**
 * A bodypart of the snake.
 * Each bodypart has a position and a direction.
 * This class implements Tile class.
 */
public class BodyPart extends Tile {
    @Getter
    @Setter
    private int directionX;
    @Getter
    @Setter
    private int directionY;

    @Getter
    private Directions direction;

    /**
     * Default constructor of a BodyPart.
     *
     * @param x      x coordinate on the board
     * @param y      y coordinate on the board
     * @param color  color of the part
     * @param sprite sprite of the part (optional)
     */
    public BodyPart(int x, int y, Color color, String sprite) {
        super(x, y, color, sprite);
    }

    /**
     * Sets fields directionX and directionY according to the
     * given parameter.
     * UP:      x = 0       y = -1
     * DOWN:    x = 0       y = 1
     * LEFT:    x = -1      y = 0
     * RIGHT:   x = 1       y = 0
     *
     * @param dir enum type of direction (UP, DOWN, LEFT or RIGHT)
     */
    public void setDirection(Directions dir) {
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
}

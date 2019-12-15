package game;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;

public class BodyPart extends Tile {
    @Getter
    @Setter
    private Directions lastMove;
    @Getter
    @Setter
    private int directionX;
    @Getter
    @Setter
    private int directionY;

    public BodyPart(int x, int y, Color color, Image sprite) {
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
     * @param dir Enum type of direction (UP, DOWN, LEFT or RIGHT)
     */
    public void setDirection(Directions dir) {
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
        this.lastMove = dir;
    }
}

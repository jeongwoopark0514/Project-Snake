package game;

import java.util.Random;
import javafx.scene.paint.Color;


/**
 * A class that represents a wall on the board,
 * which is actually just a tile with a specific color/sprite.
 */
public class Wall extends Tile {
    /**
     * Constructor to create a Wall object.
     *
     * @param x      the x coordinate on the map
     * @param y      the y coordinate on the map
     * @param color  the color of the wall
     * @param sprite the sprite of the wall (optional)
     */
    public Wall(int x, int y, Color color, String sprite) {
        super(x, y, color, sprite);
        int random = new Random().nextInt((9)) + 1;
        if (random == 6 || random == 7) {
            random = 1;
        }
        if (random == 8 || random == 9) {
            random = 2;
        }
        String wallSprite = "image/Wall" + random + ".png";
        this.setSprite(wallSprite);
    }
}

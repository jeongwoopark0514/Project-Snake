package game;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * A class that represents a wall on the board,
 * which is actually just a tile with a specific color/sprite.
 */
public class Wall extends Tile {
    /**
     * Default constructor to create a Wall object.
     * @param x the x coordinate on the map
     * @param y the y coordinate on the map
     * @param color the color of the wall
     * @param sprite the sprite of the wall (optional)
     */
    public Wall(int x, int y, Color color, Image sprite) {
        super(x, y, color, sprite);
    }
}

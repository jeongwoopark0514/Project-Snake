package game;

import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * A tile that has an x and y value.
 * Includes some operations on points such as translate.
 */
public abstract class Tile {
    @Getter
    @Setter
    @SuppressWarnings("checkstyle:MemberName")
    private int x;
    @Getter
    @Setter
    @SuppressWarnings("checkstyle:MemberName")
    private int y;
    @Getter
    @Setter
    private Color color;
    @Getter
    @Setter
    private String sprite;

    /**
     * Default constructor for all the tile objects.
     *
     * @param x      the x coordinate of the tile
     * @param y      the y coordinate of the tile
     * @param color  the color of the tile (default fallback)
     * @param sprite the sprite of the file (optional)
     */
    Tile(int x, int y, Color color, String sprite) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.sprite = sprite;
    }

    /**
     * Changes the coordinate of this point.
     *
     * @param dx change in x
     * @param dy change in y
     */
    public void translate(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    /**
     * Check if another tile is this one.
     *
     * @param other the other tile to compare with.
     * @return true if the tiles are equals, false if not.
     */
    public boolean checkSameCoords(Tile other) {
        return this.x == other.getX() && this.y == other.getY();
    }
}

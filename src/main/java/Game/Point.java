package Game;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
/**
 * Defines a single point on the grid.
 * Each point has an x and y value
 */
public class Point {
    @Getter @Setter private int x;
    @Getter @Setter private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void translate(int dx, int dy) {
        this.x += dx;
        this.y += dy;
        // Points should be on board.
        assert x >= 0 && y >= 0;
    }
}

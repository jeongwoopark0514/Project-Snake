package game;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * A point that has an x and y value.
 * Includes some operations on points such as translate.
 */
@EqualsAndHashCode
@ToString
public class Point {
    @Getter
    @Setter
    @SuppressWarnings("checkstyle:MemberName")
    private int x;
    @Getter
    @Setter
    @SuppressWarnings("checkstyle:MemberName")
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Changes the coordinate of this point.
     * @param dx Change in x.
     * @param dy Change in y.
     */
    public void translate(int dx, int dy) {
        this.x += dx;
        this.y += dy;
        // Points should be on board.
        assert x >= 0 && y >= 0;
    }
}

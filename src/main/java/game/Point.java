package game;

import exceptions.PointOutOfWindowException;
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
     * If translated point lies outside of game window, both x and y
     * are reset to 0.
     * TODO: Current implementation only checks whether x or y are below 0.
     *
     * @param dx Change in x.
     * @param dy Change in y.
     */
    public void translate(int dx, int dy) {
        try {
            this.x += dx;
            this.y += dy;
            // Points should be on board.
            if (x < 0 || y < 0) throw new PointOutOfWindowException();
        } catch (PointOutOfWindowException e) {
            this.x = 0;
            this.y = 0;
        }
    }
}

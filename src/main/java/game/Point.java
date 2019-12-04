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
     * TODO: Current implementation only checks whether x or y are below 0.
     * Changes the coordinate of this point.
     *
     * @param dx Change in x.
     * @param dy Change in y.
     * @throws PointOutOfWindowException if x < 0 or y < 0
     */
    public void translate(int dx, int dy) throws PointOutOfWindowException {
        this.x += dx;
        this.y += dy;
        // Points should be on board.
        if (x < 0 || y < 0) {
            throw new PointOutOfWindowException();
        }
    }
}

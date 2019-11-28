package game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PointTest {
    private Point point;

    @BeforeEach
    void setup() {
        this.point = new Point(1, 1);
    }

    @Test
    void translatePositiveNumbersTest() {
        this.point.translate(1, 1);
        assertEquals(new Point(2, 2), this.point);
    }

    @Test
    void translateNegativeNumbersTest() {
        this.point.translate(-1, -1);
        assertEquals(new Point(0, 0), this.point);
    }

}
package game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PointTest {
    private transient Point point;

    @BeforeEach
    void setUp() {
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

    @Test
    void translateNegativeAndPositiveNumberTest() {
        this.point.translate(-1, 1);
        assertEquals(new Point(0, 2), this.point);
    }

    @Test
    void translatePositiveAndNegativeNumbersTest() {
        this.point.translate(1, -1);
        assertEquals(new Point(2, 0), this.point);
    }

    @Test
    void translatePointWhereResultIsNegativeTest() {
        assert
    }

}
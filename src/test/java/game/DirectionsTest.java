package game;

import static game.Directions.DOWN;
import static game.Directions.LEFT;
import static game.Directions.RIGHT;
import static game.Directions.UP;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;



public class DirectionsTest {

    @Test
    void rightOppositeTest() {
        Directions opposite = RIGHT.opposite();
        assertEquals(LEFT, opposite);
    }

    @Test
    void leftOppositeTest() {
        Directions opposite = LEFT.opposite();
        assertEquals(RIGHT, opposite);
    }

    @Test
    void upOppositeTest() {
        Directions opposite = UP.opposite();
        assertEquals(DOWN, opposite);
    }

    @Test
    void downOppositeTest() {
        Directions opposite = DOWN.opposite();
        assertEquals(UP, opposite);
    }
}

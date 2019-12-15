package game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WallTest {
    private transient Tile tile;
    private transient Tile other;

    @BeforeEach
    void setUp() {
        //The wall class is used since Tile is abstract and cannot be instantiated,
        //and the Wall class is actually the same and can be instantiated.
        tile = new Wall(1, 1, Color.AZURE, null);
        other = new Wall(1, 1, Color.AZURE, null);
    }

    @Test
    void translatePositiveNumbersTest() {
        this.tile.translate(1, 1);
        assertEquals(2, tile.getX());
        assertEquals(2, tile.getY());
    }

    @Test
    void translateNegativeNumbersTest() {
        this.tile.translate(-1, -1);
        assertEquals(0, tile.getX());
        assertEquals(0, tile.getY());
    }

    @Test
    void translateNegativeAndPositiveNumberTest() {
        this.tile.translate(-1, 1);
        assertEquals(0, tile.getX());
        assertEquals(2, tile.getY());
    }

    @Test
    void translatePositiveAndNegativeNumbersTest() {
        this.tile.translate(1, -1);
        assertEquals(2, tile.getX());
        assertEquals(0, tile.getY());
    }

    @Test
    void equalsTrueTrue() {
        assertTrue(tile.checkSameCoords(other));
    }

    @Test
    void equalsTrueFalse() {
        other.setY(0);
        assertFalse(tile.checkSameCoords(other));
    }

    @Test
    void equalsFalseTrue() {
        other.setX(0);
        assertFalse(tile.checkSameCoords(other));
    }

    @Test
    void equalsFalseFalse() {
        other.setY(0);
        other.setX(0);
        assertFalse(tile.checkSameCoords(other));
    }


}
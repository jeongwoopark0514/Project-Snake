package game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BodyPartTest {
    private BodyPart part;

    @BeforeEach
    void setUp() {
        part = new BodyPart(1, 1, Color.AZURE, null);
    }

    @ParameterizedTest
    @CsvSource(
        {"0, -1, UP", "0, 1, DOWN",
            "-1, 0, LEFT", "1, 0, RIGHT"})
    void setDirectionTest(int x, int y, Directions dir) {
        part.setDirection(dir);
        assertEquals(x, part.getDirectionX());
        assertEquals(y, part.getDirectionY());
    }
}

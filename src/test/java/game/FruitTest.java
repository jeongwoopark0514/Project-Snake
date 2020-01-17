package game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FruitTest {
    private Fruit fruit;

    @BeforeEach
    void setUp() {
        fruit = new Fruit(1, 1, Color.AZURE, null, 10);
    }

    @Test
    void constructorTest() {
        assertEquals(10, fruit.getValue());
    }
}

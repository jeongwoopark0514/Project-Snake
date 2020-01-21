package game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


class FruitTest {
    private Fruit fruit;
    private Random random;

    @BeforeEach
    void setUp() {
        fruit = new Fruit(1, 1, Color.AZURE, null, 10);
        random = Mockito.mock(Random.class);
    }

    @Test
    void constructorTest() {
        assertEquals(10, fruit.getValue());
    }

    @Test
    void randomizeTestAppleOrange() {
        Settings.setPellets("apple-orange");
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(1);
        fruit.randomize(random);
        assertEquals(50, fruit.getValue());
    }

    @Test
    void randomizeTestMellonBanana() {
        Settings.setPellets("mellon-banana");
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(3);
        fruit.randomize(random);
        assertEquals(50, fruit.getValue());
    }

    @Test
    void randomizeTestMellonBananaNotRare() {
        Settings.setPellets("mellon-banana");
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(2);
        fruit.randomize(random);
        assertEquals(10, fruit.getValue());
    }

    @Test
    void randomizeTestInvalidPelletSet() {
        Settings.setPellets("potato");
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(2);
        fruit.randomize(random);
        assertEquals(10, fruit.getValue());
    }
}

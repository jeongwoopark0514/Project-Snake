package game;

import static game.Directions.LEFT;
import static game.Directions.RIGHT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


class SnakeTest {
    private transient Snake snake;
    private transient ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private transient PrintStream originalOut = System.out;
    private transient String miss = "Miss";
    @Mock
    private transient Game game;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        System.setOut(new PrintStream(outContent));
        BodyPart start = new BodyPart(5, 5, Color.GREEN, null);
        snake = new Snake(start, LEFT);
    }

    @AfterEach
    void cleanUp() {
        System.setOut(originalOut);
    }

    @Test
    void constructorHeadSet() {
        assertTrue(new BodyPart(5, 5, Color.GREEN, null).checkSameCoords(snake.getHead()));
    }

    @Test
    void changeDirectionTest() {
        snake.changeDirection(RIGHT);
        assertEquals(1, snake.getHead().getDirectionX());
        assertEquals(0, snake.getHead().getDirectionY());
    }

    @Test
    void moveTest() {
        snake.move();
        assertEquals(4, snake.getHead().getX());
        assertEquals(5, snake.getHead().getY());
    }
}
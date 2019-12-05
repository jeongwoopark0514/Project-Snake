package game;

import static game.Directions.DOWN;
import static game.Directions.LEFT;
import static game.Directions.RIGHT;
import static game.Directions.UP;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


class SnakeTest {
    private static Snake snake;
    private transient ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private transient PrintStream originalOut = System.out;
    private transient String miss = "Miss";
    @Mock
    private transient Game game;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        System.setOut(new PrintStream(outContent));
        Point start = new Point(5, 5);
        snake = new Snake(start, LEFT);
    }

    @AfterEach
    void cleanUp() {
        System.setOut(originalOut);
    }


    @Test
    void snakeDirectionCorrectlyInitializedTest() {
        assertEquals(-1, snake.getDirectionX());
        assertEquals(0, snake.getDirectionY());
    }

    @Test
    void snakeChangeDirectionDownTest() {
        assertNotEquals(0, snake.getDirectionX());
        assertNotEquals(-1, snake.getDirectionY());
        snake.changeDirection(DOWN);
        assertEquals(0, snake.getDirectionX());
        assertEquals(1, snake.getDirectionY());
    }

    @Test
    void snakeChangeDirectionUpTest() {
        snake.changeDirection(UP);
        assertEquals(0, snake.getDirectionX());
        assertEquals(-1, snake.getDirectionY());
    }

    @Test
    void snakeChangeDirectionLeftTest() {
        snake.changeDirection(UP);
        snake.changeDirection(LEFT);
        assertEquals(-1, snake.getDirectionX());
        assertEquals(0, snake.getDirectionY());
    }

    @Test
    void snakeChangeDirectionRightTest() {
        snake.changeDirection(RIGHT);
        assertEquals(1, snake.getDirectionX());
        assertEquals(0, snake.getDirectionY());
    }

    @Test
    void snakeMoveLeftChangesPositionOfSnakeTest() {
        snake.changeDirection(LEFT);
        assertEquals(new Point(5, 5), snake.getBody().get(0));
        snake.move();
        assertEquals(new Point(4, 5), snake.getBody().get(0));
    }

    @Test
    void snakeMoveRightChangesPositionOfSnakeTest() {
        snake.changeDirection(RIGHT);
        assertEquals(new Point(5, 5), snake.getBody().get(0));
        snake.move();
        assertEquals(new Point(6, 5), snake.getBody().get(0));
    }

    @Test
    void snakeMoveUpChangesPositionOfSnakeTest() {
        snake.changeDirection(UP);
        assertEquals(new Point(5, 5), snake.getBody().get(0));
        snake.move();
        assertEquals(new Point(5, 4), snake.getBody().get(0));
    }

    @Test
    void snakeMoveDownChangesPositionOfSnakeTest() {
        snake.changeDirection(DOWN);
        assertEquals(new Point(5, 5), snake.getBody().get(0));
        snake.move();
        assertEquals(new Point(5, 6), snake.getBody().get(0));
    }

    @Test
    void growSnakeAddsOnePointToBodyTest() {
        assertEquals(1, snake.getBody().size());
        snake.grow();
        assertEquals(2, snake.getBody().size());
    }

    @Test
    void snakeMoveRightWorksWithLongerSnake() {

    }

    @Test
    void wallUpTest() {
        snake = new Snake(new Point(6, 2), DOWN);
        snake.checkWall(snake.getBody().get(0));
        assertEquals(miss, outContent.toString());
    }

    @Test
    void wallUpHitTest() {
        snake = new Snake(new Point(6, 1), UP);
        snake.setGame(game);
        snake.checkWall(snake.getBody().get(0));
        assertEquals("Hit upper", outContent.toString());
    }

    @Test
    void wallDownTest() {
        snake = new Snake(new Point(10, 17), UP);
        snake.checkWall(snake.getBody().get(0));
        assertEquals(miss, outContent.toString());
    }

    @Test
    void wallDownHitTest() {
        snake = new Snake(new Point(10, 18), DOWN);
        snake.setGame(game);
        snake.checkWall(snake.getBody().get(0));
        assertEquals("Hit bottom", outContent.toString());
    }

    @Test
    void wallLeftTest() {
        snake = new Snake(new Point(2, 10), RIGHT);
        snake.checkWall(snake.getBody().get(0));
        assertEquals(miss, outContent.toString());
    }

    @Test
    void wallLeftHitTest() {
        snake = new Snake(new Point(1, 10), LEFT);
        snake.setGame(game);
        snake.checkWall(snake.getBody().get(0));
        assertEquals("Hit left", outContent.toString());
    }

    @Test
    void wallRightTest() {
        snake = new Snake(new Point(37, 9), LEFT);
        snake.checkWall(snake.getBody().get(0));
        assertEquals(miss, outContent.toString());
    }

    @Test
    void wallRightHitTest() {
        snake = new Snake(new Point(38, 9), RIGHT);
        snake.setGame(game);
        snake.checkWall(snake.getBody().get(0));
        assertEquals("Hit right", outContent.toString());
    }
}
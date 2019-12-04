package game;

import static game.Directions.DOWN;
import static game.Directions.LEFT;
import static game.Directions.RIGHT;
import static game.Directions.UP;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SnakeTest {
    private Snake snake;

    @BeforeEach
    void setUp() {
        Point start = new Point(5, 5);
        snake = new Snake(start, LEFT);
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
    void snakeMovesOutOfScreenTest() {
        snake.changeDirection(UP);
        // move snake out of screen
        for (int i = 0; i < 100; i++) {
            snake.move();
        }

        assertEquals(new Point(0, 0), snake.getHead());
    }
}
package game;

import static game.Directions.DOWN;
import static game.Directions.LEFT;
import static game.Directions.RIGHT;
import static game.Directions.UP;

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
        BodyPart start = new BodyPart(5, 5, Color.GREEN, GameSettings.SNAKE_HEAD);
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
        assertEquals(RIGHT, snake.getDirection());
    }

    @Test
    void changeDirectionBiggerSnakeTest() {
        snake.grow();
        snake.move();
        snake.grow();
        snake.move();
        snake.grow();
        snake.move();
        snake.setDirection(LEFT);
        snake.changeDirection(UP);
        assertEquals(UP, snake.getDirection());
        snake.grow();
        snake.move();
        snake.changeDirection(LEFT);
        snake.grow();
        snake.move();
        assertEquals(LEFT, snake.getDirection());
    }

    @Test
    void changeSameDirection() {
        snake.grow();
        snake.setDirection(LEFT);
        snake.changeDirection(LEFT);
        assertEquals(LEFT, snake.getDirection());
    }

    @Test
    void changeOppositeDirection() {
        snake.grow();
        snake.setDirection(LEFT);
        snake.changeDirection(RIGHT);
        assertEquals(LEFT, snake.getDirection());
    }

    @Test
    void moveSizeOneTest() {
        snake.move();
        assertEquals(6, snake.getHead().getX());
        assertEquals(5, snake.getHead().getY());
    }

    @Test
    void moveSizeTwoTest() {
        BodyPart newPart = new BodyPart(6, 5, Color.GREEN, null);
        snake.getBody().add(newPart);
        snake.move();
        BodyPart first =  snake.getBody().get(0);
        BodyPart second =  snake.getBody().get(1);
        assertEquals(6, first.getX());
        assertEquals(5, first.getY());
        assertEquals(5, second.getX());
        assertEquals(5, second.getY());
    }

    @Test
    void growTest() {
        snake.grow();
        assertEquals(2, snake.getBody().size());
    }

    @Test
    void setSpritesHeadTest() {
        snake.setSprites();
        assertEquals(GameSettings.SNAKE_HEAD, snake.getHead().getSprite());
    }

    @Test
    void setSpritesTailTest() {
        snake.changeDirection(UP);
        snake.grow();
        assertEquals("image/green_snake_tail_DOWN.png", snake.getBody().get(1).getSprite());
    }

    @Test
    void setSpritesBodyTest() {
        snake.changeDirection(LEFT);
        snake.grow();
        snake.move();
        snake.grow();
        assertEquals(GameSettings.SNAKE_BODY, snake.getBody().get(1).getSprite());
    }

}
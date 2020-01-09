package game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.text.Text;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameTest {
    private transient Game game;
    private transient Board board;

    @BeforeEach
    void setUp() {
        Canvas canvas = mock(Canvas.class);
        Painter painter = mock(Painter.class);
        Scene scene = mock(Scene.class);
        Snake snake = mock(Snake.class);
        game = new Game(scene, painter, canvas, snake, mock(Text.class));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void instructorNotNullTest() {
        assertNotNull(game);
        assertNotNull(game.getScene());
        assertNotNull(game.getCanvas());
        assertNotNull(game.getSnake());
        assertNotNull(game.getPainter());
    }

    @Test
    void gameStartTest() throws InterruptedException {
        when(board.getTile(anyInt(), anyInt())).thenReturn(null);
        game.start();
        Thread.sleep(1000);
        assertNotNull(game.getScheduler());
    }

    @Test
    void checkFruitsSmaller() {
        List<Fruit> fruits = new ArrayList<>();
        fruits.add(new Fruit(1, 1, GameSettings.FRUIT_COLOR, null, 10));
        game.setFruits(fruits);
        game.checkFruits();
        assertEquals(fruits, game.getFruits());
    }

    @Test
    void fruitTileNotNull() {
        when(board.getTile(anyInt(), anyInt())).thenReturn(mock(Tile.class)).thenReturn(null);
        assertEquals("game.Fruit", game.createFruit().getClass().getName());
    }

    @Test
    void increaseScoreTest() {
        assertEquals(0, game.getScore());
        game.increaseScore(10);
        assertEquals(10, game.getScore());

    }

    // TODO: TO BE IMPLEMENTED
    //@Test
    //void gameStopTest() {
    //}

    // TODO: TO BE IMPLEMENTED
    //@Test
    //void gamePauseTest() {
    //}

    //@Test
    //void verifyOnKeyPressedListenersWereSet() {
    // implicitly verifies init is called in constructor
    //verify(game.getCanvas()).setOnKeyPressed(Mockito.any());
    //verify(game.getCanvas()).requestFocus(); // TODO: Interchanging these two lines causes
    // error
    //}
}
package game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.text.Text;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameTest {
    private transient Game game;
    private transient Board board;
    private transient Canvas canvas;
    private transient Painter painter;
    private transient Scene scene;
    private transient Snake snake;
    private transient AnimationTimer timer;
    private transient Text scoreText;
    private transient Text pauseText;

    @BeforeEach
    void setUp() {
        canvas = mock(Canvas.class);
        painter = mock(Painter.class);
        scene = mock(Scene.class);
        snake = mock(Snake.class);
        board = mock(Board.class);
        scoreText = mock(Text.class);
        pauseText = mock(Text.class);
        game = new Game(scene, painter, canvas, snake, scoreText, pauseText);
        timer = mock(AnimationTimer.class);
        game.setTimer(timer);
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
    void checkFruitsSmaller() {
        List<Fruit> fruits = new ArrayList<>();
        fruits.add(new Fruit(1, 1, GameSettings.FRUIT_COLOR, null, 10));
        game.setFruits(fruits);
        game.checkFruits();
        assertEquals(fruits, game.getFruits());
    }

    @Test
    void locationNewFruitNotOccupiedTest() {
        when(board.getTile(anyInt(), anyInt())).thenReturn(null);
        assertNotNull(game.createFruit());
    }

    @Test
    void locationNewFruitOccupiedTest() {
        when(board.getTile(anyInt(), anyInt())).thenReturn(mock(Fruit.class)).thenReturn(null);
        assertNotNull(game.createFruit());
    }

    @Test
    void increaseScoreTest() {
        assertEquals(0, game.getScore());
        game.increaseScore(10);
        assertEquals(10, game.getScore());
    }

    @Test
    void gamePauseChangesStateTest() {
        doNothing().when(timer).stop();
        doNothing().when(timer).start();

        assertFalse(game.isPaused());
        game.pause();
        assertTrue(game.isPaused());
        game.pause();
        assertFalse(game.isPaused());
    }
}

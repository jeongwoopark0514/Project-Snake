package game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.text.Text;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameTest {
    private Game game;
    private Board board;
    private Canvas canvas;
    private Painter painter;
    private Snake snake;
    private AnimationTimer timer;
    private List<Text> textElements;
    private Scene scene;

    @BeforeEach
    void setUp() {
        canvas = mock(Canvas.class);
        painter = mock(Painter.class);
        snake = mock(Snake.class);
        board = mock(Board.class);
        timer = mock(AnimationTimer.class);
        scene = mock(Scene.class);
        textElements = Arrays.asList(new Text(), new Text());

        game = new Game(scene, painter, canvas, snake, textElements);
        game.setTimer(timer);
    }

    @Test
    void instructorNotNullTest() {
        assertNotNull(game);
        assertNotNull(game.getCanvas());
        assertNotNull(game.getSnake());
        assertNotNull(game.getPainter());
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
        doNothing().when(canvas).requestFocus();

        assertFalse(game.isPaused());
        game.pause();
        assertTrue(game.isPaused());
        game.pause();
        assertFalse(game.isPaused());
    }
}

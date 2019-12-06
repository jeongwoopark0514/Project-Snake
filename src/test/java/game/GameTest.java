package game;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class GameTest {
    private transient Game game;

    @BeforeEach
    void setUp() {
        Canvas canvas = mock(Canvas.class);

        Painter painter = mock(Painter.class);

        Scene scene = mock(Scene.class);

        Snake snake = mock(Snake.class);

        game = new Game(scene, painter, canvas, snake);
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
    void gameStartTest() {
        game.start();
        assertNotNull(game.getScheduler());
        verify(game.getPainter()).unpaintSnake(game.getSnake());
        verify(game.getPainter()).paintSnake(game.getSnake());
        verify(game.getSnake()).move();
    }

    // TODO: TO BE IMPLEMENTED
    //@Test
    //void gameStopTest() {
    //}

    // TODO: TO BE IMPLEMENTED
    //@Test
    //void gamePauzeTest() {
    //}

    @Test
    void verifyOnKeyPressedListenersWereSet() {
        // implicitly verifies init is called in constructor
        verify(game.getCanvas()).requestFocus(); // TODO: Interchanging these two lines causes error
        verify(game.getCanvas()).setOnKeyPressed(Mockito.any());
    }
}
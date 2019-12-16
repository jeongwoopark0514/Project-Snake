package game;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void gameStartTest() throws InterruptedException {
        game.start();
        Thread.sleep(1000);
        assertNotNull(game.getScheduler());
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
package game;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CollisionManagerTest {
    private CollisionManager collisionManager;
    private Board board;
    private Game game;

    @BeforeEach
    void setUp() {
        board = mock(Board.class);
        Snake snake = mock(Snake.class);
        when(snake.getHead()).thenReturn(mock(BodyPart.class));
        game = mock(Game.class);
        when(game.getPainter()).thenReturn(mock(Painter.class));
        collisionManager = new CollisionManager(board, snake, game);
    }

    @Test
    void checkEmptyTileTest() throws UnsupportedAudioFileException, IOException,
        LineUnavailableException {
        when(board.getTile(anyInt(), anyInt())).thenReturn(null);
        assertFalse(collisionManager.check());
    }

    @Test
    void checkFruitTileTest() throws UnsupportedAudioFileException, IOException,
        LineUnavailableException {
        Board board2 = mock(Board.class);
        Snake snake2 = mock(Snake.class);
        when(snake2.getHead()).thenReturn(mock(BodyPart.class));
        Game game2 = mock(Game.class);
        when(game2.getPainter()).thenReturn(mock(Painter.class));
        CollisionManager fruitManager = new CollisionManager(board2, snake2, game2);
        Sound sound = mock(Sound.class);
        fruitManager.setSound(sound);
        doNothing().when(sound).play();
        when(board2.getTile(anyInt(), anyInt())).thenReturn(mock(Fruit.class));
        when(game2.createFruit()).thenReturn(mock(Fruit.class));
        assertTrue(fruitManager.check());
    }

    @Test
    void checkOtherTileTest() throws UnsupportedAudioFileException, IOException,
        LineUnavailableException {
        when(board.getTile(anyInt(), anyInt())).thenReturn(mock(Wall.class));
        verify(game, times(0)).createFruit();
        assertTrue(collisionManager.check());
    }
}

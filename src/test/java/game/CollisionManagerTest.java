package game;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
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
    private PieceCreator creator;

    @BeforeEach
    void setUp() {
        creator = mock(PieceCreator.class);
        board = mock(Board.class);
        Snake snake = mock(Snake.class);
        when(snake.getHead()).thenReturn(mock(BodyPart.class));
        game = mock(Game.class);
        when(game.getPainter()).thenReturn(mock(Painter.class));
        collisionManager = new CollisionManager(board, snake, game);
        collisionManager.setCreator(creator);
    }

    @Test
    void checkEmptyTileTest() {
        when(board.getTile(anyInt(), anyInt())).thenReturn(null);
        assertFalse(collisionManager.check());
    }

    @Test
    void checkFruitTileTest() throws UnsupportedAudioFileException, IOException,
        LineUnavailableException {
        when(board.getTile(anyInt(), anyInt())).thenReturn(mock(Fruit.class));
        Sound sound = mock(Sound.class);
        collisionManager.setSound(sound);
        doNothing().when(sound).play();
        doReturn(mock(Fruit.class)).when(creator).createFruit(any());
        assertTrue(collisionManager.check());
    }

    @Test
    void soundErrorTest() throws UnsupportedAudioFileException, IOException,
        LineUnavailableException {
        when(board.getTile(anyInt(), anyInt())).thenReturn(mock(Fruit.class));
        Sound sound = mock(Sound.class);
        collisionManager.setSound(sound);
        doThrow(IOException.class).when(sound).play();
        doReturn(mock(Fruit.class)).when(creator).createFruit(any());
        assertTrue(collisionManager.check());
    }

    @Test
    void checkOtherTileTest() {
        when(board.getTile(anyInt(), anyInt())).thenReturn(mock(Wall.class));
        assertTrue(collisionManager.check());
    }
}

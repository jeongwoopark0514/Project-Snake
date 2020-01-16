package game;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CollisionManagerTest {
    private transient CollisionManager collisionManager;
    private transient Board board;
    private transient Game game;

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
    void checkEmptyTileTest() {
        when(board.getTile(anyInt(), anyInt())).thenReturn(null);
        assertFalse(collisionManager.check());
    }

    @Test
    void checkFruitTileTest() {
        when(board.getTile(anyInt(), anyInt())).thenReturn(mock(Fruit.class));
        when(game.createFruit()).thenReturn(mock(Fruit.class));
        assertTrue(collisionManager.check());
    }

    @Test
    void checkOtherTileTest() {
        when(board.getTile(anyInt(), anyInt())).thenReturn(mock(Wall.class));
        verify(game, times(0)).createFruit();
        assertTrue(collisionManager.check());
    }
}

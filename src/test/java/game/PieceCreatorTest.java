package game;

import static game.GameSettings.X_MAX;
import static game.GameSettings.Y_MAX;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Random;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PieceCreatorTest {
    private PieceCreator creator;
    private Board board;
    private Random random;

    @BeforeEach
    void setUp() {
        random = new Random(2);
        board = mock(Board.class);
        creator = new PieceCreator();
    }

    @AfterEach
    void breakDown() {
        Settings.setGameMode(0);
    }

    @Test
    void createWallsTest() {
        assertEquals(Y_MAX * 2 + X_MAX * 2, creator.createWalls().size());
    }

    @Test
    void adJustForDifficultyTestMedium() {
        ArrayList<Wall> walls = new ArrayList<>();
        Settings.setGameMode(1);
        creator.adjustForDifficulty(walls);
        assertEquals(20, walls.size());
    }

    @Test
    void adJustForDifficultyTestHard() {
        ArrayList<Wall> walls = new ArrayList<>();
        Settings.setGameMode(2);
        creator.adjustForDifficulty(walls);
        assertEquals(60, walls.size());
    }

    @Test
    void locationNewFruitNotOccupiedTest() {
        when(board.getTile(anyInt(), anyInt())).thenReturn(null);
        Random random = new Random(2);
        int nextX = random.nextInt((X_MAX - 2) - 1) + 1;
        int nextY = random.nextInt((Y_MAX - 2) - 1) + 1;
        creator.setRandom(new Random(2));
        Fruit fruit = creator.createFruit(board);
        assertEquals(nextX, fruit.getX());
        assertEquals(nextY, fruit.getY());
    }

    @Test
    void locationNewFruitOccupiedTest() {
        when(board.getTile(anyInt(), anyInt())).thenReturn(mock(Fruit.class)).thenReturn(null);
        Random random = new Random(3);
        random.nextInt((X_MAX - 2) - 1);
        random.nextInt((Y_MAX - 2) - 1);
        int nextX = random.nextInt((X_MAX - 2) - 1) + 1;
        int nextY = random.nextInt((Y_MAX - 2) - 1) + 1;
        creator.setRandom(new Random(3));
        Fruit fruit = creator.createFruit(board);
        assertEquals(nextX, fruit.getX());
        assertEquals(nextY, fruit.getY());
    }

}
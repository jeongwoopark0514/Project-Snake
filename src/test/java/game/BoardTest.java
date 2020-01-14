package game;

import static game.GameSettings.SNAKE_HEAD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BoardTest {
    private transient Board board;

    @BeforeEach
    void setUp() {
        // create some elements
        BodyPart bodypart = new BodyPart(1, 1, Color.GREEN, SNAKE_HEAD);
        Fruit fruit = new Fruit(2, 2, Color.YELLOW, null, 10);
        Wall wall = new Wall(3, 3, Color.BROWN, null);

        List<Tile> elements = new ArrayList<>(Arrays.asList(bodypart, fruit, wall));

        board = new BoardBuilder()
            .withElements(elements)
            .withBackground("image/background.png")
            .withDimensions(5, 5)
            .build();
    }

    @Test
    void heightTest() {
        assertEquals(5, board.getHeight());
    }

    @Test
    void widthTest() {
        assertEquals(5, board.getWidth());
    }

    @Test
    void getTileTest() {
        assertNull(board.getTile(0, 0));
    }

    @Test
    void getTileOutTest() {
        assertThrows(IndexOutOfBoundsException.class, () -> board.getTile(5, 5));
    }

    @Test
    void updateTileTest() {
        Tile tile = mock(Tile.class);
        board.updateTile(0, 0, tile);
        assertEquals(tile, board.getTile(0, 0));
    }

    @Test
    void updateTileOutTest() {
        assertThrows(IndexOutOfBoundsException.class, () ->
            board.updateTile(5, 5, mock(Tile.class)));
    }

    @ParameterizedTest
    @CsvSource(
        {"0, 4, true", "-1, 4, false", "5, 4, false",
            "4, 0, true", "4, -1, false", "4, 5, false"})
    void onBoardTest(int x, int y, boolean result) {
        assertEquals(result, board.onBoard(x, y));
    }
}

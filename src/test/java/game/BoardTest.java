//package game;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.mock;
//
//import java.util.ArrayList;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvSource;
//
//class BoardTest {
//    private transient Board board;
//
//    @BeforeEach
//    void setUp() {
//        ArrayList<ArrayList<Tile>> grid = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            ArrayList<Tile> row = new ArrayList<>();
//            for (int j = 0; j < 5; j++) {
//                row.add(null);
//            }
//            grid.add(row);
//        }
//        board = new Board(grid, "background.png");
//    }
//
//    @Test
//    void gridNull() {
//        assertThrows(NullPointerException.class, () -> new Board(null, "background.png"));
//    }
//
//    @Test
//    void heightTest() {
//        assertEquals(5, board.getHeight());
//    }
//
//    @Test
//    void widthTest() {
//        assertEquals(5, board.getWidth());
//    }
//
//    @Test
//    void getTileTest() {
//        assertNull(board.getTile(0, 0));
//    }
//
//    @Test
//    void getTileOutTest() {
//        assertThrows(IndexOutOfBoundsException.class, () -> board.getTile(5, 5));
//    }
//
//    @Test
//    void updateTileTest() {
//        Tile tile = mock(Tile.class);
//        board.updateTile(0, 0, tile);
//        assertEquals(tile, board.getTile(0, 0));
//    }
//
//    @Test
//    void updateTileOutTest() {
//        assertThrows(IndexOutOfBoundsException.class, () ->
//            board.updateTile(5, 5, mock(Tile.class)));
//    }
//
//    @ParameterizedTest
//    @CsvSource(
//        {"0, 4, true", "-1, 4, false", "5, 4, false",
//            "4, 0, true", "4, -1, false", "4, 5, false"})
//    void onBoardTest(int x, int y, boolean result) {
//        assertEquals(result, board.onBoard(x, y));
//    }
//}

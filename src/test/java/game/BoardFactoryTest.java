//package game;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.mockito.Mockito.mock;
//
//import java.util.ArrayList;
//import java.util.List;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//class BoardFactoryTest {
//    private transient BoardFactory factory;
//
//    @BeforeEach
//    void setUp() {
//        factory = new BoardFactory("background.png");
//    }
//
//    @Test
//    void createBoardToSmallTest() {
//        assertNull(factory.createBoard(0, 0));
//        assertNull(factory.createBoard(3, 0));
//        assertNull(factory.createBoard(0, 3));
//    }
//
//    @Test
//    void createBoardTest() {
//        Wall wall = new Wall(0, 0, GameSettings.WALL_COLOR, null);
//        Wall wall2 = new Wall(0, 0, GameSettings.WALL_COLOR, null);
//        factory.addTile(wall);
//        factory.addTile(wall2);
//        Board board = factory.createBoard(3, 3);
//        assertEquals(3, board.getWidth());
//        assertEquals(3, board.getHeight());
//    }
//
//
//    @Test
//    void addTilesTest() {
//        List<Tile> list = new ArrayList<>();
//        list.add(mock(Tile.class));
//        factory.addTiles(list);
//        assertEquals(1, factory.getElements().size());
//    }
//
//    @Test
//    void addTilesNoTileTest() {
//        List<Object> list = new ArrayList<>();
//        list.add(new Object());
//        factory.addTiles(list);
//        assertEquals(0, factory.getElements().size());
//    }
//
//    @Test
//    void addTileTest() {
//        Tile tile = mock(Tile.class);
//        factory.addTile(tile);
//        assertEquals(1, factory.getElements().size());
//    }
//}
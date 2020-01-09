package game;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

/**
 * A factory to create instances of the board class.
 * Given the supplied elements for the board and the parameters,
 * this factory will construct a board that can be used to implement proper collision
 * and multiple levels.
 */
public class BoardFactory {

    private transient String background;
    @Getter
    private transient List<Tile> elements;

    /**
     * Constructor for the BoardFactory.
     *
     * @param background the background that the level needs to have.
     */
    public BoardFactory(String background) {
        this.background = background;
        this.elements = new ArrayList<>();
    }

//    /**
//     * Create a board, given the parameters supplied.
//     * This factory method will create a new instance of the Board class.
//     *
//     * @param width  the width of the board.
//     * @param height the height of the board.
//     * @return the created board with all elements added or null.
//     */
//    Board createBoard(int width, int height) {
//        if (width < 3 || height < 3) {
//            return null;
//        }
//
//        ArrayList<ArrayList<Tile>> grid = new ArrayList<>(height);
//        for (int i = 0; i < height; i++) {
//            ArrayList<Tile> row = new ArrayList<>();
//            for (int j = 0; j < width; j++) {
//                row.add(null);
//            }
//            grid.add(row);
//        }
//
//        for (Tile tile : elements) {
//            int x = tile.getX();
//            int y = tile.getY();
//            if (grid.get(y).get(x) == null) {
//                grid.get(y).set(x, tile);
//            }
//        }
//        return new Board(grid, background);
//    }

    /**
     * Add a list of tiles to the factory, which will be added ot the board.
     * This can be any type of Tile.
     *
     * @param tiles the list of tiles that should be added.
     */
    void addTiles(List tiles) {
        for (Object tile : tiles) {
            if (tile instanceof Tile) {
                elements.add((Tile) tile);
            }
        }
    }

    /**
     * Add just a single Tile to the board.
     *
     * @param tile the object of type tile to add.
     */
    void addTile(Tile tile) {
        elements.add(tile);
    }
}

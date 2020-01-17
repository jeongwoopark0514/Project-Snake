package game;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

/**
 * Board representing the back structure of the game.
 * The board is used to paint all the textures,
 * add game elements like walls, pellets and the snake,
 * and is used for collision.
 * Each entry on the board can be retrieved
 * and updated to stay up to date with the dynamics of the game.
 */
class Board {
    private ArrayList<ArrayList<Tile>> grid;
    @Getter
    private int width;
    @Getter
    private int height;
    @Getter
    private String background;
    @Getter
    private List<Tile> elements;

    /**
     * Constructor for the board class, this will create an instance of a board.
     *
     * @param width         number of cells on x-axis
     * @param height        number of cells on y-axis
     * @param background    the background this board gets.
     * @param elements      list of Wall, Fruit and Bodypart objects belonging to this board
     */
    public Board(int width, int height, String background, List<Tile> elements) {
        this.width = width;
        this.height = height;
        this.background = background;
        this.elements = elements;
        createBoard();
    }

    /**
     * Get a tile from the board.
     *
     * @param x the x coordinate of the tile to get.
     * @param y the y coordinate of the tile to get.
     * @return the tile retrieved from the location on the board.
     */
    public Tile getTile(int x, int y) {
        if (!onBoard(x, y)) {
            throw new IndexOutOfBoundsException();
        }
        return grid.get(y).get(x);
    }

    /**
     * Update a given tile, this gives the ability to change the type of Tile.
     *
     * @param x    the x coordinate of the tile.
     * @param y    the y coordinate of the tile.
     * @param tile the tile to change the tile on the board to.
     */
    public void updateTile(int x, int y, Tile tile) {
        if (!onBoard(x, y)) {
            throw new IndexOutOfBoundsException();
        }
        grid.get(y).set(x, tile);
    }

    /**
     * Check if a tile is actually on the board.
     *
     * @param x the x coordinate of the location to check.
     * @param y the y coordinate of the location to check.
     * @return true if the location is within the board and false if not.
     */
    boolean onBoard(int x, int y) {
        return x >= 0 && x < getWidth() && y >= 0 && y < getHeight();
    }

    /**
     * Create a board, given the parameters supplied.
     * This factory method will create a new instance of the Board class.
     */
    private void createBoard() {
        if (width < 3 || height < 3) {
            return;
        }

        grid = new ArrayList<>(height);
        for (int i = 0; i < height; i++) {
            ArrayList<Tile> row = new ArrayList<>();
            for (int j = 0; j < width; j++) {
                row.add(null);
            }
            grid.add(row);
        }

        for (Tile tile : elements) {
            if (tile == null) {
                continue;
            }
            int x = tile.getX();
            int y = tile.getY();
            if (grid.get(y).get(x) == null) {
                grid.get(y).set(x, tile);
            }
        }
    }
}

package game;

import java.util.ArrayList;
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
    private transient ArrayList<ArrayList<Tile>> grid;
    @Getter
    private transient String background;

    /**
     * Constructor for the board class, this will create an instance of a board.
     *
     * @param grid       the grid which actually represents the board.
     * @param background the background this board gets.
     */
    Board(ArrayList<ArrayList<Tile>> grid, String background) {
        assert grid != null;
        this.grid = grid;
        this.background = background;
    }

    /**
     * Get the height of the board.
     *
     * @return the height.
     */
    int getHeight() {
        return grid.size();
    }

    /**
     * Get the width of the board.
     *
     * @return the width.
     */
    int getWidth() {
        return grid.get(0).size();
    }

    /**
     * Get a tile from the board.
     *
     * @param x the x coordinate of the tile to get.
     * @param y the y coordinate of the tile to get.
     * @return the tile retrieved from the location on the board.
     */
    Tile getTile(int x, int y) {
        assert onBoard(x, y);
        return grid.get(y).get(x);
    }

    /**
     * Update a given tile, this gives the ability to change the type of Tile.
     *
     * @param x    the x coordinate of the tile.
     * @param y    the y coordinate of the tile.
     * @param tile the tile to change the tile on the board to.
     */
    void updateTile(int x, int y, Tile tile) {
        assert onBoard(x, y);
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
}

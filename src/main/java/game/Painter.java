package game;

import static game.GameSettings.CELL_SIZE;

import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * Used for painting shapes on a canvas, which usually represents the game screen.
 */
public class Painter {
    @Getter
    @Setter
    private GraphicsContext gc;

    /**
     * Constructor.
     *
     * @param gc GraphicalContext for this Painter object.
     */
    public Painter(@NonNull GraphicsContext gc) {
        this.gc = gc;
    }

    /**
     * General method to draw a Tile on the canvas.
     *
     * @param tile Coordinate
     */
    void paint(@NonNull Tile tile) {
        // paint the cell
        if (tile.getSprite() == null) {
            gc.setFill(tile.getColor());
            gc.fillRect(tile.getX() * CELL_SIZE, tile.getY() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        } else {
            Image sprite = new Image(tile.getSprite());
            double height = sprite.getHeight();
            double width = sprite.getWidth();
            int positionX = tile.getX() * CELL_SIZE;
            int positionY = tile.getY() * CELL_SIZE;
            gc.drawImage(sprite, 0, 0, width, height, positionX, positionY, CELL_SIZE, CELL_SIZE);
        }
    }

    /**
     * Paint multiple tiles on the board.
     *
     * @param tiles the list of tiles to paint.
     */
    void paint(@NonNull List tiles) {
        for (Object tile : tiles) {
            assert tile instanceof Tile;
            paint((Tile) tile);
        }
    }

    /**
     * Paint the initial board, so all standard game elements will be drawn.
     *
     * @param board the board to paint all the elements from.
     */
    void paintBoard(Board board) {
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                Tile tile = board.getTile(j, i);
                if (tile == null) {
                    continue;
                }
                paint(tile);
            }
        }
    }

    /**
     * Removes the sprite/color from a Tile on the board.
     *
     * @param tile Coordinate
     */
    void unPaint(@NonNull Tile tile) {
        gc.clearRect(tile.getX() * CELL_SIZE, tile.getY() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    /**
     * Remove the sprite/color from all the tiles in a list of tiles.
     *
     * @param tiles the list of tiles to clear.
     */
    void unPaint(@NonNull List tiles) {
        for (Object tile : tiles) {
            assert tile instanceof Tile;
            unPaint((Tile) tile);
        }
    }

    ///**
    //* TODO: TO BE IMPLEMENTED
    //* Corrects points that are outside of the dimensions of the field.
    //*
    //* @param point Point.
    //* @return A wrapped point.
    //*/
    //private Point wrap(Point point) {
    //    return new Point(0, 0);
    //}
}

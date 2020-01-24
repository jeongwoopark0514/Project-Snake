package game;

import java.util.List;

/**
 * Used to set some attributes on an instance of Board, namely: width and height (dimensions),
 * background image and the elements (Fruit, BodyPart and Wall) belonging to this board.
 * Implements the Builder pattern.
 */
public class BoardBuilder {
    private int width;
    private int height;
    private String background;
    private List<Tile> elements;

    /**
     * Sets the dimensions of the board (width and height).
     *
     * @param width  number of cells on x-axis
     * @param height number of cells on y-axis
     * @return BoardBuilder object used for further construction
     */
    public BoardBuilder withDimensions(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * Sets the background image for the board.
     *
     * @param background path to background image
     * @return BoardBuilder object used for further construction
     */
    public BoardBuilder withBackground(String background) {
        this.background = background;
        return this;
    }

    /**
     * Sets the elements (Fruit, BodyPart and Wall) belonging to the board.
     *
     * @param elements list of Wall, Fruit and Bodypart objects belonging to this board
     * @return BoardBuilder object used for further construction
     */
    public BoardBuilder withElements(List<Tile> elements) {
        this.elements = elements;
        return this;
    }

    /**
     * Constructs a Board object with set attributes.
     *
     * @return Board the board object
     */
    public Board build() {
        return new Board(width, height, background, elements);
    }
}

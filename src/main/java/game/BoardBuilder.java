package game;

import java.util.List;

public class BoardBuilder {
    private int width;
    private int height;
    private transient String background;
    private transient List<Tile> elements;

    public BoardBuilder() {
    }

    public BoardBuilder withDimensions(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public BoardBuilder withBackground(String background) {
        this.background = background;
        return this;
    }

    public BoardBuilder withElements(List<Tile> elements) {
        this.elements = elements;
        return this;
    }

    public Board build() {
        return new Board(width, height, background, elements);
    }
}

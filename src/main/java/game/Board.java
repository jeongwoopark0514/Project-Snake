package game;

import java.util.ArrayList;
import lombok.Getter;

public class Board {
    public final ArrayList<ArrayList<Tile>> board;
    @Getter
    private transient String background;

    Board(ArrayList<ArrayList<Tile>> grid, String background) {
        assert grid != null;
        this.board = grid;
        this.background = background;
    }

    public int getWidth() {
        return board.size();
    }

    public int getHeight() {
        return board.get(0).size();
    }

    public Tile tileAt(int x, int y) {
        assert onBoard(x, y);
        Tile tile = board.get(x).get(y);
        return tile;
    }

    public boolean onBoard(int x, int y) {
        return x >= 0 && x < getWidth() && y >= 0 && y < getHeight();
    }
}

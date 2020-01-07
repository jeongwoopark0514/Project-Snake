package game;

import java.util.ArrayList;
import java.util.List;
public class BoardFactory {

    private String background;
    private List<Wall> walls;
    private Snake snake;
    private Fruit fruit;

    BoardFactory(String background) {
        this.background = background;
    }

    public Board createBoard(int x, int y) {
        assert x > 3 && y > 3;

        ArrayList<ArrayList<Tile>> grid = new ArrayList<>(y);
        for (int i = 0; i < y; i++) {
            ArrayList<Tile> row = new ArrayList<>();
            for(int j = 0; j < x; j++) {
                row.add(null);
            }
            grid.add(row);
        }

        assert walls != null;

        for (Tile wall : walls) {
            grid.get(wall.getY()).set(wall.getX(), wall);
        }

        assert fruit != null;
        assert grid.get(fruit.getY()).get(fruit.getX()) == null;
        grid.get(fruit.getY()).set(fruit.getX(), fruit);

        assert snake != null;
        assert grid.get(snake.getHead().getY()).get(snake.getHead().getX()) == null;
        grid.get(snake.getHead().getY()).set(snake.getHead().getX(), snake.getHead());

        return new Board(grid, background);
    }

    public void addWalls(List<Wall> walls) {
        this.walls = walls;
    }

    public void addSnake(Snake snake) {
        this.snake = snake;
    }

    public void addFruit(Fruit fruit) {
        this.fruit = fruit;
    }

}

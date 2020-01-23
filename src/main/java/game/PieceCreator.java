package game;

import static game.GameSettings.X_MAX;
import static game.GameSettings.Y_MAX;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.Setter;

/**
 * A class that creates the pieces which are neccessary for the game.
 * These pieces will be placed on the board.
 */
public class PieceCreator {
    @Setter
    private Random random = new Random();

    /**
     * Method that generates the walls that will be put onto the board.
     *
     * @return the list of generated walls
     */
    public ArrayList<Wall> createWalls() {
        ArrayList<Wall> walls = new ArrayList<>();
        for (int i = 0; i < GameSettings.Y_MAX; i++) {
            walls.add(new Wall(0, i, GameSettings.WALL_COLOR, null));
        }
        for (int i = 0; i < GameSettings.X_MAX; i++) {
            walls.add(new Wall(i, 0, GameSettings.WALL_COLOR, null));
        }
        for (int i = 0; i < GameSettings.Y_MAX; i++) {
            walls.add(new Wall(GameSettings.X_MAX - 1, i, GameSettings.WALL_COLOR, null));
        }
        for (int i = 0; i < GameSettings.X_MAX; i++) {
            walls.add(new Wall(i, GameSettings.Y_MAX - 1, GameSettings.WALL_COLOR, null));
        }

        adjustForDifficulty(walls);
        return walls;
    }

    /**
     * Add extra walls according to the set difficulty.
     *
     * @param walls the list of walls to add more walls to
     */
    public void adjustForDifficulty(List<Wall> walls) {
        int mode = Settings.getGameMode();
        int numOfExtra;
        switch (mode) {
            case 1:
                numOfExtra = 1;
                break;
            case 2:
                numOfExtra = 3;
                break;
            default:
                numOfExtra = 0;
                break;
        }

        for (int i = 0; i < 20 * numOfExtra; i++) {
            int random1 = new Random().nextInt((X_MAX));
            int random2 = new Random().nextInt((Y_MAX));

            walls.add(new Wall(random1, random2, GameSettings.WALL_COLOR, null));
        }
    }

    /**
     * Create a piece of fruit to be put onto the map.
     * The method makes sure that the place the fruits spawns is actually empty.
     */
    public Fruit createFruit(Board board) {
        int x = random.nextInt((X_MAX - 2) - 1) + 1;
        int y = random.nextInt((Y_MAX - 2) - 1) + 1;
        Tile tile = board.getTile(x, y);
        if (tile != null) {
            return createFruit(board);
        } else {
            Fruit fruit = new Fruit(x, y, GameSettings.FRUIT_COLOR, null, 10);
            fruit.randomize(new Random());
            return fruit;
        }
    }
}

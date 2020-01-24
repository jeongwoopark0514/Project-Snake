package game;

import java.util.Random;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;


/**
 * Fruit class representing the pellets on the map,
 * that can be eaten by the snake.
 */
public class Fruit extends Tile {

    @Getter
    @Setter
    private int value;

    /**
     * Default constructor for the Fruit items on the map.
     *
     * @param x      the x coordinate on the map.
     * @param y      the y coordinate on the map.
     * @param color  the color of the fruit.
     * @param sprite the sprite of the fruit (optional).
     * @param value  the value of this fruit tile.
     */
    public Fruit(int x, int y, Color color, String sprite, int value) {
        super(x, y, color, sprite);
        this.value = value;
    }

    /**
     * This method randomizes the pellet that is spawned.
     * There is a 1/10 chance that a pellet worth 50 points
     * instead of 10 spawns.
     *
     * @param random the Random generator that is used to
     *               check if such a pellet should be spawned.
     */
    public void randomize(Random random) {
        int rand = random.nextInt(10) + 1;

        if (Settings.getPellets().equals("apple-orange")) {
            int appleOrange = 2;
            if (rand != appleOrange) {
                rand = 1;
            } else {
                this.value = 50;
            }

        } else if (Settings.getPellets().equals("melon-banana")) {
            int melonBanana = 4;
            if (rand != melonBanana) {
                rand = 3;
            } else {
                this.value = 50;
            }
        }
        String pelletSprite = "image/Pellet" + rand + ".png";
        this.setSprite(pelletSprite);
    }
}

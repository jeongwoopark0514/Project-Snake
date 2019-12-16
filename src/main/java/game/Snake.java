package game;

import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * An object that represents the Hero of the game, Snake.
 * Snake has a direction in which it moves, and a method that moves the snake one square
 * in its current direction.
 */
public class Snake {
    @Getter
    private List<Tile> body = new LinkedList<>();
    @Getter
    @Setter
    private BodyPart head;
    @Getter
    @Setter
    private Directions direction;
    @Getter
    @Setter
    private Game game;

    /**
     * Constructor.
     *
     * @param start     Initial coordinate of the snake.
     * @param direction Initial direction of the snake.
     */
    public Snake(BodyPart start, Directions direction) {
        this.body.add(start);
        this.head = start;
        head.setLastMove(direction);
        head.setDirection(direction);
    }

    /**
     * Changes the direction of the snake by setting the direction it's head moves to.
     *
     * @param dir Enum type of direction (UP, DOWN, LEFT or RIGHT)
     */
    public final void changeDirection(Directions dir) {
        head.setDirection(dir);
    }

    /**
     * Moves snake one square into its current direction.
     */
    public void move() {
        //This should be changed when the snake gets proper grow methods,
        //body part n should move into the direction part n - 1 moved in the last move.
        //So if the head first goes up and now goes left, the part directly after the head
        //should move up. (The BodyPart's "lastMove" property can be used to implement this.

        for (Tile tile : body) {
            BodyPart part = (BodyPart) tile;
            part.translate(part.getDirectionX(), part.getDirectionY());
        }
    }


    ///**
    // * TODO: TO BE IMPLEMENTED.
    // */
    //public void grow() {
    //    this.body.add(new Point(1, 1));
    //}
}

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
    private transient int minSize = 1; //minimal size the snake has to be to have a body

    /**
     * Constructor.
     *
     * @param start     Initial coordinate of the snake.
     * @param direction Initial direction of the snake.
     */
    public Snake(BodyPart start, Directions direction) {
        this.body.add(start);
        this.head = start;
        head.setDirection(direction);
    }

    /**
     * Set the sprite of the different parts of the snake.
     * TODO: account for rotation and direction.
     */
    public void setSprites() {
        body.get(0).setSprite(GameSettings.SNAKE_HEAD);
        if (body.size() > minSize) {
            for (int i = 1; i < body.size() - 1; i++) {
                body.get(i).setSprite(GameSettings.SNAKE_BODY);
            }
            body.get(body.size() - 1).setSprite(GameSettings.SNAKE_TAIL);
        }
    }

    /**
     * Changes the direction of the snake by setting the direction its head moves to.
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
        for (int i = body.size() - 1; i > 0; i--) {
            BodyPart part = (BodyPart) body.get(i);
            BodyPart previous = (BodyPart) body.get(i - 1);
            part.setX(previous.getX());
            part.setY(previous.getY());
        }
        head.translate(head.getDirectionX(), head.getDirectionY());
    }

    /**
     * Grow the snake when it eats a pellet.
     */
    public void grow() {
        BodyPart curTail = (BodyPart) body.get(body.size() - 1);
        BodyPart newTail = new BodyPart(curTail.getX(), curTail.getY(),
            GameSettings.SNAKE_COLOR, null);
        body.add(newTail);
        setSprites();
    }
}

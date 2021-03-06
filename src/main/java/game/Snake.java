package game;

import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * An object that represents the Hero of the game, Snake.
 * Snake has a direction in which it moves, and a method that moves the snake one square
 * in its current direction.
 */
public class Snake {
    @Getter
    private List<BodyPart> body = new LinkedList<>();
    @Getter
    @Setter
    private BodyPart head;
    @Getter
    private BodyPart tail;
    @Getter
    @Setter
    private Directions direction;
    @Getter
    @Setter
    private Game game;
    private int minSize = 1; //minimal size the snake has to be to have a body

    /**
     * Constructor.
     *
     * @param start     Initial coordinate of the snake.
     * @param direction Initial direction of the snake.
     */
    public Snake(BodyPart start, Directions direction) {
        this.tail = start;
        this.body.add(start);
        this.head = start;
        head.setDirection(direction);
        tail.setDirection(direction.opposite());
    }

    /**
     * Sets the sprite of the different parts of the snake.
     */
    public void setSprites() {
        if (direction == null) {
            head.setSprite("/image/" + Settings.getSnakeColor() + "_snake_head_UP.png");
            return;
        }
        String newSpriteHead = "image/" + Settings.getSnakeColor() + "_snake_head_"
            + direction + ".png";
        head.setSprite(newSpriteHead);
        if (body.size() > minSize) {
            for (int i = 1; i <= body.size() - 1; i++) {
                body.get(i).setSprite("image/" + Settings.getSnakeColor() + "_snake_body.png");
            }
        }
    }

    /**
     * Changes the direction of the snake by setting the direction its head moves to.
     *
     * @param dir Enum type of direction (UP, DOWN, LEFT or RIGHT).
     * @throws NullPointerException if dir is null
     */
    public void changeDirection(@NonNull Directions dir) throws NullPointerException {
        if (this.body.size() <= minSize) {
            setDirectionTo(dir);
        } else if (dir != this.direction.opposite() && dir != this.direction) {
            setDirectionTo(dir);
        }
    }

    /**
     * Set direction of head and direction to the certain direction.
     *
     * @param dir direction that the snake proceeds.
     */
    public void setDirectionTo(Directions dir) {
        this.head.setDirection(dir);
        this.setDirection(dir);
    }

    /**
     * Moves snake one square into its current direction.
     */
    public void move() {
        for (int i = body.size() - 1; i > 0; i--) {
            BodyPart part = body.get(i);
            BodyPart previous = body.get(i - 1);
            part.setX(previous.getX());
            part.setY(previous.getY());
            part.setDirection(previous.getDirection());
        }
        head.translate(head.getDirectionX(), head.getDirectionY());
        setSprites();
    }

    /**
     * Adds one bodypart to the snake body.
     */
    public void grow() {
        BodyPart newTail = new BodyPart(tail.getX(), tail.getY(),
            GameSettings.SNAKE_COLOR, null);
        body.add(newTail);
        tail = newTail;
        setSprites();
    }
}

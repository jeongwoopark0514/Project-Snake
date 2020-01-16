package game;

import java.io.IOException;
import java.util.List;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import lombok.Getter;
import lombok.Setter;

/**
 * This class provides the methods to check for collision.
 * It will check if the head of the snake collides with another elements on the board,
 * if it does collide it executes actions accordingly.
 */
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
class CollisionManager {
    private transient Board board;
    @Setter
    private transient Snake snake;
    private transient Game game;
    private transient Painter painter;
    @Getter @Setter
    private Sound sound = new Sound("music/pellet.wav");

    /**
     * Constructor for the collision manager.
     *
     * @param board the board on which the current level takes place.
     * @param snake the snake in the current game.
     * @param game  the game that is running.
     */
    CollisionManager(Board board, Snake snake, Game game) {
        this.board = board;
        this.snake = snake;
        this.game = game;
        this.painter = game.getPainter();
    }

    /**
     * The main method of the collision manager.
     * This checks if the next place of the snakes head will collide with an element.
     * If there is a collision the method will take action accordingly.
     * If there is no collision nothing happens.
     * If there was collision with a fruit the score increases and another fruit gets added.
     * If there was collision with either the snake or the wall,
     * game.stop() method will be called and the game will end.
     *
     * @return false if there was no collision and true if there is a collision.
     */
    boolean check() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        BodyPart head = snake.getHead();
        int x = head.getX();
        int y = head.getY();
        Tile tile = board.getTile(x, y);
        if (tile == null) {
            return false;
        } else if (tile instanceof Fruit) {
            sound.play();
            manageFruits((Fruit) tile);
            board.updateTile(x, y, head);
            System.out.println("Collision with fruit");
            return true;
        } else {
            System.out.println("Collision with " + tile.getClass().getName());
            game.stop();
            return true;
        }
    }

    /**
     * Test collision of the snake with all fruits on the map.
     * If the fruit was hit, make sure it gets removed.
     * Also make sure the snake grows.
     * Make sure there is always a fruit on the map and repaint all fruits on the map.
     */
    private void manageFruits(Fruit fruit) {
        List<Fruit> fruits = game.getFruits();
        game.increaseScore(fruit.getValue());
        fruits.remove(fruit);
        painter.unPaint(fruit);
        snake.grow();
        Fruit newFruit = game.createFruit();
        fruits.add(newFruit);
        board.updateTile(newFruit.getX(), newFruit.getY(), newFruit);
        painter.paint(newFruit);
    }
}

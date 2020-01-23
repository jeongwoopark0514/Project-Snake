package database;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * POJO class that contains all the data that is retrieved from the database,
 * which needs to be put into the lists of the leaderboard.
 */
public class Details {
    private SimpleIntegerProperty rank;
    private SimpleStringProperty name;
    private SimpleIntegerProperty score;

    /**
     * Constructor for POJO class.
     *
     * @param rank  the rank of players highscore
     * @param name  name of player
     * @param score score of the player
     */
    public Details(Integer rank, String name, Integer score) {
        this.rank = new SimpleIntegerProperty(rank);
        this.name = new SimpleStringProperty(name);
        this.score = new SimpleIntegerProperty(score);
    }

    /**
     * Get the rank of the player.
     *
     * @return the rank
     */
    public int getRank() {
        return rank.get();
    }

    /**
     * Get the name of the user.
     *
     * @return the name
     */
    public String getName() {
        return name.get();
    }

    /**
     * Get the score of the user.
     *
     * @return the score
     */
    public int getScore() {
        return score.get();
    }
}

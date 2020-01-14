package database;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class UserDetails {

    private SimpleIntegerProperty rank;
    private SimpleStringProperty username;
    private SimpleIntegerProperty score;

    /**
     * Constructor for POJO class.
     * @param rank - the rank of players highscore
     * @param username - username of player
     * @param score - score of the player
     */
    public UserDetails(Integer rank, String username, Integer score) {
        this.rank = new SimpleIntegerProperty(rank);
        this.username = new SimpleStringProperty(username);
        this.score = new SimpleIntegerProperty(score);
    }

    public int getRank() {
        return rank.get();
    }

    public SimpleIntegerProperty rankProperty() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = new SimpleIntegerProperty(rank);
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username = new SimpleStringProperty(username);
    }

    public int getScore() {
        return score.get();
    }

    public SimpleIntegerProperty scoreProperty() {
        return score;
    }

    public void setScore(int score) {
        this.score = new SimpleIntegerProperty(score);
    }
}

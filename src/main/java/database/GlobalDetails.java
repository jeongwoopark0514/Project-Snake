package database;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class GlobalDetails {

    private SimpleIntegerProperty globalRank;
    private SimpleStringProperty username;
    private SimpleIntegerProperty globalScore;

    /**
     * Constructor for POJO class.
     * @param globalRank - the rank of players highscore
     * @param username - username of player
     * @param globalScore - score of the player
     */
    public GlobalDetails(Integer globalRank, String username, Integer globalScore) {
        this.globalRank = new SimpleIntegerProperty(globalRank);
        this.username = new SimpleStringProperty(username);
        this.globalScore = new SimpleIntegerProperty(globalScore);
    }

    public int getGlobalRank() {
        return globalRank.get();
    }

    public SimpleIntegerProperty globalRankProperty() {
        return globalRank;
    }

    public void setGlobalRank(int globalRank) {
        this.globalRank = new SimpleIntegerProperty(globalRank);
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

    public int getGlobalScore() {
        return globalScore.get();
    }

    public SimpleIntegerProperty globalScoreProperty() {
        return globalScore;
    }

    public void setGlobalScore(int globalScore) {
        this.globalScore = new SimpleIntegerProperty(globalScore);
    }
}

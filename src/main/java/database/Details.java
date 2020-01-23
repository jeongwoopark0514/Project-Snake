package database;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Details {

    private SimpleIntegerProperty rank;
    private SimpleStringProperty name;
    private SimpleIntegerProperty score;

    /**
     * Constructor for POJO class.
     *
     * @param rank  - the rank of players highscore
     * @param name  - name of player
     * @param score - score of the player
     */
    public Details(Integer rank, String name, Integer score) {
        this.rank = new SimpleIntegerProperty(rank);
        this.name = new SimpleStringProperty(name);
        this.score = new SimpleIntegerProperty(score);
    }

    public int getRank() {
        return rank.get();
    }

    public void setRank(int rank) {
        this.rank = new SimpleIntegerProperty(rank);
    }

    public SimpleIntegerProperty rankProperty() {
        return rank;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public int getScore() {
        return score.get();
    }

    public void setScore(int score) {
        this.score = new SimpleIntegerProperty(score);
    }

    public SimpleIntegerProperty scoreProperty() {
        return score;
    }
}

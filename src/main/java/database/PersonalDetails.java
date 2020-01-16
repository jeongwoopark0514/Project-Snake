package database;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PersonalDetails {

    private SimpleIntegerProperty personalRank;
    private SimpleIntegerProperty personalScore;
    private SimpleStringProperty nickname;

    /**
     * This is the constructor for Personal Leaderboard POJO class.
     * @param personalRank - ranks of personal highest scores
     * @param personalScore - scores of current user
     * @param nickname - nicknames added for each game
     */
    public PersonalDetails(Integer personalRank, Integer personalScore, String nickname) {
        this.personalRank = new SimpleIntegerProperty(personalRank);
        this.personalScore = new SimpleIntegerProperty(personalScore);
        this.nickname = new SimpleStringProperty(nickname);
    }

    public int getPersonalRank() {
        return personalRank.get();
    }

    public SimpleIntegerProperty personalRankProperty() {
        return personalRank;
    }

    public void setPersonalRank(int personalRank) {
        this.personalRank = new SimpleIntegerProperty(personalRank);
    }

    public int getPersonalScore() {
        return personalScore.get();
    }

    public SimpleIntegerProperty personalScoreProperty() {
        return personalScore;
    }

    public void setPersonalScore(int personalScore) {
        this.personalScore = new SimpleIntegerProperty(personalScore);
    }

    public String getNickname() {
        return nickname.get();
    }

    public SimpleStringProperty nicknameProperty() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = new SimpleStringProperty(nickname);
    }


}

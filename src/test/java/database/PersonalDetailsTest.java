package database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;


class PersonalDetailsTest {

    @Test
    void getPersonalRankTest() {
        PersonalDetails personalDetails = new PersonalDetails(1, 1, "nicknameQ");
        assertEquals(1, personalDetails.getPersonalRank());
    }

    @Test
    void personalRankPropertyTest() {
        PersonalDetails personalDetails = new PersonalDetails(1, 1, "nicknameQ");
        assertNotNull(personalDetails.personalRankProperty());
    }

    @Test
    void setPersonalRankTest() {
        PersonalDetails personalDetails = new PersonalDetails(1, 1, "nicknamew");
        personalDetails.setPersonalRank(40);
        assertEquals(40, personalDetails.getPersonalRank());
    }

    @Test
    void getPersonalScoreTest() {
        PersonalDetails personalDetails = new PersonalDetails(1, 30, "nicknamew");
        assertEquals(30, personalDetails.getPersonalScore());
    }

    @Test
    void personalScorePropertyTest() {
        PersonalDetails personalDetails = new PersonalDetails(1, 1, "nicknamef");
        assertNotNull(personalDetails.personalScoreProperty());
    }

    @Test
    void setPersonalScore() {
        PersonalDetails personalDetails = new PersonalDetails(1, 1, "nicknamef");
        personalDetails.setPersonalScore(30);
        assertEquals(30, personalDetails.getPersonalScore());
    }

    @Test
    void getNicknameTest() {
        PersonalDetails personalDetails = new PersonalDetails(1, 1, "nickname");
        assertEquals("nickname", personalDetails.getNickname());
    }

    @Test
    void nicknameProperty() {
        PersonalDetails personalDetails = new PersonalDetails(1, 1, "nicknamed");
        assertNotNull(personalDetails.nicknameProperty());
    }

    @Test
    void setNicknameTest() {
        PersonalDetails personalDetails = new PersonalDetails(1, 1, "nicknameq");
        personalDetails.setNickname("nickname2");
        assertEquals("nickname2", personalDetails.getNickname());
    }
}
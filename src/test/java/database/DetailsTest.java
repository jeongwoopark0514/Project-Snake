package database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class DetailsTest {

    @Test
    void getRankTest() {
        Details details = new Details(1, "name", 30);
        assertEquals(1, details.getRank());
    }

    @Test
    void rankPropertyTest() {
        Details details = new Details(1, "name", 30);
        assertNotNull(details.rankProperty());
    }

    @Test
    void setRankTest() {
        Details details = new Details(1, "name2", 20);
        details.setRank(12);
        assertEquals(12, details.getRank());
    }

    @Test
    void getNameTest() {
        Details details = new Details(1, "name3", 30);
        assertEquals("name3", details.getName());
    }

    @Test
    void namePropertyTest() {
        Details details = new Details(4, "name31", 33);
        assertNotNull(details.nameProperty());
    }

    @Test
    void setNameTest() {
        Details details = new Details(1, "name", 30);
        details.setName("namename");
        assertEquals("namename", details.getName());
    }

    @Test
    void getScoreTest() {
        Details details = new Details(1, "nameqw", 30);
        assertEquals(30, details.getScore());
    }

    @Test
    void scorePropertyTest() {
        Details details = new Details(1, "nameqw", 30);
        assertNotNull(details.scoreProperty());
    }

    @Test
    void setScoreTest() {
        Details details = new Details(1, "sjkf", 234);
        details.setScore(23442);
        assertEquals(23442, details.getScore());
    }
}
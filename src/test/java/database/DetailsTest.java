package database;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DetailsTest {

    @Test
    void getRankTest() {
        Details details = new Details(1, "name", 30);
        assertEquals(1, details.getRank());
    }

    @Test
    void getNameTest() {
        Details details = new Details(1, "name3", 30);
        assertEquals("name3", details.getName());
    }

    @Test
    void getScoreTest() {
        Details details = new Details(1, "nameqw", 30);
        assertEquals(30, details.getScore());
    }
}
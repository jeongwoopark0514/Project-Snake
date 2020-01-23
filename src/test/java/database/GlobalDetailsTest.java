package database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class GlobalDetailsTest {

    @Test
    void getGlobalRankTest() {
        GlobalDetails globalDetails = new GlobalDetails(1, "username", 30);
        assertEquals(1, globalDetails.getGlobalRank());
    }

    @Test
    void globalRankPropertyTest() {
        GlobalDetails globalDetails = new GlobalDetails(1, "username", 30);
        assertNotNull(globalDetails.globalRankProperty());
    }

    @Test
    void setGlobalRankTest() {
        GlobalDetails globalDetails = new GlobalDetails(1, "username2", 20);
        globalDetails.setGlobalRank(12);
        assertEquals(12, globalDetails.getGlobalRank());
    }

    @Test
    void getUsernameTest() {
        GlobalDetails globalDetails = new GlobalDetails(1, "username3", 30);
        assertEquals("username3", globalDetails.getUsername());
    }

    @Test
    void usernamePropertyTest() {
        GlobalDetails globalDetails = new GlobalDetails(4, "username31", 33);
        assertNotNull(globalDetails.usernameProperty());
    }

    @Test
    void setUsernameTest() {
        GlobalDetails globalDetails = new GlobalDetails(1, "username", 30);
        globalDetails.setUsername("usernamename");
        assertEquals("usernamename", globalDetails.getUsername());
    }

    @Test
    void getGlobalScoreTest() {
        GlobalDetails globalDetails = new GlobalDetails(1, "usernameqw", 30);
        assertEquals(30, globalDetails.getGlobalScore());
    }

    @Test
    void globalScorePropertyTest() {
        GlobalDetails globalDetails = new GlobalDetails(1, "usernameqw", 30);
        assertNotNull(globalDetails.globalScoreProperty());
    }

    @Test
    void setGlobalScoreTest() {
        GlobalDetails globalDetails = new GlobalDetails(1, "sjkf", 234);
        globalDetails.setGlobalScore(23442);
        assertEquals(23442, globalDetails.getGlobalScore());
    }
}
package game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SettingsTest {

    @Test
    void defaultSettings() {
        assertEquals("", Settings.getBackground());
        assertEquals(0, Settings.getGameMode());
    }
}

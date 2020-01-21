package game;

import lombok.Getter;
import lombok.Setter;

/**
 * A class tht holds the settings from the "Settings" screen.
 */
public class Settings {

    @Setter
    @Getter
    private static String background = "";
    @Setter
    @Getter
    private static int gameMode = 0;

}

package gui.controller;

import database.DBconnect;
import database.SessionManager;
import gui.Gui;
import java.io.IOException;
import java.io.PrintWriter;
import lombok.Getter;
import lombok.Setter;

/**
 * Controls the entry page after login page.
 * This class calls methods from Gui class which contains only GUI logic.
 */
public class EntryController {
    public Gui gui = new Gui();
    @Getter
    @Setter
    private DBconnect database = DBconnect.getInstance();
    @Getter
    @Setter
    private SessionManager manager = SessionManager.getInstance();

    /**
     * When you click start button, move to game screen.
     */
    public void startGame() {
        gui.startSnakeGame();
    }

    /**
     * When you click, leaderboard button, move the page to leaderboard.
     */
    public void changeToLeaderBoard() throws IOException {
        gui.switchScene("src/main/resources/fxml/leaderboard.fxml");
    }

    /**
     * No testing required because impossible to test system.exit.
     */
    public void quitButton() {
        gui.quit();
    }

    /**
     * Sign out the game and go back to login page.
     */
    public void changeToLogin() throws IOException {
        //This is actually closed in the SessionsManager but PMD does not register this.
        PrintWriter writer = new PrintWriter("cookie.txt"); //NOPMD
        database.openConnection();
        manager.logOut(manager.getUsername(), writer);
        database.closeConnection();
        gui.switchScene("src/main/resources/fxml/login.fxml");
    }

    /**
     * Change the scene to settings.
     */
    public void changeToSettings() throws IOException {
        gui.switchScene("src/main/resources/fxml/setting.fxml");
    }
}

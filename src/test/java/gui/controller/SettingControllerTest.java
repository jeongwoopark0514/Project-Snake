package gui.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import game.Settings;
import gui.Gui;
import gui.GuiButton;
import java.io.IOException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SettingControllerTest {
    private GuiButton guiButton;
    private SettingController controller;

    @BeforeEach
    void setUp() {
        controller = new SettingController();
        guiButton = mock(GuiButton.class);
        controller.guiButton = guiButton;
    }

    @AfterEach
    void breakDown() {
        Settings.setBackground("");
        Settings.setGameMode(0);
        Settings.setSnakeColor("green");
        Settings.setPellets("apple-orange");
    }

    @Test
    void goBackEntryTest() {
        try {
            Gui gui = mock(Gui.class);
            controller.gui = gui;
            doNothing().when(gui).switchScene("Entry");
            controller.goBackEntry();
            verify(gui).switchScene(anyString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void changeBackgroundJungle() {
        doReturn(true).when(guiButton).equalsButton(any());
        controller.changeBackground();
        assertEquals("/image/jungle_image.png", Settings.getBackground());
    }

    @Test
    void changeBackgroundNight() {
        doReturn(false, true).when(guiButton).equalsButton(any());
        controller.changeBackground();
        assertEquals("/image/night_image.png", Settings.getBackground());
    }

    @Test
    void changeBackgroundDefault() {
        doReturn(false, false, true).when(guiButton).equalsButton(any());
        controller.changeBackground();
        assertEquals("", Settings.getBackground());
    }

    @Test
    void changeBackgroundNone() {
        doReturn(false).when(guiButton).equalsButton(any());
        controller.changeBackground();
        assertEquals("", Settings.getBackground());
    }

    @Test
    void changeDifficultyEasy() {
        doReturn(true).when(guiButton).equalsButton(any());
        controller.changeDifficulty();
        assertEquals(0, Settings.getGameMode());
    }

    @Test
    void changeDifficultyMedium() {
        doReturn(false, true).when(guiButton).equalsButton(any());
        controller.changeDifficulty();
        assertEquals(1, Settings.getGameMode());
    }

    @Test
    void changeDifficultyHard() {
        doReturn(false, false, true).when(guiButton).equalsButton(any());
        controller.changeDifficulty();
        assertEquals(2, Settings.getGameMode());
    }

    @Test
    void changeDifficultyNone() {
        doReturn(false).when(guiButton).equalsButton(any());
        controller.changeDifficulty();
        assertEquals(0, Settings.getGameMode());
    }

    @Test
    void changeSnakeColorGreen() {
        doReturn(true).when(guiButton).equalsButton(any());
        controller.changeSnakeColor();
        assertEquals("green", Settings.getSnakeColor());
    }

    @Test
    void changeSnakeColorYellow() {
        doReturn(false, true).when(guiButton).equalsButton(any());
        controller.changeSnakeColor();
        assertEquals("yellow", Settings.getSnakeColor());
    }

    @Test
    void changeSnakeColorGrey() {
        doReturn(false, false, true).when(guiButton).equalsButton(any());
        controller.changeSnakeColor();
        assertEquals("grey", Settings.getSnakeColor());
    }

    @Test
    void changeSnakeColorNone() {
        doReturn(false).when(guiButton).equalsButton(any());
        controller.changeSnakeColor();
        assertEquals("green", Settings.getSnakeColor());
    }

    @Test
    void changePelletsAppleOrange() {
        doReturn(true).when(guiButton).equalsButton(any());
        controller.changePellets();
        assertEquals("apple-orange", Settings.getPellets());
    }

    @Test
    void changePelletsMelonBanana() {
        doReturn(false, true).when(guiButton).equalsButton(any());
        controller.changePellets();
        assertEquals("melon-banana", Settings.getPellets());
    }

    @Test
    void changePelletsNone() {
        doReturn(false).when(guiButton).equalsButton(any());
        controller.changePellets();
        assertEquals("apple-orange", Settings.getPellets());
    }
}
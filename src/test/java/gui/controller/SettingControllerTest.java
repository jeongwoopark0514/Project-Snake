package gui.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import gui.Gui;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class SettingControllerTest {

    @Test
    void goBackEntryTest() {
        try {
            Gui gui = mock(Gui.class);
            SettingController settingController = new SettingController();
            settingController.gui = gui;
            doNothing().when(gui).switchScene("Entry");
            settingController.goBackEntry();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
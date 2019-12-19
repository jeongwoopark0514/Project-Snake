package gui.controller;

import gui.Gui;

import java.io.IOException;

public class LeaderBoardController {

    public transient Gui gui = new Gui();

    public void goBackMain() throws IOException {
        gui.switchScene("src/main/resources/fxml/entry.fxml");
    }

}

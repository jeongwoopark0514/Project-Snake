package gui.controller;

import database.DBconnect;
import database.GlobalDetails;
import database.PersonalDetails;
import database.SessionManager;
import gui.Gui;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;



/**
 * This is the controller of LeaderBoard.
 * This class will contain all the methods that control leader board, and will be kept separate
 * from GUI logic.
 * This controller class is in progress. Will be modified a lot in the next sprint.
 */
public class LeaderBoardController implements Initializable {

    public transient Gui gui = new Gui();

    public transient TableView<GlobalDetails> globalTable = new TableView<>();
    public transient TableView<PersonalDetails> personalTable = new TableView<>();

    public transient TableColumn<GlobalDetails, Integer> globalRank;
    public transient TableColumn<PersonalDetails, Integer> personalRank;

    public transient TableColumn<GlobalDetails, String> username;
    public transient TableColumn<PersonalDetails, String> nickname;

    public transient TableColumn<GlobalDetails, Integer> globalScore;
    public transient TableColumn<PersonalDetails, Integer> personalScore;

    private ObservableList<GlobalDetails> globalScores;
    private ObservableList<PersonalDetails> personalScores;

    private ArrayList<GlobalDetails> list = new ArrayList<>();
    private ArrayList<PersonalDetails> list2 = new ArrayList<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateLeaderboards();
    }

    /**
     * Fill in leaderboard information.
     */
    public void populateLeaderboards() {
        try {
            DBconnect.getInstance().getGlobalScores(list);
            DBconnect.getInstance().getPersonalScores(list2, SessionManager.getInstance().getUsername());
            globalScores = FXCollections.observableArrayList(list);
            personalScores = FXCollections.observableArrayList(list2);
            globalRank.setCellValueFactory(new PropertyValueFactory<>("globalRank"));
            username.setCellValueFactory(new PropertyValueFactory<>("username"));
            globalScore.setCellValueFactory(new PropertyValueFactory<>("globalScore"));
            personalRank.setCellValueFactory(new PropertyValueFactory<>("personalRank"));
            personalScore.setCellValueFactory(new PropertyValueFactory<>("personalScore"));
            nickname.setCellValueFactory(new PropertyValueFactory<>("nickname"));
            globalTable.setEditable(true);
            personalTable.setEditable(true);
            globalTable.setItems(globalScores);
            personalTable.setItems(personalScores);
        } catch (Exception e) {
            System.out.println("populateTable" + e);
        }
    }

    /**
     * This methods go back to the entry page when you click goBack button.
     */
    public void goBackMain() throws IOException {
        gui.switchScene("src/main/resources/fxml/entry.fxml");
    }

    /**
     * No testing required because impossible to test system.exit.
     */
    public void quitButton() {
        gui.quit();
    }

}

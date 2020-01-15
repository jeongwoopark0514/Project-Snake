package gui.controller;

import database.DBconnect;
import database.GlobalDetails;
import database.PersonalDetails;
import gui.Gui;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * This is the controller of LeaderBoard.
 * This class will contain all the methods that control leader board, and will be kept separate
 * from GUI logic.
 * This controller class is in progress. Will be modified a lot in the next sprint.
 */
public class LeaderBoardController implements Initializable {

    public transient Gui gui = new Gui();

    @FXML
    private TableView<GlobalDetails> globalTable = new TableView<>();
    @FXML
    private TableView<PersonalDetails> personalTable = new TableView<>();
    @FXML
    private TableColumn<GlobalDetails, Integer> global_rank;
    @FXML
    private TableColumn<PersonalDetails, Integer> personal_rank;
    @FXML
    private TableColumn<GlobalDetails, String> username;
    @FXML
    private TableColumn<PersonalDetails, String> nickname;
    @FXML
    private TableColumn<GlobalDetails, Integer> global_score;
    @FXML
    private TableColumn<PersonalDetails, Integer> personal_score;

    private ObservableList<GlobalDetails> globalScores;

    private ObservableList<PersonalDetails> personalScores;

    private ArrayList<GlobalDetails> list = new ArrayList<>();
    private ArrayList<PersonalDetails> list2 = new ArrayList<>();

    private DBconnect database = new DBconnect();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateLeaderboards();
    }

    /**
     * When you click start button, move to game screen.
     */
    public void startGame() {
        gui.startSnakeGame();
    }

    /**
     * When leaderboard button is clicked, move to leaderboard screen.
     * @throws IOException - Exception if the file does not exist.
     */
    public void changeToLeaderBoard() throws IOException {
        gui.switchScene("src/main/resources/fxml/leaderboard.fxml");
    }

    /**
     * Fill in global highscore table.
     */
    public void populateLeaderboards() {
        try {
            database.getGlobalScores(list);
            database.getPersonalScores(list2,"Rohan");
            globalScores = FXCollections.observableArrayList(list);
            personalScores = FXCollections.observableArrayList(list2);
            global_rank.setCellValueFactory(new PropertyValueFactory<>("globalRank"));
            username.setCellValueFactory(new PropertyValueFactory<>("username"));
            global_score.setCellValueFactory(new PropertyValueFactory<>("globalScore"));
            personal_rank.setCellValueFactory(new PropertyValueFactory<>("personalRank"));
            personal_score.setCellValueFactory(new PropertyValueFactory<>("personalScore"));
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
     * @throws IOException Exception if the file does not exist.
     */
    public void goBackMain() throws IOException {
        gui.switchScene("src/main/resources/fxml/entry.fxml");
    }

}

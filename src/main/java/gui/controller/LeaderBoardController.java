package gui.controller;

import database.DBconnect;
import database.UserDetails;
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
    private TableView<UserDetails> globalTable = new TableView<>();
    @FXML
    private TableColumn<UserDetails, Integer> col_rank;
    @FXML
    private TableColumn<UserDetails, String> col_username;
    @FXML
    private TableColumn<UserDetails, Integer> col_score;

    private ObservableList<UserDetails> scores;
    private ArrayList<UserDetails> list = new ArrayList<>();
    private DBconnect database = new DBconnect();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateTable();
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
    public void globalLeaderboard() throws IOException {
        gui.switchScene("src/main/resources/fxml/leaderboard.fxml");
    }

    /**
     * Fill in global highscore table.
     */
    public void populateTable() {
        try {
            database.getGlobalScores(list);
            scores = FXCollections.observableArrayList(list);
            col_rank.setCellValueFactory(new PropertyValueFactory<>("rank"));
            col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
            col_score.setCellValueFactory(new PropertyValueFactory<>("score"));
            globalTable.setEditable(true);
            globalTable.setItems(scores);
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

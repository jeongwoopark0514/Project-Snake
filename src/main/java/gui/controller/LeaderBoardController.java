package gui.controller;

import database.DBconnect;
import database.UserDetails;
import gui.Gui;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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

    @FXML
    private TableView<UserDetails> globalTable;
    @FXML
    private TableColumn<UserDetails, Integer> rank;
    @FXML
    private TableColumn<UserDetails, String> username;
    @FXML
    private TableColumn<UserDetails, Integer> score;

    private ObservableList<UserDetails> scores;
    private DBconnect database = new DBconnect();

    private int position = 1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * When you click start button, move to game screen.
     */
    public void startGame() {
        gui.startSnakeGame();
    }


    /**
     * loads the global leaderboard scores to table.
     */
    public void globalLeaderboard() {
        try {
            gui.switchScene("src/main/resources/fxml/leaderboard.fxml");
            scores = FXCollections.observableArrayList(
                    new UserDetails(
                            position,
                            database.getGlobalScores().getString("username"),
                            database.getGlobalScores().getInt("score"))
            );
            rank.setCellValueFactory(new PropertyValueFactory<>("Rank"));
            username.setCellValueFactory(new PropertyValueFactory<>("Username"));
            score.setCellValueFactory(new PropertyValueFactory<>("Score"));
            globalTable.setItems(scores);
            position += 1;
        } catch (Exception e) {
            System.out.println(e);
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

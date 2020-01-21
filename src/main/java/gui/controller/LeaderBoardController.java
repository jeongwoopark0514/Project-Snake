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
import lombok.Getter;
import lombok.Setter;


/**
 * This is the controller of LeaderBoard.
 * This class will contain all the methods that control leader board, and will be kept separate
 * from GUI logic.
 * This controller class is in progress. Will be modified a lot in the next sprint.
 */
public class LeaderBoardController implements Initializable {

    public Gui gui = new Gui();

    public TableView<GlobalDetails> globalTable = new TableView<>();
    public TableView<PersonalDetails> personalTable = new TableView<>();

    public TableColumn<GlobalDetails, Integer> globalRank;
    public TableColumn<PersonalDetails, Integer> personalRank;

    public TableColumn<GlobalDetails, String> username;
    public TableColumn<PersonalDetails, String> nickname;

    public TableColumn<GlobalDetails, Integer> globalScore;
    public TableColumn<PersonalDetails, Integer> personalScore;

    private ObservableList<GlobalDetails> globalScores;
    private ObservableList<PersonalDetails> personalScores;

    private ArrayList<GlobalDetails> list = new ArrayList<>();
    private ArrayList<PersonalDetails> list2 = new ArrayList<>();

    @Getter
    @Setter
    private DBconnect database = DBconnect.getInstance();
    @Getter
    @Setter
    private SessionManager manager = SessionManager.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        openDB();
        tableViewGlobal();
        tableViewPersonal();
        populateLeaderboards();
        database.closeConnection();
    }

    public void openDB() {
        database.openConnection();
        database.getGlobalScores(list);
        database.getPersonalScores(list2, manager.getUsername());
    }

    public void tableViewGlobal() {
        globalScores = FXCollections.observableArrayList(list);
        globalRank.setCellValueFactory(new PropertyValueFactory<>("globalRank"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        globalScore.setCellValueFactory(new PropertyValueFactory<>("globalScore"));
    }

    public void tableViewPersonal() {
        personalScores = FXCollections.observableArrayList(list2);
        personalRank.setCellValueFactory(new PropertyValueFactory<>("personalRank"));
        personalScore.setCellValueFactory(new PropertyValueFactory<>("personalScore"));
        nickname.setCellValueFactory(new PropertyValueFactory<>("nickname"));
    }
    /**
     * Fill in leaderboard information.
     */
    public void populateLeaderboards() {
        try {
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


}

package gui.controller;

import database.DBconnect;
import database.Details;
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
    public TableView<Details> globalTable;
    public TableView<Details> personalTable;
    public TableColumn<Details, Integer> globalRank;
    public TableColumn<Details, Integer> personalRank;
    public TableColumn<Details, String> username;
    public TableColumn<Details, String> nickname;
    public TableColumn<Details, Integer> globalScore;
    public TableColumn<Details, Integer> personalScore;
    private ObservableList<Details> globalScores;
    private ObservableList<Details> personalScores;
    private ArrayList<Details> globalList = new ArrayList<>();
    private ArrayList<Details> personalList = new ArrayList<>();
    @Getter
    @Setter
    private DBconnect database = DBconnect.getInstance();
    @Getter
    @Setter
    private SessionManager manager = SessionManager.getInstance();

    public LeaderBoardController() {
        globalTable = new TableView<>();
        personalTable = new TableView<>();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        openDB();
        tableViewGlobal();
        tableViewPersonal();
        populateLeaderboards();

        database.closeConnection();
    }

    /**
     * Open DB connection for tables.
     */
    public void openDB() {
        database.openConnection();
        database.getGlobalScores(globalList);
        database.getPersonalScores(personalList, manager.getUsername());
    }

    /**
     * Setting up tableview for global table.
     */
    private void tableViewGlobal() {
        globalScores = FXCollections.observableArrayList(globalList);
        globalRank.setCellValueFactory(new PropertyValueFactory<>("rank"));
        username.setCellValueFactory(new PropertyValueFactory<>("name"));
        globalScore.setCellValueFactory(new PropertyValueFactory<>("score"));
    }

    /**
     * Setting up tableview for personal table.
     */
    private void tableViewPersonal() {
        personalScores = FXCollections.observableArrayList(personalList);
        personalRank.setCellValueFactory(new PropertyValueFactory<>("rank"));
        personalScore.setCellValueFactory(new PropertyValueFactory<>("score"));
        nickname.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    /**
     * Fill in leaderboard information.
     */
    private void populateLeaderboards() {
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

    /**
     * Implements the functionality for the quit button.
     */
    public void quitButton() {
        gui.quit();
    }
}

package gui.controller;

import database.DBconnect;
import database.SessionManager;
import gui.Gui;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class LoginController {

    @FXML
    public TextField loginUsername;
    public PasswordField loginPassword;
    public TextField registerUsername;
    public PasswordField registerPassword;
    public PasswordField confirmPassword;

    public Gui gui = new Gui();

    @Getter
    @Setter
    private DBconnect database = DBconnect.getInstance();
    @Getter
    @Setter
    private SessionManager manager = SessionManager.getInstance();

    /**
     * when you click register button, move to register page.
     *
     * @throws IOException IOexception thrown for null file.
     */
    public void clickRegister() throws IOException {
        gui.switchScene("src/main/resources/fxml/register.fxml");
    }


    /**
     * This method checks if the login was successful.
     */
    public void login() throws IOException {
        database.openConnection();
        if (gui.getText(loginUsername).equals("") || gui.getText(loginPassword).equals("")) {
            System.out.println("LOGIN UNSUCCESSFUL");
            gui.showWarningAlert("One or multiple fields have not been filled in!",
                "Empty field(s)");
        } else if (database.authenticate(gui.getText(loginUsername),
            gui.getText(loginPassword), null)) {
            System.out.println("LOGIN SUCCESSFUL");
            //This is actually closed in the SessionsManager but PMD does not register this.
            PrintWriter writer = new PrintWriter("cookie.txt"); //NOPMD
            manager.saveCookie(writer, gui.getText(loginUsername));
            gui.switchScene("src/main/resources/fxml/entry.fxml");
        } else {
            gui.showWarningAlert("Wrong username/password combination. Please try again.",
                "Something went wrong");
            System.out.println("LOGIN UNSUCCESSFUL");
        }
        database.closeConnection();
    }

    /**
     * This method checks if the user gets registered into the database.
     */
    public void register() {
        database.openConnection();
        if (!gui.getText(registerPassword).equals(gui.getText(confirmPassword))) {
            gui.showWarningAlert("Passwords do not match!", "Something went wrong");
            System.out.println("REGISTRATION UNSUCCESSFUL");
        } else if (gui.getText(registerUsername).equals("")
            || gui.getText(registerPassword).equals("")
            || gui.getText(registerPassword).equals("")) {
            gui.showWarningAlert("One or multiple fields have not been filled in!",
                    "Empty field(s)");
            System.out.println("REGISTRATION UNSUCCESSFUL");
        } else if (database.registerUser(gui.getText(registerUsername),
            gui.getText(registerPassword), null)) {
            gui.showAlert("Successfully registered.", "Success");
            System.out.println("REGISTRATION SUCCESSFUL");
        } else {
            gui.showWarningAlert("Username already taken!", "Something went wrong");
            System.out.println("REGISTRATION UNSUCCESSFUL");
        }
        database.closeConnection();
    }

    /**
     * when you click goback button, move to login page.
     *
     * @throws IOException IOexception thrown for null file.
     */
    public void goBackLogin() throws IOException {
        gui.switchScene("src/main/resources/fxml/login.fxml");
    }


}

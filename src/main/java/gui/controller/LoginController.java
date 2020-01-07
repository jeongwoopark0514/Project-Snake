package gui.controller;

import database.DBconnect;
import gui.Gui;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.Setter;



public class LoginController {

    @FXML
    public transient TextField loginUsername;
    public transient PasswordField loginPassword;
    public transient TextField registerUsername;
    public transient PasswordField registerPassword;
    public transient PasswordField confirmPassword;

    public transient Gui gui = new Gui();

    @Getter @Setter
    private DBconnect database = DBconnect.getInstance();

    /**
     * when you click register button, move to register page.
     * @throws IOException IOexception thrown for null file.
     */
    public void clickRegister() throws IOException {
        gui.switchScene("src/main/resources/fxml/register.fxml");
    }


    /**
     * This method checks if the login was successful.
     */
    public void login() throws IOException {
        if (gui.getText(loginUsername).equals("") || gui.getText(loginPassword).equals("")) {
            System.out.println("LOGIN UNSUCCESSFUL");
            gui.showAlert("One or multiple fields have not been filled in!", "Empty field(s)");
        } else if (database.authenticate(gui.getText(loginUsername),
            gui.getText(loginPassword), null)) {
            System.out.println("LOGIN SUCCESSFUL");
            gui.switchScene("src/main/resources/fxml/entry.fxml");
        } else {
            gui.showAlert("Wrong username/password combination. Please try again.",
                "Something went wrong");
            System.out.println("LOGIN UNSUCCESSFUL");
        }
    }

    /**
     * This method checks if the user gets registered into the database.
     */
    public void register() {
        if (!gui.getText(registerPassword).equals(gui.getText(confirmPassword))) {
            gui.showAlert("Passwords do not match!", "Something went wrong");
            System.out.println("REGISTRATION UNSUCCESSFUL");
        } else if (gui.getText(registerUsername).equals("")
            || gui.getText(registerPassword).equals("")
            || gui.getText(registerPassword).equals("")) {
            gui.showAlert("One or multiple fields have not been filled in!",
                    "Empty field(s)");
            System.out.println("REGISTRATION UNSUCCESSFUL");
        } else if (database.registerUser(gui.getText(registerUsername),
            gui.getText(registerPassword), null)) {
            gui.showAlert("Successfully registered.", "Success");
            System.out.println("REGISTRATION SUCCESSFUL");
        } else {
            gui.showAlert("Username already taken!", "Something went wrong");
            System.out.println("REGISTRATION UNSUCCESSFUL");
        }
    }

    /**
     * when you click goback button, move to login page.
     * @throws IOException IOexception thrown for null file.
     */
    public void goBackLogin() throws IOException {
        gui.switchScene("src/main/resources/fxml/login.fxml");
    }


}

package gui.controller;

import database.DBconnect;
import gui.Gui;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;


public class LoginController {
    /**
     * when you click register button, move to register page.
     * @throws IOException IOexception thrown for null file.
     */
    public void clickRegister() throws IOException {
        gui.switchScene("src/main/resources/fxml/register.fxml");
    }

    @FXML
    public transient TextField loginUsername;
    public transient PasswordField loginPassword;
    public transient TextField registerUsername;
    public transient PasswordField registerPassword;
    public transient PasswordField confirmPassword;

    public Gui gui = new Gui();
    private DBconnect database = new DBconnect();



    /**
     * This method checks if the login was successful.
     */
    public void login() {
        try {
            if (loginUsername.getText().equals("") || loginPassword.getText().equals("")) {
                System.out.println("LOGIN UNSUCCESSFUL");
                gui.showAlert("One or multiple fields have not been filled in!", "Empty field(s)");
            } else if (database.authenticate(loginUsername.getText(), loginPassword.getText())) {
                System.out.println("LOGIN SUCCESSFUL");
                gui.switchScene("src/main/resources/fxml/entry.fxml");
            } else {
                gui.showAlert("Wrong username/password combination. Please try again.",
                    "Something went wrong");
                System.out.println("LOGIN UNSUCCESSFUL");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * This method checks if the user gets registered into the database.
     */
    public void register() {
        try {
            if (!registerPassword.getText().equals(confirmPassword.getText())) {
                gui.showAlert("Passwords do not match!", "Something went wrong");
                System.out.println("REGISTRATION UNSUCCESSFUL");
            } else if (registerUsername.getText().equals("")
                    || registerPassword.getText().equals("")
                    || confirmPassword.getText().equals("")) {
                gui.showAlert("One or multiple fields have not been filled in!",
                        "Empty field(s)");
                System.out.println("REGISTRATION UNSUCCESSFUL");
            } else if (database.registerUser(registerUsername.getText(),registerPassword.getText())) {
                gui.showAlert("Successfully registered.", "Success");
                System.out.println("REGISTRATION SUCCESSFUL");
            } else {
                gui.showAlert("Username already taken!", "Something went wrong");
                System.out.println("REGISTRATION UNSUCCESSFUL");
            }
        } catch (Exception e) {
            System.out.println(e);
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

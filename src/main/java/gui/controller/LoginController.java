package gui.controller;

import database.DBconnect;
import gui.AlertBox;
import gui.MainRunner;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.Setter;


public class LoginController {


    /**
     * when you click register button, move to register page.
     * @throws IOException IOexception thrown for null file.
     */
    public void clickRegister() throws IOException {
        final URL url = new File("src/main/resources/fxml/register.fxml").toURI().toURL();
        final Parent parentRegister = FXMLLoader.load(url);

        Scene registerScene = new Scene(parentRegister);
        MainRunner.stage.setScene(registerScene);
    }

    @FXML
    @Getter @Setter public TextField loginUsername;
    @Getter @Setter public TextField loginPassword;
    @Getter @Setter public TextField registerUsername;
    @Getter @Setter public TextField registerPassword;
    @Getter @Setter public TextField confirmPassword;

    @Getter @Setter private DBconnect database = new DBconnect();

    public String getLoginUsernameText() {

        return loginUsername.getText();
    }

    public String getLoginPasswordText() {

        return loginPassword.getText();
    }

    public String getRegisterUsernameText() {
        return registerUsername.getText();
    }

    public String getRegisterPasswordText() {
        return registerPassword.getText();
    }

    public String getConfirmPasswordText() {
        return confirmPassword.getText();
    }

    /**
     * This method checks if the login was successful.
     */
    public void login() {
        try {
            if (getLoginUsernameText().equals("") || getLoginPasswordText().equals("")) {
                System.out.println("LOGIN UNSUCCESSFUL");
                AlertBox.display("One or multiple fields have not been filled in!",
                        "Empty field(s)");
            } else if (database.loginData(getLoginUsernameText(), getLoginPasswordText())) {
                System.out.println("LOGIN SUCCESSFUL");
                final URL url = new File("src/main/resources/fxml/entry.fxml").toURI().toURL();
                final Parent entryParent = FXMLLoader.load(url);
                MainRunner.stage.setScene(new Scene(entryParent, 1000, 600));
            } else {
                AlertBox.display("Wrong username/password combination. Please try again.",
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
            if (!getRegisterPasswordText().equals(getConfirmPasswordText())) {
                AlertBox.display("Passwords do not match!", "Something went wrong");
                System.out.println("REGISTRATION UNSUCCESSFUL");
            } else if (getRegisterUsernameText().equals("")
                    || getRegisterPasswordText().equals("")
                    || getConfirmPasswordText().equals("")) {
                AlertBox.display("One or multiple fields have not been filled in!",
                        "Empty field(s)");
                System.out.println("REGISTRATION UNSUCCESSFUL");
            } else if (database.registerUser(getRegisterUsernameText(),getRegisterPasswordText())) {
                AlertBox.display("Successfully registered.", "Success");
                System.out.println("REGISTRATION SUCCESSFUL");
            } else {
                AlertBox.display("Username already taken!", "Something went wrong");
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
        final URL url = new File("src/main/resources/fxml/login.fxml").toURI().toURL();
        final Parent parentRegister = FXMLLoader.load(url);

        Scene loginScene = new Scene(parentRegister);
        MainRunner.stage.setScene(loginScene);
    }


}

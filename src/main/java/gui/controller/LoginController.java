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
import javafx.scene.control.PasswordField;
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
    @Getter @Setter public TextField loginusername;
    @Getter @Setter public PasswordField loginpassword;
    @Getter @Setter public TextField registerusername;
    @Getter @Setter public PasswordField registerpassword;
    @Getter @Setter public PasswordField confirmpassword;

    public String getLoginusernametext() {

        return loginusername.getText();
    }

    public String getLoginpasswordtext() {

        return loginpassword.getText();
    }

    public String getRegisterusernametext() {
        return registerusername.getText();
    }

    public String getRegisterpasswordtext() {
        return registerpassword.getText();
    }

    public String getConfirmpasswordtext() {
        return confirmpassword.getText();
    }

    /**
     * This method checks if the login was successful.
     */
    @SuppressWarnings("PMD")
    public void login() {
        try {
            DBconnect database = new DBconnect();
            if (getLoginusernametext().equals("") || getLoginpasswordtext().equals("")) {
                System.out.println("LOGIN UNSUCCESSFUL");
                AlertBox.display("One or multiple fields have not been filled in!",
                        "Empty field(s)");
            } else if (database.loginData(getLoginusernametext(), getLoginpasswordtext())) {
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
    @SuppressWarnings("PMD")
    public void register() {
        try {
            DBconnect database = new DBconnect();
            if (!getRegisterpasswordtext().equals(getConfirmpasswordtext())) {
                AlertBox.display("Passwords do not match!", "Something went wrong");
                System.out.println("REGISTRATION UNSUCCESSFUL");
            } else if (getRegisterusernametext().equals("")
                    || getRegisterpasswordtext().equals("")
                    || getConfirmpasswordtext().equals("")) {
                AlertBox.display("One or multiple fields have not been filled in!",
                        "Empty field(s)");
                System.out.println("REGISTRATION UNSUCCESSFUL");
            } else if (database.registerUser(getRegisterusernametext(),getRegisterpasswordtext())) {
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

package gui.controller;

import Database.DBconnect;
import gui.AlertBox;
import gui.MainRunner;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.net.URL;


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
    public TextField username;
    public TextField password;


    public String getUsername() {
        return username.getText();
    }

    public String getPassword() {
        return password.getText();
    }


    public void login() {
        try{
        DBconnect database = new DBconnect();
        if (database.loginData(getUsername(), getPassword())) {
            System.out.println("LOGIN SUCCESSFUL");
            System.out.println();
            AlertBox.display("You are logged in!", "Success");
        } else {
            AlertBox.display("Wrong username/password combination. Please try again.",
                    "Something went wrong");
            System.out.println("LOGIN UNSUCCESSFUL");
            System.out.println();
        }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}

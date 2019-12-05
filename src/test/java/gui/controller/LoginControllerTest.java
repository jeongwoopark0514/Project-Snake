package gui.controller;

import static org.mockito.ArgumentMatchers.any;

import database.DBconnect;
import gui.Gui;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;




class LoginControllerTest {


    @Test
    void clickRegisterTest() {
        try {
            Gui gui = Mockito.mock(Gui.class);
            LoginController loginController = new LoginController();
            loginController.gui = gui;
            Mockito.doNothing().when(gui).switchScene("whatever");
            loginController.clickRegister();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void loginUserPassEmpty() throws IOException {
        Gui gui = Mockito.mock(Gui.class);
        LoginController loginController = new LoginController();
        loginController.gui = gui;
        Mockito.when(gui.loginUserOrPassEmpty(any(), any())).thenReturn(true);
        Mockito.doNothing().when(gui).showAlert("alert", "title");
        loginController.login();
    }

    @Test
    void loginDatabaseTrue() throws IOException {
        Gui gui = Mockito.mock(Gui.class);
        LoginController loginController = new LoginController();
        loginController.gui = gui;
        DBconnect database = Mockito.mock(DBconnect.class);
        Mockito.when(database.authenticate("hey", "hey")).thenReturn(true);
        loginController.setDatabase(database);
        Mockito.when(gui.getText(any())).thenReturn("hey");
        try {
            Mockito.doNothing().when(gui).switchScene("message");
        } catch (IOException e) {
            e.printStackTrace();
        }
        loginController.login();
    }

    @Test
    void loginDatabaseFalse() throws IOException {
        Gui gui = Mockito.mock(Gui.class);
        LoginController loginController = new LoginController();
        loginController.gui = gui;
        DBconnect database = Mockito.mock(DBconnect.class);
        Mockito.when(database.authenticate("bye", "bye")).thenReturn(false);
        loginController.setDatabase(database);
        Mockito.when(gui.getText(any())).thenReturn("bye");
        try {
            Mockito.doNothing().when(gui).switchScene("file");
        } catch (IOException e) {
            e.printStackTrace();
        }
        loginController.login();
    }

    @Test
    void registerUserNameTwoPassGood() {
        Gui gui = Mockito.mock(Gui.class);
        LoginController loginController = new LoginController();
        loginController.gui = gui;
        Mockito.when(gui.registerAndConfirm(any(), any())).thenReturn(true);
        Mockito.when(gui.threeAllCorrect(any(), any(), any())).thenReturn(true);
        Mockito.doNothing().when(gui).showAlert("message", "title");
        loginController.register();
    }

    @Test
    void registerTwoPassWordsDiff() {
        Gui gui = Mockito.mock(Gui.class);
        LoginController loginController = new LoginController();
        loginController.gui = gui;
        Mockito.when(gui.registerAndConfirm(any(), any())).thenReturn(false);
        Mockito.doNothing().when(gui).showAlert("showAlert", "title");
        loginController.register();
    }

    @Test
    void registerDatabaseTrue() {
        Gui gui = Mockito.mock(Gui.class);
        LoginController loginController = new LoginController();
        loginController.gui = gui;
        DBconnect database = Mockito.mock(DBconnect.class);
        Mockito.when(gui.registerAndConfirm(any(), any())).thenReturn(true);
        Mockito.when(gui.threeAllCorrect(any(), any(), any())).thenReturn(false);
        Mockito.when(gui.getText(any())).thenReturn("hoi");
        Mockito.when(database.registerUser("hoi", "hoi")).thenReturn(true);
        loginController.setDatabase(database);

        Mockito.doNothing().when(gui).showAlert("congrats", "title1");
        loginController.register();
    }

    @Test
    void registerDatabaseFalse() {
        Gui gui = Mockito.mock(Gui.class);
        LoginController loginController = new LoginController();
        loginController.gui = gui;
        DBconnect database = Mockito.mock(DBconnect.class);
        Mockito.when(gui.registerAndConfirm(any(), any())).thenReturn(true);
        Mockito.when(gui.threeAllCorrect(any(), any(), any())).thenReturn(false);
        Mockito.when(database.registerUser("hi", "z")).thenReturn(false);
        loginController.setDatabase(database);
        Mockito.doNothing().when(gui).showAlert("congrats", "title1");
        loginController.register();
    }


    @Test
    void goBackLoginTest() {
        try {
            Gui gui = Mockito.mock(Gui.class);
            LoginController loginController = new LoginController();
            loginController.gui = gui;
            Mockito.doNothing().when(gui).switchScene("whatever");
            loginController.goBackLogin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
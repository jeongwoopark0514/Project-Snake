package gui.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import database.DBconnect;
import database.SessionManager;
import gui.Gui;
import java.io.IOException;
import org.junit.jupiter.api.Test;




class LoginControllerTest {

    private transient String multiple = "One or multiple fields have not been filled in!";
    private transient String empty = "Empty field(s)";

    @Test
    void clickRegisterTest() {
        try {
            Gui gui = mock(Gui.class);
            LoginController loginController = new LoginController();
            loginController.gui = gui;
            doNothing().when(gui).switchScene("whatever");
            loginController.clickRegister();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void loginNamePassEmpty() throws IOException {
        Gui gui = mock(Gui.class);
        LoginController loginController = new LoginController();
        loginController.gui = gui;
        when(gui.getText(any())).thenReturn("");
        loginController.login();
        verify(gui).showAlert(any(), eq(empty));
    }


    @Test
    void loginNameTruePassFalse() throws IOException {
        Gui gui = mock(Gui.class);
        LoginController loginController = new LoginController();
        loginController.gui = gui;
        when(gui.getText(any())).thenReturn("").thenReturn(" ");
        loginController.login();
        verify(gui).showAlert(any(), eq(empty));
    }

    @Test
    void loginNameFalsePassTrue() throws IOException {
        Gui gui = mock(Gui.class);
        LoginController loginController = new LoginController();
        loginController.gui = gui;
        when(gui.getText(any())).thenReturn(" ").thenReturn("");
        loginController.login();
        verify(gui).showAlert(any(), eq(empty));
    }

    @Test
    void loginDatabaseTrue() throws IOException {
        Gui gui = mock(Gui.class);
        LoginController loginController = new LoginController();
        loginController.setManager(mock(SessionManager.class));
        loginController.gui = gui;
        DBconnect database = mock(DBconnect.class);
        when(database.authenticate("hey", "hey", null)).thenReturn(true);
        loginController.setDatabase(database);
        when(gui.getText(any())).thenReturn("hey");
        try {
            doNothing().when(gui).switchScene("message");
        } catch (IOException e) {
            e.printStackTrace();
        }
        loginController.login();
    }

    @Test
    void loginDatabaseFalse() throws IOException {
        Gui gui = mock(Gui.class);
        LoginController loginController = new LoginController();
        loginController.gui = gui;
        DBconnect database = mock(DBconnect.class);
        when(database.authenticate("bye", "bye", null)).thenReturn(false);
        loginController.setDatabase(database);
        when(gui.getText(any())).thenReturn("bye");
        try {
            doNothing().when(gui).switchScene("file");
        } catch (IOException e) {
            e.printStackTrace();
        }
        loginController.login();
    }

    @Test
    void registerEqualsFalse() {
        Gui gui = mock(Gui.class);
        LoginController loginController = new LoginController();
        loginController.gui = gui;
        when(gui.getText(any())).thenReturn("").thenReturn(" ");
        doNothing().when(gui).showAlert("message", "title");
        loginController.register();
    }

    //1 tff
    @Test
    void registerThreeEqualsTrueFalseFalse() {
        Gui gui = mock(Gui.class);
        LoginController loginController = new LoginController();
        loginController.gui = gui;
        when(gui.getText(any())).thenReturn("k")
            .thenReturn("k").thenReturn("").thenReturn(" ").thenReturn(" ");
        loginController.register();
        verify(gui).showAlert(multiple,
            empty);

    }

    //2 fff
    @Test
    void registerThreeEqualsFalseFalseFalse() {
        Gui gui = mock(Gui.class);
        LoginController loginController = new LoginController();
        loginController.gui = gui;
        when(gui.getText(any())).thenReturn("k")
            .thenReturn("k").thenReturn("y").thenReturn("y").thenReturn("y");
        DBconnect database = mock(DBconnect.class);
        when(gui.getText(any())).thenReturn("Kk").thenReturn("Kk");
        when(database.registerUser(gui.getText(any()),
            gui.getText(any()), null)).thenReturn(true);
        loginController.setDatabase(database);
        loginController.register();
        verify(gui).showAlert("Successfully registered.", "Success");
    }

    @Test
    void registerThreeEqualsFalseFalseFalse2() {
        Gui gui = mock(Gui.class);
        LoginController loginController = new LoginController();
        loginController.gui = gui;
        when(gui.getText(any())).thenReturn("k").thenReturn("k")
            .thenReturn("y").thenReturn("y").thenReturn("y");
        DBconnect database = mock(DBconnect.class);
        when(gui.getText(any())).thenReturn("Kk").thenReturn("Kk");
        when(database.registerUser(gui.getText(any()),
            gui.getText(any()), null)).thenReturn(false);
        loginController.setDatabase(database);
        loginController.register();
        verify(gui).showAlert("Username already taken!", "Something went wrong");
    }

    //3 ftf
    @Test
    void registerThreeEqualsFalseTrueFalse() {
        Gui gui = mock(Gui.class);
        LoginController loginController = new LoginController();
        loginController.gui = gui;
        when(gui.getText(any())).thenReturn("k").thenReturn("k")
            .thenReturn("y").thenReturn("").thenReturn("y");
        loginController.register();
        verify(gui).showAlert(multiple,
            empty);
    }

    //4 ftf
    @Test
    void registerThreeEqualsTrueTrueFalse() {
        Gui gui = mock(Gui.class);
        LoginController loginController = new LoginController();
        loginController.gui = gui;
        when(gui.getText(any())).thenReturn("q").thenReturn("q").thenReturn("")
            .thenReturn("").thenReturn("y");
        loginController.register();
        verify(gui).showAlert(multiple,
            empty);
    }

    //5 ttt
    @Test
    void registerThreeEqualsTrueTrueTrue() {
        Gui gui = mock(Gui.class);
        LoginController loginController = new LoginController();
        loginController.gui = gui;
        when(gui.getText(any())).thenReturn("q").thenReturn("q")
            .thenReturn("").thenReturn("").thenReturn("");
        loginController.register();
        verify(gui).showAlert(multiple,
            empty);
    }

    //6 ftt
    @Test
    void registerThreeEqualsFalseTrueTrue() {
        Gui gui = mock(Gui.class);
        LoginController loginController = new LoginController();
        loginController.gui = gui;
        when(gui.getText(any())).thenReturn("a")
            .thenReturn("a").thenReturn("x").thenReturn("").thenReturn("");
        loginController.register();
        verify(gui).showAlert(multiple,
            empty);
    }

    //7 tft
    @Test
    void registerThreeEqualsTrueFalseTrue() {
        Gui gui = mock(Gui.class);
        LoginController loginController = new LoginController();
        loginController.gui = gui;
        when(gui.getText(any())).thenReturn("l")
            .thenReturn("l").thenReturn("").thenReturn("f").thenReturn("");
        loginController.register();
        verify(gui).showAlert(multiple,
            empty);
    }

    //8 fft
    @Test
    void registerThreeEqualsFalseFalseTrue() {
        Gui gui = mock(Gui.class);
        LoginController loginController = new LoginController();
        loginController.gui = gui;
        when(gui.getText(any())).thenReturn("v")
            .thenReturn("v").thenReturn("q").thenReturn("f").thenReturn("");
        loginController.register();
        verify(gui).showAlert(multiple,
            empty);
    }

    @Test
    void goBackLoginTest() {
        try {
            Gui gui = mock(Gui.class);
            LoginController loginController = new LoginController();
            loginController.gui = gui;
            doNothing().when(gui).switchScene("whatever");
            loginController.goBackLogin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
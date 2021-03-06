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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("PMD.BeanMembersShouldSerialize")
class LoginControllerTest {

    private String multiple = "One or multiple fields have not been filled in!";
    private String empty = "Empty field(s)";
    private LoginController loginController;
    private DBconnect database;

    @BeforeEach
    void setUp() {
        loginController = new LoginController();
        database = mock(DBconnect.class);
        loginController.setDatabase(database);
    }

    @Test
    void clickRegisterTest() {
        try {
            Gui gui = mock(Gui.class);
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
        loginController.gui = gui;
        when(gui.getText(any())).thenReturn("");
        loginController.login();
        verify(gui).showWarningAlert(any(), eq(empty));
    }


    @Test
    void loginNameTruePassFalse() throws IOException {
        Gui gui = mock(Gui.class);
        loginController.gui = gui;
        when(gui.getText(any())).thenReturn("").thenReturn(" ");
        loginController.login();
        verify(gui).showWarningAlert(any(), eq(empty));
    }

    @Test
    void loginNameFalsePassTrue() throws IOException {
        Gui gui = mock(Gui.class);
        loginController.gui = gui;
        when(gui.getText(any())).thenReturn(" ").thenReturn("");
        loginController.login();
        verify(gui).showWarningAlert(any(), eq(empty));
    }

    @Test
    void loginDatabaseTrue() throws IOException {
        Gui gui = mock(Gui.class);
        loginController.setManager(mock(SessionManager.class));
        loginController.gui = gui;
        when(database.authenticate("hey", "hey", null)).thenReturn(true);
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
        loginController.gui = gui;
        when(database.authenticate("bye", "bye", null)).thenReturn(false);
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
        loginController.gui = gui;
        when(gui.getText(any())).thenReturn("").thenReturn(" ");
        doNothing().when(gui).showWarningAlert("message", "title");
        loginController.register();
    }

    //1 tff
    @Test
    void registerThreeEqualsTrueFalseFalse() {
        Gui gui = mock(Gui.class);
        loginController.gui = gui;
        when(gui.getText(any())).thenReturn("k")
            .thenReturn("k").thenReturn("").thenReturn(" ").thenReturn(" ");
        loginController.register();
        verify(gui).showWarningAlert(multiple,
            empty);

    }

    //2 fff
    @Test
    void registerThreeEqualsFalseFalseFalse() {
        Gui gui = mock(Gui.class);
        loginController.gui = gui;
        when(gui.getText(any())).thenReturn("k")
            .thenReturn("k").thenReturn("y").thenReturn("y").thenReturn("y");
        when(gui.getText(any())).thenReturn("Kk").thenReturn("Kk");
        when(database.registerUser(gui.getText(any()),
            gui.getText(any()), null)).thenReturn(true);
        loginController.register();
        verify(gui).showAlert("Successfully registered.", "Success");
    }

    @Test
    void registerThreeEqualsFalseFalseFalse2() {
        Gui gui = mock(Gui.class);
        loginController.gui = gui;
        when(gui.getText(any())).thenReturn("k").thenReturn("k")
            .thenReturn("y").thenReturn("y").thenReturn("y");
        when(gui.getText(any())).thenReturn("Kk").thenReturn("Kk");
        when(database.registerUser(gui.getText(any()),
            gui.getText(any()), null)).thenReturn(false);
        loginController.register();
        verify(gui).showWarningAlert("Username already taken!", "Something went wrong");
    }

    //3 ftf
    @Test
    void registerThreeEqualsFalseTrueFalse() {
        Gui gui = mock(Gui.class);
        loginController.gui = gui;
        when(gui.getText(any())).thenReturn("k").thenReturn("k")
            .thenReturn("y").thenReturn("").thenReturn("y");
        loginController.register();
        verify(gui).showWarningAlert(multiple,
            empty);
    }

    //4 ftf
    @Test
    void registerThreeEqualsTrueTrueFalse() {
        Gui gui = mock(Gui.class);
        loginController.gui = gui;
        when(gui.getText(any())).thenReturn("q").thenReturn("q").thenReturn("")
            .thenReturn("").thenReturn("y");
        loginController.register();
        verify(gui).showWarningAlert(multiple,
            empty);
    }

    //5 ttt
    @Test
    void registerThreeEqualsTrueTrueTrue() {
        Gui gui = mock(Gui.class);
        loginController.gui = gui;
        when(gui.getText(any())).thenReturn("q").thenReturn("q")
            .thenReturn("").thenReturn("").thenReturn("");
        loginController.register();
        verify(gui).showWarningAlert(multiple,
            empty);
    }

    //6 ftt
    @Test
    void registerThreeEqualsFalseTrueTrue() {
        Gui gui = mock(Gui.class);
        loginController.gui = gui;
        when(gui.getText(any())).thenReturn("a")
            .thenReturn("a").thenReturn("x").thenReturn("").thenReturn("");
        loginController.register();
        verify(gui).showWarningAlert(multiple,
            empty);
    }

    //7 tft
    @Test
    void registerThreeEqualsTrueFalseTrue() {
        Gui gui = mock(Gui.class);
        loginController.gui = gui;
        when(gui.getText(any())).thenReturn("l")
            .thenReturn("l").thenReturn("").thenReturn("f").thenReturn("");
        loginController.register();
        verify(gui).showWarningAlert(multiple,
            empty);
    }

    //8 fft
    @Test
    void registerThreeEqualsFalseFalseTrue() {
        Gui gui = mock(Gui.class);
        loginController.gui = gui;
        when(gui.getText(any())).thenReturn("v")
            .thenReturn("v").thenReturn("q").thenReturn("f").thenReturn("");
        loginController.register();
        verify(gui).showWarningAlert(multiple,
            empty);
    }

    @Test
    void goBackLoginTest() {
        try {
            Gui gui = mock(Gui.class);
            loginController.gui = gui;
            doNothing().when(gui).switchScene("whatever");
            loginController.goBackLogin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
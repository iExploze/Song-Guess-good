import app.LoginUseCaseFactory;
import app.Main;
import app.SignupUseCaseFactory;
import dataAccessObjects.spotifyAccessObjects.UserTopTracks;
import dataAccessObjects.spotifyAccessObjects.UserTopTracksObject;
import org.junit.BeforeClass;
import view.*;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import dataAccessObjects.UserStorage.FileUserDataAccessObject;
import entities.*;
import entities.Users.CommonUserFactory;
import entities.Users.User;
import interface_adapter.PlayState;
import interface_adapter.PlayViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.guess.GuessController;
import interface_adapter.guess.GuessPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import usecase.guess.GuessInputBoundary;
import usecase.guess.GuessInteractor;
import usecase.guess.GuessOutputBoundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;
public class LoginTests {
    LoginView lv;

    public LoginView getLogin() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);

        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;

        JPanel jp2 = (JPanel) jp.getComponent(0);

        LoginView lv = (LoginView) jp2.getComponent(1);

        return lv;
    }
    public JPasswordField getPasswordField() {
        JComponent panel = (JComponent) lv.getComponent(3);
        JPasswordField pwdField = (JPasswordField) panel.getComponent(1);
        return pwdField;
    }
    public JTextField getUsernameField() {
        JComponent panel = (JComponent) lv.getComponent(1);
        JTextField userField = (JTextField) panel.getComponent(1);
        return userField;
    }

    @Test
    public void TestPassword() {
        Main.main(null);
        lv = getLogin();
        JPasswordField pwdField = getPasswordField();
        JTextField usrField = getUsernameField();
        KeyEvent event = new KeyEvent(pwdField
                , // we are interacting with the pwdField
                KeyEvent.KEY_TYPED, //
                System.currentTimeMillis(), // say the event happened right now
                0, // no modifiers
                KeyEvent.VK_UNDEFINED, // for KEY_TYPED, the KeyCode is undefined per documentation
                'y'); // the character that is being typed
        lv.dispatchEvent(event);
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        KeyEvent eventRight = new KeyEvent(
                pwdField,
                KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_RIGHT,
                KeyEvent.CHAR_UNDEFINED
        );
        lv.dispatchEvent(eventRight);
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        KeyEvent event2 = new KeyEvent(
                pwdField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'z');
        lv.dispatchEvent(event2);
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals("yz", new String(pwdField.getPassword()));

    }

    @Test
    public void TestLogin() {
        Main.main(null);
        lv = getLogin();
        JPasswordField pwdField = getPasswordField();
        JTextField usrField = getUsernameField();
        KeyEvent event = new KeyEvent(usrField
                , // we are interacting with the pwdField
                KeyEvent.KEY_TYPED, //
                System.currentTimeMillis(), // say the event happened right now
                0, // no modifiers
                KeyEvent.VK_UNDEFINED, // for KEY_TYPED, the KeyCode is undefined per documentation
                'y'); // the character that is being typed
        lv.dispatchEvent(event);
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        KeyEvent eventRight = new KeyEvent(
                usrField,
                KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_RIGHT,
                KeyEvent.CHAR_UNDEFINED
        );
        lv.dispatchEvent(eventRight);
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        KeyEvent event2 = new KeyEvent(
                usrField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'z');
        lv.dispatchEvent(event2);
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals("yz", new String(usrField.getText()));

    }





}

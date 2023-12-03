import app.LoginUseCaseFactory;
import app.SignupUseCaseFactory;
import dataAccessObjects.UserStorage.FileUserDataAccessObject;
import dataAccessObjects.spotifyAccessObjects.UserTopTracks;
import dataAccessObjects.spotifyAccessObjects.UserTopTracksObject;
import entities.*;
import entities.Users.CommonUserFactory;
import entities.Users.User;
import interface_adapter.PlayViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.guess.GuessController;
import interface_adapter.guess.GuessPresenter;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import org.apache.commons.logging.Log;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import usecase.guess.GuessInputBoundary;
import usecase.guess.GuessInteractor;
import usecase.guess.GuessOutputBoundary;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

public class LoginEndTesting {
    static LoginView loginView;
    static LoginViewModel loginViewModel;
    static SignupView signupView;
    static FileUserDataAccessObject userDataAccessObject;
    static CommonUserFactory commonUserFactory;
    static PlayView playView;
    static JPanel views;
    static ViewManagerModel viewManagerModel;
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
        JComponent panel = (JComponent) loginView.getComponent(3);
        JPasswordField pwdField = (JPasswordField) panel.getComponent(1);
        return pwdField;
    }
    public JTextField getUsernameField() {
        JComponent panel = (JComponent) loginView.getComponent(1);
        JTextField userField = (JTextField) panel.getComponent(1);
        return userField;
    }
    public JButton getLoginButton() {
        JPanel panel = (JPanel) loginView.getComponent(5);
        JButton JButton = (JButton) panel.getComponent(0);
        return JButton;
    }
    public JButton getSignUpButton() {
        JPanel panel = (JPanel) loginView.getComponent(5);
        JButton JButton = (JButton) panel.getComponent(1);
        return JButton;
    }
    public TextFieldSuggestion getTextField() {
        JPanel jp3 = (JPanel) playView.getComponent(1); //Guess info
        TextFieldSuggestion textField = (TextFieldSuggestion) jp3.getComponent(1); // should be  TextField
        return textField;
    }
    public JTextField getSignupUsernameField() {
        JPanel panel = (JPanel) signupView.getComponent(1);
        JTextField userField = (JTextField) panel.getComponent(1);
        return userField;
    }
    @BeforeClass
    public static void init() {
        JFrame application = new JFrame("Song Playback Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        views = new JPanel(cardLayout);
        application.add(views);

        viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // Assuming PlayController is correctly implemented and has the required methods

        commonUserFactory= new CommonUserFactory();
        User user = commonUserFactory.createUser("a","b");



        Player player = new SinglePlayer(user);
        Quiz quiz = new PlaylistQuiz(player);
        Playlist playlist = new SpotifyPlaylist();
        UserTopTracks userTopTracks = new UserTopTracksObject();

        PlayViewModel playViewModel = new PlayViewModel();



        GuessOutputBoundary guessOutputBoundary = new GuessPresenter(viewManagerModel, playViewModel);
        GuessInputBoundary guessInputBoundary = new GuessInteractor(guessOutputBoundary, quiz);
        GuessController guessController = new GuessController(guessInputBoundary);



        // Pass the timerController to the PlayView
        playView = new PlayView(playViewModel, guessController);


        loginViewModel = new LoginViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();

        try {
            userDataAccessObject = new FileUserDataAccessObject("./users.csv", new CommonUserFactory());
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }


        signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel,
                userDataAccessObject, playViewModel, quiz, userTopTracks, playlist);



        loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, userDataAccessObject,
                playViewModel, signupViewModel, quiz, userTopTracks, playlist);



        views.add(playView, PlayView.viewName);
        // Here, the viewName is a public static final String field in the PlayView class
        views.add(loginView, loginView.viewName);

        views.add(signupView, signupView.viewName);
        //viewManagerModel.setActiveView(UAuthView.viewName);
        viewManagerModel.setActiveView(loginView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        // Set the size of the window
        application.setLocationRelativeTo(null); // Center the window
        application.setVisible(true);

    }

    @Test
    public void testLogin() throws AWTException, InterruptedException {
        Robot robot = new Robot();
        JTextField userField = getUsernameField();
        JPasswordField passField = getPasswordField();
        userField.requestFocus();
        robot.keyPress(KeyEvent.VK_A);
        robot.keyPress(KeyEvent.VK_B);
        sleep(1000);
        assertEquals(userField.getText(), "ab");
        passField.requestFocus();
        robot.keyPress(KeyEvent.VK_A);
        robot.keyPress(KeyEvent.VK_B);
        sleep(500);
        assertEquals(passField.getText(), "ab");
        JButton login = getLoginButton();
        login.doClick();
        sleep(500);
        LoginState loginState = loginViewModel.getState();
        assertEquals(loginState.getUsernameError(), "ab: Account does not exist.");
        User user = commonUserFactory.createUser("ab", "abc");
        userDataAccessObject.save(user);
        login.doClick();
        sleep(500);
        loginState = loginViewModel.getState();
        assertEquals(loginState.getUsernameError(), "Incorrect password for ab.");
        robot.keyPress(KeyEvent.VK_C);
        login.doClick();
        sleep(2000);
        assertTrue(getTextField().isVisible());

    }

    @Test
    public void TestSignupSwitch() throws AWTException {
        JButton signUp = getSignUpButton();
        signUp.doClick();
        assertTrue(getSignupUsernameField().isVisible());

    }



}

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
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import org.junit.Before;
import org.junit.Test;
import view.LoginView;
import view.SignupView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
public class TestLoginSIgnupView {
    @Before
    public void init() {
        JFrame application = new JFrame("Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.
        LoginViewModel loginViewModel = new LoginViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        PlayViewModel playViewModel = new PlayViewModel();
        Playlist playlist = new SpotifyPlaylist();
        UserTopTracks userTopTracks = new UserTopTracksObject();

        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./users.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CommonUserFactory commonUserFactory= new CommonUserFactory();
        User user = commonUserFactory.createUser("a","b");
        Player player = new SinglePlayer(user);
        Quiz quiz = new PlaylistQuiz(player);

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject, playViewModel, quiz, userTopTracks, playlist);

        views.add(signupView, signupView.viewName);


        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, userDataAccessObject, playViewModel, signupViewModel, quiz, userTopTracks, playlist);
        views.add(loginView, loginView.viewName);


        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
    @Test
    public void TestBuild() {

    }


}

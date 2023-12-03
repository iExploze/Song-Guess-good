// Main.java
package app;


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
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import usecase.guess.GuessInputBoundary;
import usecase.guess.GuessInteractor;
import usecase.guess.GuessOutputBoundary;
import view.LoginView;
import view.PlayView;
import view.SignupView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        JFrame application = new JFrame("Song Playback Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // Assuming PlayController is correctly implemented and has the required methods

        CommonUserFactory commonUserFactory= new CommonUserFactory();
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
        PlayView playView = new PlayView(playViewModel, guessController);


        LoginViewModel loginViewModel = new LoginViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();

        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./users.csv", new CommonUserFactory());
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }


        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel,
                userDataAccessObject, playViewModel, quiz, userTopTracks, playlist);



        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, userDataAccessObject,
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
}

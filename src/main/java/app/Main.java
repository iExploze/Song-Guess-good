// Main.java
package app;


import dataAccessObjects.UserStorage.FileUserDataAccessObject;
import entities.*;
import entities.PlaylistQuiz;
import entities.Quiz;
import entities.SinglePlayer;
import entities.Users.CommonUserFactory;
import entities.Users.User;
import interface_adapter.PlayViewModel;
import interface_adapter.UAuth.UAuthController;
import interface_adapter.UAuth.UAuthPresenter;
import interface_adapter.UAuth.UAuthViewModel;
import interface_adapter.ViewManagerModel;

import interface_adapter.PlayViewModel;
import interface_adapter.guess.GuessController;
import interface_adapter.guess.GuessPresenter;
import interface_adapter.login.LoginViewModel;


import interface_adapter.play_song.PlayPresenter;
import interface_adapter.score.ScoreController;
import interface_adapter.score.ScorePresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.skip_song.SkipController;
import interface_adapter.skip_song.SkipPresenter;
import interface_adapter.skip_song.SkipViewModel;
import interface_adapter.timer.TimerController;
import interface_adapter.timer.TimerPresenter;
import usecase.Skip.SkipInputBoundary;
import usecase.Skip.SkipInteractor;
import usecase.Skip.SkipOutputBoundary;
import usecase.UserAuth.UAuthInputBoundary;
import usecase.UserAuth.UAuthInteractor;
import usecase.UserAuth.UAuthOutputBoundary;
import usecase.UserAuth.UAuthOutputData;
import usecase.guess.GuessInputBoundary;
import usecase.guess.GuessInteractor;
import usecase.guess.GuessOutputBoundary;
import usecase.play_song.PlayInputBoundary;
import usecase.play_song.PlayInteractor;
import usecase.play_song.PlayOutputBoundary;
import usecase.play_song.PlayUserDataAccessInterface;
import usecase.score.ScoreInputBoundary;
import usecase.score.ScoreInteractor;
import usecase.score.ScoreOutputBoundary;
import usecase.timer.*;

import view.*;
import interface_adapter.play_song.PlayController;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        JFrame application = new JFrame("Song Playback Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        CommonUserFactory commonUserFactory= new CommonUserFactory();
        User user = commonUserFactory.createUser("a","b");

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);
        PlayViewModel playViewModel = new PlayViewModel();
        SkipViewModel skipViewModel = new SkipViewModel();

        Player player = new SinglePlayer(user);
        Quiz quiz = new PlaylistQuiz(player);

        // Test End
        UAuthOutputData uAuthOutputData = new UAuthOutputData();

        UAuthViewModel uAuthViewModel = new UAuthViewModel();
        UAuthOutputBoundary uAuthOutputBoundary = new UAuthPresenter(uAuthOutputData, uAuthViewModel);
        UAuthInputBoundary uAuthInputBoundary = new UAuthInteractor(uAuthOutputBoundary, uAuthOutputData);
        UAuthController uAuthController = new UAuthController(uAuthInputBoundary);
        UAuthView uAuthView = new UAuthView(uAuthController, uAuthViewModel);
        LoginViewModel loginViewModel = new LoginViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();

        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./users.csv", new CommonUserFactory());
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }


        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject, playViewModel, quiz);



        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, userDataAccessObject, playViewModel, signupViewModel, quiz);


        PlayUserDataAccessInterface playUserDataAccessObject = new SongData();
        PlayView playView = PlayUseCaseFactory.create(viewManagerModel, playViewModel, skipViewModel,
                playUserDataAccessObject, quiz);

        views.add(playView, PlayView.viewName);
        // Here, the viewName is a public static final String field in the PlayView class
        views.add(loginView, loginView.viewName);

        views.add(uAuthView, UAuthView.viewName);
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

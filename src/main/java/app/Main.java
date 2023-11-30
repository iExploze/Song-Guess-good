// Main.java
package app;

import entities.Player;
import entities.PlaylistQuiz;
import entities.Quiz;
import entities.SinglePlayer;
import entities.Users.CommonUserFactory;
import entities.Users.User;
import interface_adapter.UAuth.UAuthController;
import interface_adapter.UAuth.UAuthPresenter;
import interface_adapter.UAuth.UAuthViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.skip_song.SkipController;
import interface_adapter.skip_song.SkipPresenter;
import interface_adapter.skip_song.SkipViewModel;
import usecase.Skip.SkipInputBoundary;
import usecase.Skip.SkipInteractor;
import usecase.Skip.SkipOutputBoundary;
import usecase.UserAuth.UAuthInputBoundary;
import usecase.UserAuth.UAuthInteractor;
import usecase.UserAuth.UAuthOutputBoundary;
import usecase.UserAuth.UAuthOutputData;
import view.PlayView;
import interface_adapter.play_song.PlayController;
import view.UAuthView;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        JFrame application = new JFrame("Song Playback Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();

        // Assuming PlayController is correctly implemented and has the required methods
        PlayController playController = new PlayController();

        CommonUserFactory commonUserFactory= new CommonUserFactory();
        User user = commonUserFactory.createUser("a","b");



        Player player = new SinglePlayer(user);
        Quiz quiz = new PlaylistQuiz(player);

        SkipViewModel skipViewModel = new SkipViewModel();
        SkipOutputBoundary skipOutputBoundary = new SkipPresenter(skipViewModel);
        SkipInputBoundary skipInputBoundary = new SkipInteractor(quiz, skipOutputBoundary);
        SkipController skipController = new SkipController(skipInputBoundary);
        PlayView playView = new PlayView(playController, skipController);

        UAuthOutputData uAuthOutputData = new UAuthOutputData();

        UAuthViewModel uAuthViewModel = new UAuthViewModel();
        UAuthOutputBoundary uAuthOutputBoundary = new UAuthPresenter(uAuthOutputData, uAuthViewModel);
        UAuthInputBoundary uAuthInputBoundary = new UAuthInteractor(uAuthOutputBoundary, uAuthOutputData);
        UAuthController uAuthController = new UAuthController(uAuthInputBoundary);
        UAuthView uAuthView = new UAuthView(uAuthController, uAuthViewModel);

        // Here, the viewName is a public static final String field in the PlayView class
        views.add(playView, PlayView.viewName);
        views.add(uAuthView, UAuthView.viewName);

        //viewManagerModel.setActiveView(UAuthView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setSize(new Dimension(800, 600)); // Set the size of the window
        application.setLocationRelativeTo(null); // Center the window
        application.setVisible(true);
    }
}

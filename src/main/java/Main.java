// Main.java
package app;

import entities.Player;
import entities.PlaylistQuiz;
import entities.Quiz;
import entities.SinglePlayer;
import interface_adapter.ViewManagerModel;
import interface_adapter.skip_song.SkipController;
import interface_adapter.skip_song.SkipPresenter;
import interface_adapter.skip_song.SkipViewModel;
import usecase.Skip.SkipInputBoundary;
import usecase.Skip.SkipInteractor;
import usecase.Skip.SkipOutputBoundary;
import view.PlayView;
import interface_adapter.play_song.PlayController;

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

        Player player = new SinglePlayer("tester");
        Quiz quiz = new PlaylistQuiz(player);

        SkipViewModel skipViewModel = new SkipViewModel();
        SkipOutputBoundary skipOutputBoundary = new SkipPresenter(viewManagerModel, skipViewModel);
        SkipInputBoundary skipInputBoundary = new SkipInteractor(quiz, skipOutputBoundary);
        SkipController skipController = new SkipController(skipInputBoundary);
        PlayView playView = new PlayView(playController, skipController);

        // Here, the viewName is a public static final String field in the PlayView class
        views.add(playView, PlayView.viewName);

        viewManagerModel.setActiveView(PlayView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setSize(new Dimension(800, 600)); // Set the size of the window
        application.setLocationRelativeTo(null); // Center the window
        application.setVisible(true);
    }
}

// Main.java
package app;

import interface_adapter.ViewManagerModel;
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
        PlayView playView = new PlayView(playController);

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

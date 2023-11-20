//import interface_adapter.play_song.PlayViewModel;
//import app.PlayUseCaseFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.play_song.PlayViewModel;
import view.PlayView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("Play Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

//        // This keeps track of and manages which view is currently showing.
//        ViewManagerModel viewManagerModel = new ViewManagerModel();
//        new ViewManager(views, cardLayout, viewManagerModel);
//
//        // The data for the views, such as username and password, are in the ViewModels.
//        // This information will be changed by a presenter object that is reporting the
//        // results from the use case. The ViewModels are observable, and will
//        // be observed by the Views.
//        PlayViewModel playViewModel = new PlayViewModel();
//
//        PlayView playView = PlayUseCaseFactory.create(viewManagerModel, playViewModel, );
//        views.add(playView, playView.viewName);
//
//        viewManagerModel.setActiveView(playView.viewName);
//        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
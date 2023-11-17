package interface_adapter.play_song;

import view.PlayView;
import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PlayViewModel {
    public static final String TITLE_LABEL = "PLay song view";
    public static final String PLAY_BUTTON_LABEL = "PLAY";
    public static final String STOP_BUTTON_LABEL = "STOP";
    public static final String SUBMIT_BUTTON_LABEL = "SUBMIT";

    private PlayState state = new PlayState();

    public PlayViewModel() {
    }

    public void setState(PlayState state) {
        this.state = state;
    }

    public void addPropertyChangeListener(PlayView playView) {
    }
}

package interface_adapter.play_song;
import view.PlayView;

import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PlayViewModel extends ViewModel {
    public static final String TITLE_LABEL = "PLay song view";
    public static final String PLAY_BUTTON_LABEL = "PLAY";
    public static final String STOP_BUTTON_LABEL = "STOP";
    public static final String GUESS_BUTTON_LABEL = "GUESS";
    public static final String ANSWER_LABEL = "Input your guess";

    private PlayState state = new PlayState();

    public PlayViewModel() {
        super("");
    }

    public void setState(PlayState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() { support.firePropertyChange("state", null, this.state); }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public PlayState getState() {
        return state;
    }
}

package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PlayViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Play song view";
    public static final String PLAY_BUTTON_LABEL = "PLAY";
    public static final String STOP_BUTTON_LABEL = "STOP";
    public static final String GUESS_BUTTON_LABEL = "GUESS";
    public static final String ANSWER_LABEL = "Input your guess";

    private PlayState state = new PlayState();
    private int score;
    private int time; // New attribute for the timer

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public PlayViewModel() {
        super("PLAY_VIEW");
        this.score = 0;
        this.time = 0; // Initialize timer to 0
    }

    // Setters and Getters for score
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // Setters and Getters for time
    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    // PlayState related methods
    public PlayState getState() {
        return state;
    }

    public void setState(PlayState state) {
        this.state = state;
    }
    public void setSuggestions(PlayState state) {
        this.state = state;
        support.firePropertyChange("suggestion", null, this.state);
    }

    // PropertyChangeListener support methods
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    // Fire all property changes, this can be used to notify observers of all changes at once
    @Override
    public void firePropertyChanged() {
        // Notify about the score change
        // Notify about the state change
        support.firePropertyChange("state", null, this.state);

    }

    public void fireTimerChanged(){
        support.firePropertyChange("time", null, this.state);
    }
}

package interface_adapter;

import entities.Quiz;
import entities.Song;

import java.util.List;

public class PlayState {
    private int score;
    private int time;
    private Song song;
    private String guess = "";
    private String incorrectGuessMessage = null;
    private List<String> suggestions;

    // List of stuff that needs to be updated on the playview
    // Score
    // Current Song Playing #name + playing thing
    // AutoCorrectDatabase
    public PlayState(){}



    public void setGuess(){};
    public void setSong(Song song){
        this.song = song;
    };
    public Song getSong(){
        return this.song;
    }

    public void setSuggestions(Quiz quiz) {
        this.suggestions = quiz.getSuggestions();
    }

    public List<String> getSuggestions() {
        return suggestions;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setTime(int time)
    {
        this.time = time;
    }

    public int getTime()
    {
        return time;
    }
}

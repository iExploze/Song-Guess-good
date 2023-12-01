package interface_adapter;

import entities.Quiz;

import java.util.List;

public class PlayState {
    private int score;
    private String song;
    private String guess = "";
    private String incorrectGuessMessage = null;
    private List<String> suggestions;

    // List of stuff that needs to be updated on the playview
    // Score
    // Current Song Playing #name + playing thing
    // AutoCorrectDatabase
    public PlayState(){}


    public void setGuess(){};
    //ublic void setScore();


    public void setSuggestions(Quiz quiz) {
        this.suggestions = quiz.getSuggestions();
    }

    public List<String> getSuggestions() {
        return suggestions;
    }
}

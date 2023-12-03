package interface_adapter;

import java.util.List;

public class PlayState {
    private int score;
    private String songURL;
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
    public void setSong(String songurl){
        this.songURL = songurl;
    };
    public String getSong(){
        return this.songURL;
    }

    public void setSuggestions(List<String> suggestions) {
        this.suggestions = suggestions;
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
}

package interface_adapter.play_song;

public class PlayState {
    private String guess = "";

    private String incorrectGuessMessage = null;

    public  PlayState(PlayState copy){
        guess = copy.guess;
        incorrectGuessMessage = copy.incorrectGuessMessage;
    }

    public PlayState(){}

    public String getGuess(){
        return guess;
    }

    public void setGuess(String guess){
        this.guess = guess;
    }

    public void setIncorrectGuess(String incorrectGuessMessage){
        this.incorrectGuessMessage = incorrectGuessMessage;
    }
}

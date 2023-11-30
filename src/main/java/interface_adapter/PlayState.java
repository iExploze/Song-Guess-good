package interface_adapter;

public class PlayState {
    private String guess = "";
    private String incorrectGuessMessage = null;

    public PlayState(PlayState copy){
        guess = copy.guess;
        incorrectGuessMessage = copy.incorrectGuessMessage;
    }

    public PlayState(){}

    public void setGuess(String guess){this.guess = guess;}

    public String getGuess(){return guess;}

}

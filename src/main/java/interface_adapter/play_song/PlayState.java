package interface_adapter.play_song;

public class PlayState {
    private String guess = "";

    public  PlayState(PlayState copy){
        guess = copy.guess;
    }

    public PlayState(){}

    public String getGuess(){
        return guess;
    }

    public void setGuess(String guess){
        this.guess = guess;
    }
}

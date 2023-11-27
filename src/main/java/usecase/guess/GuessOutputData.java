package usecase.guess;

public class GuessOutputData {
    private final String guess;

    private final boolean guessStatus;

    public GuessOutputData(String guess, boolean correctGuess) {
        this.guess = guess;
        this.guessStatus = correctGuess;
    }

    public String getGuess(){
        return guess;
    }

    public boolean getGuessStatus(){
        return guessStatus;
    }
}

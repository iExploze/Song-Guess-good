package usecase.guess;

public class GuessOutputData {
    private final String guess;

    private boolean correctGuess;

    public GuessOutputData(String guess, boolean correctGuess) {
        this.guess = guess;
        this.correctGuess = correctGuess;
    }

    public String getGuess(){
        return guess;
    }

    public boolean getGuessStatus(){
        return correctGuess;
    }
}

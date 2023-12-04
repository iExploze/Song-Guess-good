package usecase.guess;

public class GuessInputData {

    final private String guess;

    public GuessInputData(String guess) {
        this.guess = guess;
    }

    public String getGuess(){
        return guess;
    }
}

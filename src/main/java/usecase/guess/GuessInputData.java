package main.java.usecase.guess;

public class GuessInputData {

    final private String guess;

    public GuessInputData(String guess) {
        this.guess = guess;
    }

    String getGuess(){
        return guess;
    }
}

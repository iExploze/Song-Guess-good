package main.java.usecase.guess;

public interface GuessOutputBoundary {
    void prepareSuccessView(GuessOutputBoundary correctGuess);

    void prepareFailView(String incorrectGuess);
}
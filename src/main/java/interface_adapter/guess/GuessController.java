package interface_adapter.guess;

import usecase.guess.GuessInputBoundary;
import usecase.guess.GuessInputData;

public class GuessController {

    final GuessInputBoundary playerGuessUseCaseInteractor;

    public GuessController(GuessInputBoundary playerGuessUseCaseInteractor) {
        this.playerGuessUseCaseInteractor = playerGuessUseCaseInteractor;
    }

    public void execute(String guess){
        GuessInputData guessInputData = new GuessInputData(guess);

        playerGuessUseCaseInteractor.execute(guessInputData);
    }
}

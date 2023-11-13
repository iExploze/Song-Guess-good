package main.java.usecase.guess;

import main.java.dataAccessObjects.GuessDataAccessInterface;

public class GuessInteractor implements GuessInputBoundary{
    final GuessDataAccessInterface guessDataAccessObject;

    final GuessOutputBoundary guessPresenter;

    public GuessInteractor(GuessDataAccessInterface guessDataAccessInterface,
                           GuessOutputBoundary guessOutputBoundary){
        this.guessDataAccessObject = guessDataAccessInterface;
        this.guessPresenter = guessOutputBoundary;
    }

    @Override
    public void execute(GuessInputData guessInputData) {
        //if the user's guess is the same as the song name accessed using the DAO
            //prepare successView
            //successView might involve adding a point to the user's score
        //else if it's not the same
            //prepare failView
        //else
            //skip button?
    }
}

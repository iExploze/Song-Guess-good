package usecase.guess;

import entities.Quiz;
import entities.Song;

public class GuessInteractor implements GuessInputBoundary{
    final GuessOutputBoundary guessPresenter;
    final Quiz quiz;

    public GuessInteractor(GuessOutputBoundary guessOutputBoundary,
                           Quiz quiz){
        this.guessPresenter = guessOutputBoundary;
        this.quiz = quiz;
    }

    @Override
    public void execute(GuessInputData guessInputData) {
        String songName = quiz.currentPlaying().getSongName();
        String playerGuess = guessInputData.getGuess();
        boolean guessStatus = songName.equals(playerGuess);

        if (guessStatus){
            GuessOutputData guessOutputData = new GuessOutputData(playerGuess, songName);
            quiz.addPoints();
            quiz.goNext();
            guessPresenter.prepareSuccessView(guessOutputData);
        }else{
            guessPresenter.prepareFailView(songName);
        }
    }
}
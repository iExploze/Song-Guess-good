package usecase.guess;

import entities.Quiz;
import entities.Song;

public class GuessInteractor implements GuessInputBoundary{
    final GuessOutputBoundary guessPresenter;
    final Quiz quiz;

    public GuessInteractor(GuessOutputBoundary guessOutputBoundary, Quiz quiz){
        this.guessPresenter = guessOutputBoundary;
        this.quiz = quiz;
    }

    @Override
    public void execute(GuessInputData guessInputData) {
        String oldSongName = quiz.currentPlaying().getSongName();
        String playerGuess = guessInputData.getGuess();
        boolean guessStatus = oldSongName.equals(playerGuess);
        quiz.goNext();
        Song song = quiz.currentPlaying();
        if (guessStatus){
            quiz.addPoints();
            int score = quiz.getPoints();
            GuessOutputData guessOutputData = new GuessOutputData(playerGuess, oldSongName, song, score);

            guessPresenter.prepareSuccessView(guessOutputData);
        }else{
            int score = quiz.getPoints();
            guessPresenter.prepareFailView(oldSongName, song, score);
        }
    }
}
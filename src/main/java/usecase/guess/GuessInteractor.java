package usecase.guess;

import entities.Quiz;
import entities.Song;

public class GuessInteractor implements GuessInputBoundary{
    final GuessOutputBoundary guessPresenter;
    final Song song;
    Quiz quiz;

    public GuessInteractor(GuessOutputBoundary guessOutputBoundary,
                           Song song, Quiz quiz){
        this.guessPresenter = guessOutputBoundary;
        this.song = song;
        this.quiz = quiz;
    }

    @Override
    public void execute(GuessInputData guessInputData) {
        String songName = song.getSongName();
        String playerGuess = guessInputData.getGuess();
        boolean guessStatus = songName.equals(playerGuess);

        if (guessStatus){
            GuessOutputData guessOutputData = new GuessOutputData(playerGuess, guessStatus);
            guessPresenter.prepareSuccessView(guessOutputData);
        }else{
            guessPresenter.prepareFailView("Incorrect guess");
        }

        quiz.goNext(); // progress to the next song
    }
}
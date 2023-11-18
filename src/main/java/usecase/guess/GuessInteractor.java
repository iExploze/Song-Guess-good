package usecase.guess;

import entities.Song;

public class GuessInteractor implements GuessInputBoundary{
    final GuessOutputBoundary guessPresenter;
    final Song song;

    public GuessInteractor(GuessOutputBoundary guessOutputBoundary,
                           Song song){
        this.guessPresenter = guessOutputBoundary;
        this.song = song;
    }

    @Override
    public void execute(GuessInputData guessInputData) {
        String songName = song.getSongName();
        String playerGuess = guessInputData.getGuess();
        boolean guessStatus = songName.equals(playerGuess);

        GuessOutputData guessOutputData = new GuessOutputData(playerGuess, guessStatus);
        guessPresenter.prepareSuccessView(guessOutputData);
    }
}

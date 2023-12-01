package usecase.guess;

import entities.Song;

public interface GuessOutputBoundary {
    void prepareSuccessView(GuessOutputData correctGuess);

    void prepareFailView(String oldSongName, Song newSong, int score);
}

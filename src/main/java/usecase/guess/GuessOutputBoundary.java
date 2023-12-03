package usecase.guess;

public interface GuessOutputBoundary {
    void prepareSuccessView(GuessOutputData correctGuess);

    void prepareFailView(String oldSongName, String newSong, int score);
}

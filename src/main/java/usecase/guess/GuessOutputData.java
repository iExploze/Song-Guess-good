package usecase.guess;

public class GuessOutputData {
    private final String guess;
    private final String correctSong;

    public GuessOutputData(String guess, String correctSong) {
        this.guess = guess;
        this.correctSong = correctSong;
    }

    public String getGuess(){
        return guess;
    }

    public String getCorrectSong(){
        return correctSong;
    }
}

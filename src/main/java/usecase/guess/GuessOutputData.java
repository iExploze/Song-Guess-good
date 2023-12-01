package usecase.guess;

import entities.Song;

public class GuessOutputData {
    private final String guess;
    private final String oldSongName;
    private final Song song;
    private int score;

    public GuessOutputData(String guess, String oldSongName, Song song, int score) {
        this.guess = guess;
        this.song = song;
        this.oldSongName = oldSongName;
        this.score = score;
    }

    public String getGuess(){
        return guess;
    }

    public int getScore() {
        return score;
    }

    public String getCorrectSong(){
        return this.oldSongName;
    }
    public Song getNewSong(){return song;}
}

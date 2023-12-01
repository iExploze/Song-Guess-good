package usecase.play_song;

import entities.Player;
import entities.PlayerFactory;
import entities.Quiz;
import entities.Song;

public class PlayInteractor implements PlayInputBoundary{
    final Quiz quiz;
    final PlayOutputBoundary playPresenter;

    public PlayInteractor(Quiz quiz, PlayOutputBoundary playOutputBoundary) {
        this.quiz = quiz;
        this.playPresenter = playOutputBoundary;
    }

    @Override
    public void PlaySong() {
        if(quiz.currentPlaying() != null)
            this.quiz.currentPlaying().playSong();
    }

    @Override
    public void StopSong() {
        this.quiz.currentPlaying().stopSong();
    }

}

package usecase.play_song;

import entities.Player;
import entities.PlayerFactory;
import entities.Quiz;
import entities.Song;

public class PlayInteractor implements PlayInputBoundary{
    final PlayUserDataAccessInterface playUserDataAccessInterface;
    final PlayOutputBoundary playPresenter;

    public PlayInteractor(PlayUserDataAccessInterface playUserDataAccessInterface, PlayOutputBoundary playOutputBoundary) {
        this.playUserDataAccessInterface = playUserDataAccessInterface;
        this.playPresenter = playOutputBoundary;
    }

    @Override
    public void PlaySong() {

    }

    @Override
    public void StopSong() {

    }

}


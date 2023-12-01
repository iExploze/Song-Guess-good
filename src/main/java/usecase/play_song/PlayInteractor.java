package usecase.play_song;

import entities.Player;
import entities.PlayerFactory;
import entities.Song;

public class PlayInteractor implements PlayInputBoundary {
    final PlayUserDataAccessInterface userDataAccessObject;
    final PlayOutputBoundary playPresenter;

    public PlayInteractor(PlayUserDataAccessInterface userDataAccessInterface,
                          PlayOutputBoundary playOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.playPresenter = playOutputBoundary;
    }

    public void execute() {
        userDataAccessObject.playSong();
    }
}


package usecase.play_song;

import entities.Player;
import entities.PlayerFactory;
import entities.Song;

public class PlayInteractor implements PlayInputBoundary{
    final PlayUserDataAccessInterface userDataAccessObject;
    final PlayOutputBoundary playPresenter;
    final PlayerFactory playerFactory;

    public PlayInteractor(PlayUserDataAccessInterface userDataAccessInterface,
                          PlayOutputBoundary playOutputBoundary,
                          PlayerFactory playerFactory) {
        this.userDataAccessObject = userDataAccessInterface;
        this.playPresenter = playOutputBoundary;
        this.playerFactory = playerFactory;
    }

    public void execute(PlayInputData playInputData) {
        // check if the song preview exists, could be null
        String name = playInputData.getTitle();
        if (userDataAccessObject.existSong(name)) {
            userDataAccessObject.playSong(userDataAccessObject.getSong(name));
        }
        playPresenter.prepareSuccessView();
    }
}

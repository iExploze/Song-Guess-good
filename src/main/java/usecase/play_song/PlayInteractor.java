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

    public void execute() {
        if (userDataAccessObject.existSongPreview()) {
            for (String url: userDataAccessObject.existing_Previews()){

            }

        }
        playPresenter.prepareSucessView();
    }
}

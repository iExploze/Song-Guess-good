package interface_adapter.play_song;

import usecase.play_song.PlayInputBoundary;

public class PlayController {
    PlayInputBoundary userPlayUseCaseInteractor;

    public PlayController() {
    }
    public void execute() {
        userPlayUseCaseInteractor.execute();
    }
}

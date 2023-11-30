package interface_adapter.play_song;

import interface_adapter.PlayState;
import interface_adapter.PlayViewModel;
import interface_adapter.ViewManagerModel;
import usecase.play_song.PlayOutputBoundary;

public class PlayPresenter implements PlayOutputBoundary {
    private final PlayViewModel playViewModel;
    private ViewManagerModel viewManagerModel;

    public PlayPresenter(ViewManagerModel viewManagerModel,
                         PlayViewModel playViewModel) {
        this.playViewModel = playViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView() {
        PlayState playState = playViewModel.getState();
        this.playViewModel.setState(playState);
        playViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(viewManagerModel.getActiveView());
        viewManagerModel.firePropertyChanged();
    }
}

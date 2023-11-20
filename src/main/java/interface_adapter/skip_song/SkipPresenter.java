package interface_adapter.skip_song;

import interface_adapter.ViewManagerModel;
import usecase.Skip.SkipOutputBoundary;
import view.ViewManager;

public class SkipPresenter implements SkipOutputBoundary {

    private ViewManagerModel viewManagerModel;
    private SkipViewModel skipViewModel;

    public SkipPresenter(ViewManagerModel viewManagerModel, SkipViewModel skipViewModel)
    {
        this.skipViewModel = skipViewModel;
    }
    @Override
    public void prepareView(String name) {

    }
}

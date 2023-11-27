package interface_adapter.guess;

import interface_adapter.ViewManagerModel;
import interface_adapter.play_song.PlayState;
import interface_adapter.play_song.PlayViewModel;
import usecase.guess.GuessOutputBoundary;
import usecase.guess.GuessOutputData;

import javax.swing.text.View;

public class GuessPresenter implements GuessOutputBoundary {
    private final PlayViewModel playViewModel;
    private ViewManagerModel viewManagerModel;

    public GuessPresenter(ViewManagerModel viewManagerModel,
                          PlayViewModel playViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.playViewModel = playViewModel;
    }

    @Override
    public void prepareSuccessView(GuessOutputData correctGuess) {
        PlayState playState = playViewModel.getState();
        this.playViewModel.setState(playState);
        playState.setGuess(correctGuess.getGuess());
        playViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(playViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String incorrectGuessMessage) {
        PlayState playState = playViewModel.getState();
        playState.setIncorrectGuess(incorrectGuessMessage);
        playViewModel.firePropertyChanged();
    }
}

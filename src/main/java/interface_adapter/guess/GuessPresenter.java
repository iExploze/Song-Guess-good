package interface_adapter.guess;

import interface_adapter.ViewManagerModel;
import interface_adapter.PlayState;
import interface_adapter.PlayViewModel;
import usecase.guess.GuessOutputBoundary;
import usecase.guess.GuessOutputData;

import javax.swing.*;
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

        String correctMessage = "Your guess was correct!";
        JOptionPane.showMessageDialog(null, correctMessage);

        playViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(playViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String songName) {
        String incorrectMessage = "Your guess was not correct. The current song playing is " + songName + ".";
        JOptionPane.showMessageDialog(null, incorrectMessage);

        playViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(playViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
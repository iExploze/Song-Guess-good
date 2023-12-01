package interface_adapter.guess;

import entities.Song;
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
        playState.setSong(correctGuess.getNewSong());
        playState.setScore(correctGuess.getScore());
        this.playViewModel.setState(playState);
        //playState.setGuess(correctGuess.getGuess());
        this.playViewModel.firePropertyChanged();
        String correctMessage = "Your guess was correct!";
        JOptionPane.showMessageDialog(null, correctMessage);

        //playViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(playViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String oldSongName, Song song, int score) {
        String incorrectMessage = "Your guess was not correct. The current song playing is " + oldSongName + ".";
        JOptionPane.showMessageDialog(null, incorrectMessage);


        //update song on play view
        PlayState playState = playViewModel.getState();
        playState.setSong(song);
        playState.setScore(score);
        this.playViewModel.setState(playState);

        playViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(playViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
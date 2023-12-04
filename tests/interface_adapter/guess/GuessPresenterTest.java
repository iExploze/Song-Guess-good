package interface_adapter.guess;

import interface_adapter.PlayState;
import interface_adapter.PlayViewModel;
import interface_adapter.ViewManagerModel;
import org.junit.Before;
import org.junit.Test;
import usecase.guess.GuessOutputData;

import static org.junit.Assert.*;

public class GuessPresenterTest {
    ViewManagerModel viewManagerModel;
    PlayViewModel playViewModel;
    GuessPresenter guessPresenter;
    @Before
    public void init(){
        this.viewManagerModel = new ViewManagerModel();
        this.playViewModel = new PlayViewModel();
        this.guessPresenter = new GuessPresenter(viewManagerModel, playViewModel);
    }

    @Test
    public void successTest() {
        // test data
        GuessOutputData guessOutputData = new GuessOutputData("old song", "old song", "next song", 0);
        guessPresenter.prepareSuccessView(guessOutputData);

        // check for changes in playState
        PlayState playState = playViewModel.getState();
        assertEquals(guessOutputData.getNewSong(), playState.getSong());
        assertEquals(guessOutputData.getScore(), playState.getScore());

        // check view stays on play view
        assertEquals(viewManagerModel.getActiveView(), playViewModel.getViewName());

    }

    @Test
    public void failTest() {
        // test data
        String oldSongName = "old song";
        String songURL = "url";
        int score = 0;
        guessPresenter.prepareFailView(oldSongName, songURL, score);

        // check for changes in playState
        PlayState playState = playViewModel.getState();
        assertEquals(score, playState.getScore());
        assertEquals(songURL, playState.getSong());

        // check view stays on play view
        assertEquals(viewManagerModel.getActiveView(), playViewModel.getViewName());

    }

}
package app;

import entities.Quiz;
import interface_adapter.ViewManagerModel;
import interface_adapter.PlayViewModel;
import interface_adapter.guess.GuessController;
import interface_adapter.guess.GuessPresenter;
import interface_adapter.play_song.PlayController;
import interface_adapter.play_song.PlayPresenter;
import interface_adapter.score.ScoreController;
import interface_adapter.skip_song.SkipController;
import interface_adapter.skip_song.SkipPresenter;
import interface_adapter.skip_song.SkipViewModel;
import interface_adapter.timer.TimerController;
import usecase.Skip.SkipOutputBoundary;
import usecase.guess.GuessInputBoundary;
import usecase.guess.GuessInteractor;
import usecase.guess.GuessOutputBoundary;
import usecase.play_song.PlayInputBoundary;
import usecase.play_song.PlayInteractor;
import usecase.play_song.PlayOutputBoundary;
import usecase.play_song.PlayUserDataAccessInterface;
import view.PlayView;

public class PlayUseCaseFactory {

    /** Prevent instantiation. */
    private PlayUseCaseFactory() {}

    public static PlayView create(
            ViewManagerModel viewManagerModel, PlayViewModel playViewModel,
            PlayUserDataAccessInterface playUserDataAccessInterface, Quiz quiz) {
        PlayController playController = createUsersPlayCase(viewManagerModel, playViewModel, playUserDataAccessInterface);
        GuessController guessController = createUsersGuessCase(viewManagerModel, playViewModel, quiz);
        return new PlayView(playController, playViewModel, guessController);
    }

    private static PlayController createUsersPlayCase(
            ViewManagerModel viewManagerModel, PlayViewModel playViewModel,
            PlayUserDataAccessInterface playUserDataAccessInterface) {

        PlayOutputBoundary playOutputBoundary = new PlayPresenter(viewManagerModel, playViewModel);

        PlayInputBoundary userPlayInteractor = new PlayInteractor(
                playUserDataAccessInterface, playOutputBoundary);
        return new PlayController(userPlayInteractor);
    }

    private static GuessController createUsersGuessCase(
            ViewManagerModel viewManagerModel, PlayViewModel playViewModel, Quiz quiz) {

        GuessOutputBoundary guessOutputBoundary = new GuessPresenter(viewManagerModel, playViewModel);

        GuessInputBoundary userGuessInteractor = new GuessInteractor(
                guessOutputBoundary, quiz);
        return new GuessController(userGuessInteractor);

    }

    /*private static SkipController createUsersSkipCase(
            ViewManagerModel viewManagerModel, SkipViewModel skipViewModel) {

        SkipOutputBoundary skipOutputBoundary = new SkipPresenter(viewManagerModel, skipViewModel);

        GuessInputBoundary userGuessInteractor = new GuessInteractor(
                guessOutputBoundary, quiz);
        return new GuessController(userGuessInteractor);

    }*/
}

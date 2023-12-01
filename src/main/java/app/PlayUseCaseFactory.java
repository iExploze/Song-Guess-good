package app;

import entities.Quiz;
import interface_adapter.ViewManagerModel;
import interface_adapter.PlayViewModel;
import interface_adapter.guess.GuessController;
import interface_adapter.guess.GuessPresenter;
import interface_adapter.play_song.PlayController;
import interface_adapter.play_song.PlayPresenter;
import interface_adapter.score.ScoreController;
import interface_adapter.score.ScorePresenter;
import interface_adapter.skip_song.SkipController;
import interface_adapter.skip_song.SkipPresenter;
import interface_adapter.skip_song.SkipViewModel;
import interface_adapter.timer.TimerController;
import interface_adapter.timer.TimerPresenter;
import usecase.Skip.SkipInputBoundary;
import usecase.Skip.SkipInteractor;
import usecase.Skip.SkipOutputBoundary;
import usecase.guess.GuessInputBoundary;
import usecase.guess.GuessInteractor;
import usecase.guess.GuessOutputBoundary;
import usecase.play_song.PlayInputBoundary;
import usecase.play_song.PlayInteractor;
import usecase.play_song.PlayOutputBoundary;
import usecase.play_song.PlayUserDataAccessInterface;
import usecase.score.ScoreInputBoundary;
import usecase.score.ScoreInteractor;
import usecase.score.ScoreOutputBoundary;
import usecase.timer.*;
import view.PlayView;

public class PlayUseCaseFactory {

    /** Prevent instantiation. */
    private PlayUseCaseFactory() {}

    public static PlayView create(
            ViewManagerModel viewManagerModel, PlayViewModel playViewModel, SkipViewModel skipViewModel,
            PlayUserDataAccessInterface playUserDataAccessInterface, Quiz quiz) {
        PlayController playController = createUsersPlayCase(viewManagerModel, playViewModel, playUserDataAccessInterface);
        GuessController guessController = createUsersGuessCase(viewManagerModel, playViewModel, quiz);
        SkipController skipController = createUsersSkipCase(viewManagerModel, skipViewModel, quiz);
        ScoreController scoreController = createUsersScoreCase(playViewModel, quiz);
        TimerController timerController = createUsersTimerCase(playViewModel, quiz);
        return new PlayView(playController, skipController,
                scoreController, playViewModel,
                timerController, guessController);
    }

    private static PlayController createUsersPlayCase(
            ViewManagerModel viewManagerModel, PlayViewModel playViewModel,
            PlayUserDataAccessInterface playUserDataAccessInterface) {

        PlayOutputBoundary playOutputBoundary = new PlayPresenter(viewManagerModel, playViewModel);

        PlayInputBoundary userPlayInteractor = new PlayInteractor(
                playUserDataAccessInterface, playOutputBoundary);
        return new PlayController();
    }

    private static GuessController createUsersGuessCase(
            ViewManagerModel viewManagerModel, PlayViewModel playViewModel, Quiz quiz) {

        GuessOutputBoundary guessOutputBoundary = new GuessPresenter(viewManagerModel, playViewModel);

        GuessInputBoundary userGuessInteractor = new GuessInteractor(
                guessOutputBoundary, quiz);
        return new GuessController(userGuessInteractor);

    }

    private static SkipController createUsersSkipCase(
            ViewManagerModel viewManagerModel, SkipViewModel skipViewModel, Quiz quiz) {

        SkipOutputBoundary skipOutputBoundary = new SkipPresenter(skipViewModel);

        SkipInputBoundary userSkipInteractor = new SkipInteractor(
                quiz, skipOutputBoundary);
        return new SkipController(userSkipInteractor);

    }
    private static ScoreController createUsersScoreCase(PlayViewModel playViewModel, Quiz quiz) {

        ScoreOutputBoundary scoreOutputBoundary = new ScorePresenter(playViewModel);

        ScoreInputBoundary userScoreInteractor = new ScoreInteractor(quiz, scoreOutputBoundary);
        return new ScoreController(userScoreInteractor);

    }
    private static TimerController createUsersTimerCase(PlayViewModel playViewModel, Quiz quiz) {

        TimeOutputBoundary timeOutputBoundary = new TimerPresenter(playViewModel);
        TimeInputData timeInputData = new TimeInputData();
        TimeOutputData timeOutputData = new TimeOutputData();

        TimeInputBoundary userTimeInteractor = new TimeInteractor(
                quiz, timeOutputBoundary, timeInputData, timeOutputData);
        return new TimerController(userTimeInteractor, timeInputData);

    }
}

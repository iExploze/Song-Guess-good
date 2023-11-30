package interface_adapter.score;

import interface_adapter.PlayViewModel;
import usecase.score.ScoreOutputBoundary;
import usecase.score.ScoreOutputData;

public class ScorePresenter implements ScoreOutputBoundary {

    private final PlayViewModel playViewModel;

    public ScorePresenter(PlayViewModel playViewModel) {
        this.playViewModel = playViewModel;
    }

    @Override
    public void displayScore(ScoreOutputData scoreOutputData) {
        playViewModel.setScore(scoreOutputData.getScore());
        playViewModel.firePropertyChanged();
    }
}

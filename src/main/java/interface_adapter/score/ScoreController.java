package interface_adapter.score;

import usecase.score.ScoreInputBoundary;

public class ScoreController {

    private final ScoreInputBoundary scoreInputBoundary;

    public ScoreController(ScoreInputBoundary scoreInputBoundary) {
        this.scoreInputBoundary = scoreInputBoundary;
    }

    public void getScore() {
        scoreInputBoundary.returnScore();
    }
}

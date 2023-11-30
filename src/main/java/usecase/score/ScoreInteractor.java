package usecase.score;

import entities.PlaylistQuiz;
import entities.Quiz;

public class ScoreInteractor implements ScoreInputBoundary{

    Quiz quiz;
    ScoreOutputBoundary scoreOutputBoundary;
    public ScoreInteractor(Quiz quiz, ScoreOutputBoundary scoreOutputBoundary)
    {
        this.quiz = quiz;
        this.scoreOutputBoundary = scoreOutputBoundary;
    }
    @Override
    public void returnScore() {
        ScoreOutputData scoreOutputData = new ScoreOutputData(this.quiz.getPoints());
        scoreOutputBoundary.displayScore(scoreOutputData);
    }
}

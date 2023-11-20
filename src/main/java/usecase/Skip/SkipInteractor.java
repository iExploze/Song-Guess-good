package usecase.Skip;

import entities.PlaylistQuiz;

public class SkipInteractor implements SkipInputBoundary {

    PlaylistQuiz quiz;
    final SkipOutputBoundary skipOutputBoundary;
    public SkipInteractor(PlaylistQuiz quiz, SkipOutputBoundary skipOutputBoundary){
        this.quiz = quiz;
        this.skipOutputBoundary = skipOutputBoundary;
    }
    public void execute()
    {
        quiz.goNext();
        skipOutputBoundary.prepareView(this.quiz.currentPlaying().getSongName());
    }
}

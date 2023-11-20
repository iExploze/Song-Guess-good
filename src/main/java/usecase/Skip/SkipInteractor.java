package usecase.Skip;

import entities.PlaylistQuiz;
import entities.Quiz;

public class SkipInteractor implements SkipInputBoundary {

    Quiz quiz;
    final SkipOutputBoundary skipOutputBoundary;
    public SkipInteractor(Quiz quiz, SkipOutputBoundary skipOutputBoundary){
        this.quiz = quiz;
        this.skipOutputBoundary = skipOutputBoundary;
    }
    public void execute()
    {
        quiz.goNext();
        System.out.println(this.quiz.currentPlaying().getSongName());  // temp code for simulating song changes
        skipOutputBoundary.prepareView(this.quiz.currentPlaying().getSongName());
    }
}

package usecase.Skip;

import entities.PlaylistQuiz;

public class SkipInteractor extends SkipInputBoundary {

    PlaylistQuiz quiz;
    public SkipInteractor(PlaylistQuiz quiz){
        this.quiz = quiz;
    }
    public void execute()
    {
        quiz.goNext();
    }
}

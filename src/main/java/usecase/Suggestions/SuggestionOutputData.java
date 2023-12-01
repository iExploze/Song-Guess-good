package usecase.Suggestions;

import entities.Quiz;

import java.util.List;

public class SuggestionOutputData {
    private Quiz quiz;
    public SuggestionOutputData(Quiz quiz) {
        this.quiz = quiz;
    }
    public List<String> getSuggestion(){return quiz.getSuggestions();}
}

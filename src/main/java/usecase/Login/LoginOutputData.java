package usecase.Login;

import entities.Quiz;
import entities.Users.User;

public class LoginOutputData {

    private final User user;
    private Quiz quiz;
    private boolean useCaseFailed;

    public LoginOutputData(User user, Quiz quiz, boolean useCaseFailed) {
        this.user = user;
        this.quiz = quiz;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUser() {
        return user.getUsername();
    }

    public Quiz getQuiz() {
        return quiz;
    }

}

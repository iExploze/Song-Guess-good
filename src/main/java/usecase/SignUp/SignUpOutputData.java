package usecase.SignUp;

import entities.Quiz;
import entities.Users.User;

public class SignUpOutputData {
    // so far only give back username, can add more later for this view
    User user;
    Quiz quiz;


    public SignUpOutputData(User user, Quiz quiz) {
        this.user = user;
        this.quiz = quiz;
    }

    public User getUsername() {
        return this.user;
    }

    public Quiz getQuiz() {
        return this.quiz;
    }
}

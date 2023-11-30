package usecase.SignUp;

import entities.Users.User;

public class SignUpOutputData {
    // so far only give back username, can add more later for this view
    User user;


    public SignUpOutputData(User user) {
        this.user = user;
    }

    public User getUsername() {
        return this.user;
    }
}

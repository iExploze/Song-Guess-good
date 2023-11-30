package usecase.Login;

import entities.Users.User;

public class LoginOutputData {

    private final User user;
    private boolean useCaseFailed;

    public LoginOutputData(User user, boolean useCaseFailed) {
        this.user = user;
        this.useCaseFailed = useCaseFailed;
    }

    public User getUser() {
        return user;
    }

}

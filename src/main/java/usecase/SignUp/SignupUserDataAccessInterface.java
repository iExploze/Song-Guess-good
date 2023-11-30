package usecase.SignUp;

import entities.Users.User;


public interface SignupUserDataAccessInterface {
    boolean exists(String username);

    void save(User user);

    void setTokenInfo(User user);

    User getUser(String username);
}

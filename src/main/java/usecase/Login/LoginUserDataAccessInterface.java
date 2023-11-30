package usecase.Login;


import entities.Users.User;

public interface LoginUserDataAccessInterface {
    boolean exists(String username);

    void save(User user);

    void setTokenInfo(User user);

    User getUser(String username);
}

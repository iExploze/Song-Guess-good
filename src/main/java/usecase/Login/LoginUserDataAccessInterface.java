package usecase.Login;


import entities.Users.User;

public interface LoginUserDataAccessInterface {
    boolean exists(String identifier);

    void save(User user);

    User getUser(String username);
}

package main.java.usecase.Login;


import main.java.entities.Users.User;

public interface LoginUserDataAccessInterface {
    boolean exists(String identifier);

    void save(User user);

    User get(String username);
}

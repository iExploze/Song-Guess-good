package main.java.dataAccessObjects.UserStorage;

import main.java.entities.Users.User;

public interface UserDataAccessObject {
    boolean exists(String username);

    void save(User user);
}

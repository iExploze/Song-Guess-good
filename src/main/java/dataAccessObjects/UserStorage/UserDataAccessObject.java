package dataAccessObjects.UserStorage;

import entities.Users.User;

public interface UserDataAccessObject {
    boolean exists(String username);

    void save(User user);

    void setAccessToken(User user);

    User getUser(String username);
}

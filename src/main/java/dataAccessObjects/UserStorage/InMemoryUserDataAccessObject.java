package dataAccessObjects.UserStorage;


import entities.Users.User;
import usecase.Login.LoginUserDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDataAccessObject implements UserDataAccessObject, LoginUserDataAccessInterface {

    private final Map<String, User> users = new HashMap<>();

    /**
     * @param identifier the user's username
     * @return whether the user exists
     */
    @Override
    public boolean exists(String identifier) {
        return users.containsKey(identifier);
    }

    /**
     * @param user the data to save
     */
    @Override
    public void save(User user) {
        users.put(user.getUsername(), user);
    }

    public void setTokenInfo(User user) {users.put(user.getUsername(), user);}

    @Override
    public User getUser(String username) {
        return users.get(username);
    }
}

package entities.Users;

import java.util.HashMap;

public class CommonUserFactory implements UserFactory {
    @Override
    public User createUser(String name, String password) {
        return new CommonUser(name, password, null);
    }

    @Override
    public User createUser(String name, String password, HashMap<String, String> tokenInfo) {
        return new CommonUser(name, password, tokenInfo);
    }
}

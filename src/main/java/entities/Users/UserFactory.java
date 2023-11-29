package entities.Users;

import java.util.HashMap;

public interface UserFactory {
    User createUser(String name, String password, HashMap<String,String> tokenInfo);
    User createUser(String name, String password);
}

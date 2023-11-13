package main.java.entities.Users;

public class CommonUserFactory implements UserFactory {
    @Override
    public User createUser(String name, String password) {
        return new CommonUser(name, password);
    }

    @Override
    public User createUser(String name, String password, String accessToken) {
        return new CommonUser(name, password, accessToken);
    }
}

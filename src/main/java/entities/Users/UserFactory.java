package main.java.entities.Users;

public interface UserFactory {
    User createUser(String name, String password, String accessToken);
    User createUser(String name, String password);
}

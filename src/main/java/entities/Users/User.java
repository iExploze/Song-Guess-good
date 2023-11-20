package entities.Users;

public interface User {
    String getUsername();
    String getPassword();
    String getAccessToken();
    void setAccessToken(String accessToken);
}

package entities.Users;

import java.util.HashMap;

public interface User {
    String getUsername();
    String getPassword();
    String getAccessToken();
    String getRefreshToken();
    void setTokenInfo(HashMap<String, String> tokenInfo);
    boolean checkExpired();

}

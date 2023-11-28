package entities.Users;

import java.util.HashMap;
import java.util.List;

public interface User {
    String getUsername();
    String getPassword();
    String getAccessToken();
    String getRefreshToken();
    List getTopSongs();
    void setTokenInfo(HashMap<String, String> tokenInfo);
    boolean checkExpired();

}

package entities.Users;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

public class CommonUser implements User {
    private String username;
    private String password;
    private HashMap<String, String> tokenInfo;
    private LocalDateTime timestamp;
    private List topSongs;

    public CommonUser(String username, String password, HashMap<String, String> tokenInfo) {
        this.username = username;
        this.password = password;
        this.tokenInfo = tokenInfo;
        this.timestamp = java.time.LocalDateTime.now();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public void setTokenInfo(HashMap<String, String> tokenInfo) {
        this.tokenInfo = tokenInfo; this.timestamp = java.time.LocalDateTime.now();
    }
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getAccessToken() {return tokenInfo.get("access_token");
    }
    public boolean getTokenInfo() {return tokenInfo == null;}

    public List getTopSongs() {
        return this.topSongs;
    }

    public void setTopSongs(List topSongs) {
        this.topSongs=topSongs;
    }

    public String getRefreshToken() {return tokenInfo.get("refresh_token");}
    public boolean checkExpired() {return java.time.LocalDateTime.now().isAfter(this.timestamp.plusMinutes(59));}
}

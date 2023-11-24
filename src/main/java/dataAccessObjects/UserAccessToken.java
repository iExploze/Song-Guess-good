package dataAccessObjects;

import entities.Users.User;

import java.io.IOException;
import java.util.HashMap;

public class UserAccessToken implements dataAccessToken {
    //this calss should take a User a return their current AccessToken. Meaning if needs to check if an accessToken is
    @Override
    public String getAccessToken(User user) throws IOException {
        return user.getAccessToken();
    }

    public String getUpdatedAccessToken(User user) throws IOException {
        SpotifyAuthenticationObject spotifyAuthenticationObject = new SpotifyAuthenticationObject();
        HashMap<String, String> tokenInfo = spotifyAuthenticationObject.getRequestTokenfromRefreshToken(
                user.getRefreshToken(), "ee5a4dc5c931462e9e630c64a8aee5ac");
        user.setTokenInfo(tokenInfo);
        return user.getRefreshToken();
    }

}

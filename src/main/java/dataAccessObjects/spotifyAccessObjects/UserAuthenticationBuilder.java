package dataAccessObjects.spotifyAccessObjects;

import com.google.gson.Gson;
import entities.Users.CommonUserFactory;
import entities.Users.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class UserAuthenticationBuilder {
    // This class just puts the various user authentication pieces together and builds them
    User user;
    userOAuthObject userOAuthObject;
    userTrackandPlaylistDataAccessObject userTopTracksDataAccessObject;
    SpotifyAuthenticationObject spotifyAuthenticationObject;
    RandomSecureS256Generator randomSecureS256Generator;
    userAuthentication userauthentication;

    public UserAuthenticationBuilder(User user, userOAuthObject userOAuthObject, userTrackandPlaylistDataAccessObject userTopTracksDataAccessObject
    , SpotifyAuthenticationObject spotifyAuthenticationObject, RandomSecureS256Generator randomSecureS256Generator) {
        this.user = user;
        this.randomSecureS256Generator = randomSecureS256Generator;
        this.spotifyAuthenticationObject = spotifyAuthenticationObject;
        this.userTopTracksDataAccessObject = userTopTracksDataAccessObject;
        this.userOAuthObject = userOAuthObject;

    }

    public List getTopTracks() throws IOException {
        String codeVerifier = randomSecureS256Generator.generateRandomCodeVerifier(60);
        String codeChallenge = randomSecureS256Generator.generateRandomCodeChallenge(codeVerifier);
        String client_id = "ee5a4dc5c931462e9e630c64a8aee5ac";
        String redirect_uri = "http://localhost:8080/callback";
        String scope = "user-top-read";
        String url = this.userOAuthObject.userAuthorizationURL("ee5a4dc5c931462e9e630c64a8aee5ac",
                "http://localhost:8080/callback","user-top-read", codeChallenge);
        Gson gson = new Gson();
        if (user.getAccessToken() == null) {
            userAuthentication userauthentication = new userAuthentication(url);
            this.userauthentication = userauthentication;

            String authorization = this.userauthentication.getResponse().substring(5);
            SpotifyAuthenticationObject spotifyAuthenticationObject = new SpotifyAuthenticationObject();
            String accessToken = spotifyAuthenticationObject.requestAccessTokenfromAuthorization(authorization, client_id, redirect_uri, codeVerifier);

            HashMap TokenInfo = gson.fromJson(accessToken, HashMap.class);
            user.setTokenInfo(TokenInfo);
        }
        else {
            String accessToken = spotifyAuthenticationObject.getRequestTokenfromRefreshToken(user.getRefreshToken(), client_id);
            HashMap TokenInfo = gson.fromJson(accessToken, HashMap.class);
            user.setTokenInfo(TokenInfo);
        }

        String topTracks = this.userTopTracksDataAccessObject.getTopTracks();

        List topTrackData = userTopTracksDataAccessObject.topTracksResponseToNamePreviewUrl(topTracks);
        return topTrackData;
    }


}

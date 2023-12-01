
import com.google.gson.Gson;
import dataAccessObjects.spotifyAccessObjects.*;
import entities.Users.CommonUserFactory;
import entities.Users.User;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;
public class AuthenticationTests {
    userAuthentication userauthentication;
    String authorization;
    String client_id = "ee5a4dc5c931462e9e630c64a8aee5ac";
    String redirect_uri = "http://localhost:8080/callback";
    String codeVerifier;
    String codeChallenge;
    String a;
    HashMap b;
    HashMap c;
    String topTracks;
    List<HashMap<String, String>> topTrackData;
    @Before
    public void init() throws IOException, UserTopTracksDataAccessObject.NeedRefreshException {
        RandomSecureS256Generator randomSecureS256Generator = new RandomSecureS256Generator();
        userOAuthObject useroAuthObject = new userOAuthObject();
        codeVerifier = randomSecureS256Generator.generateRandomCodeVerifier(60);
        codeChallenge = randomSecureS256Generator.generateRandomCodeChallenge(codeVerifier);

        String url = useroAuthObject.userAuthorizationURL("ee5a4dc5c931462e9e630c64a8aee5ac",
                "http://localhost:8080/callback","user-top-read", codeChallenge);

        userAuthentication userauthentication = new userAuthentication(url);

        this.userauthentication = userauthentication;
        authorization = this.userauthentication.getResponse().substring(5);
        SpotifyAuthenticationObject spotifyAuthenticationObject = new SpotifyAuthenticationObject();
        a = spotifyAuthenticationObject.requestAccessTokenfromAuthorization(authorization, client_id, redirect_uri, codeVerifier);

        Gson gson = new Gson();
        HashMap b= gson.fromJson(a, HashMap.class);
        String f = spotifyAuthenticationObject.getRequestTokenfromRefreshToken((String) b.get("refresh_token"), client_id);
        c = gson.fromJson(f, HashMap.class);

        // top songs info
        CommonUserFactory commonUserFactory = new CommonUserFactory();
        User user = commonUserFactory.createUser("a","b");
        user.setTokenInfo(c);

        UserTopTracksDataAccessObject userTopTracksDataAccessObject = new UserTopTracksDataAccessObject(user);
        topTracks = userTopTracksDataAccessObject.getTopTracks();

        topTrackData = userTopTracksDataAccessObject.topTracksResponseToNamePreviewUrl(topTracks);
    }

    @Test
    public void testGetAuthorization() throws IOException, UserTopTracksDataAccessObject.NeedRefreshException {
        System.out.println(this.userauthentication.getResponse());
        assertNotNull(this.userauthentication.getResponse());

    }
    @Test
    public void testGetAccessTokenFromAuthorization() throws IOException {
        assertNotNull(b.get("access_token"));
    }
    @Test
    public void testGetRefreshTokenFromAuthorization() {
        assertNotNull(c.get("access_token"));
    }
    @Test
    public void testGetTopTracks() {

        assertNotNull(topTracks);
    }
    @Test
    public void testTopTrackCleaned() {
        System.out.println(topTrackData);
        assertNotNull(topTrackData);
    }
    @Test
    public void getTopTrackNames() {
        ArrayList res = new ArrayList();
        for (HashMap ma: topTrackData) {
            res.add(ma.get("name"));
        }
        System.out.println(res);
    }
}

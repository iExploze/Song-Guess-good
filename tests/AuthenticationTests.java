import com.google.gson.Gson;
import dataAccessObjects.spotifyAccessObjects.*;
import entities.Users.CommonUser;
import entities.Users.CommonUserFactory;
import entities.Users.User;
import org.checkerframework.checker.units.qual.C;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
public class AuthenticationTests {
    static userAuthentication userauthentication;
    static String authorization;
    static String client_id = "ee5a4dc5c931462e9e630c64a8aee5ac";
    static String redirect_uri = "http://localhost:8080/callback";
    static String codeVerifier;
    static String codeChallenge;
    static String a;
    static HashMap b;
    static HashMap c;
    static List topTracks;
    static List<HashMap<String, String>> topTrackData;
    static User user;
    static String refresh1;
    static String refresh2;
    static String refresh3;

    @BeforeClass
    public static void init() throws IOException {
        RandomSecureS256Generator randomSecureS256Generator = new RandomSecureS256Generator();
        userOAuthObject useroAuthObject = new userOAuthObject();
        //codeVerifier = randomSecureS256Generator.generateRandomCodeVerifier(60);
       // codeChallenge = randomSecureS256Generator.generateRandomCodeChallenge(codeVerifier);

        //String url = useroAuthObject.userAuthorizationURL("ee5a4dc5c931462e9e630c64a8aee5ac",
                //"http://localhost:8080/callback","user-top-read", codeChallenge);

        //userauthentication= new userAuthentication(url);

        //authorization = userauthentication.getResponse().substring(5);
        //SpotifyAuthenticationObject spotifyAuthenticationObject = new SpotifyAuthenticationObject();
        //a = spotifyAuthenticationObject.requestAccessTokenfromAuthorization(authorization, client_id, redirect_uri, codeVerifier);

        //Gson gson = new Gson();
        //b = gson.fromJson(a, HashMap.class);
        //String f = spotifyAuthenticationObject.getRequestTokenfromRefreshToken((String) b.get("refresh_token"), client_id);
        //c = gson.fromJson(f, HashMap.class);
        // top songs info
        CommonUserFactory commonUserFactory = new CommonUserFactory();
        user = commonUserFactory.createUser("a","b");
        UserAuthenticationBuilder builder = new UserAuthenticationBuilder(user, new userOAuthObject(),
                new UserTopTracksDataAccessObject(user), new SpotifyAuthenticationObject(),
                new RandomSecureS256Generator());
        topTracks = builder.getTopTracks();
        refresh1 = user.getRefreshToken();

        UserTopTracks userTopTracksDataAccessObject = new UserTopTracksObject();
        topTrackData = userTopTracksDataAccessObject.getTopTracks(user);
        refresh2 = user.getRefreshToken();

        topTrackData = userTopTracksDataAccessObject.getTopTracks(user);
        refresh3 = user.getRefreshToken();
    }

//    @Test
//    public void testGetAuthorization() throws IOException, UserTopTracksDataAccessObject.NeedRefreshException {
//        assertNotNull(userauthentication.getResponse());
//
//    }
//    @Test
//    public void testGetAccessTokenFromAuthorization() throws IOException {
//        assertNotNull(b.get("access_token"));
//    }
//    @Test
//    public void testGetRefreshTokenFromAuthorization() {
//        assertNotNull(c.get("access_token"));
//    }
//    @Test
//    public void testTopTrackCleaned() {
//        assertNotNull(topTrackData);
//    }
    @Test
    public void getTopTrackNames() {
        ArrayList res = new ArrayList();
        for (HashMap ma: topTrackData) {
            res.add(ma.get("name"));
        }
        System.out.println(res);
    }

    @Test
    public void testMulitpleRefresh() throws IOException, AWTException, InterruptedException {
        assertNotEquals(refresh1, refresh2);
        assertNotEquals(refresh2, refresh3);
     }

//    @Test @Ignore
//    public void testUserAuthBuilder() throws IOException {
//        CommonUserFactory c =new CommonUserFactory();
//        User user1 = c.createUser("a","b");
//        UserAuthenticationBuilder a = new UserAuthenticationBuilder(user1, new userOAuthObject(),
//               new UserTopTracksDataAccessObject(user1), new SpotifyAuthenticationObject(),
//                new RandomSecureS256Generator());
//        assertNotNull(topTracks);
//
//     }
    @Test @Ignore
    public void testUserAuthBuilderRefresh() throws IOException {
        UserAuthenticationBuilder a = new UserAuthenticationBuilder(user, new userOAuthObject(),
                new UserTopTracksDataAccessObject(user), new SpotifyAuthenticationObject(),
                new RandomSecureS256Generator());
        assertNotNull(a.getTopTracks());

    }
}

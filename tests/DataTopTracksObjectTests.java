
import dataAccessObjects.spotifyAccessObjects.UserTopTracksDataAccessObject;
import dataAccessObjects.spotifyAccessObjects.UserTopTracksObject;
import entities.Users.CommonUserFactory;
import entities.Users.User;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class DataTopTracksObjectTests {
    List<HashMap<String, String>> topTracks;
    User user;
    @Before
    public void init() throws IOException, UserTopTracksDataAccessObject.NeedRefreshException {
        CommonUserFactory commonUserFactory = new CommonUserFactory();
        user = commonUserFactory.createUser("a","b");
        UserTopTracksObject userTopTracksObject = new UserTopTracksObject();

        topTracks = userTopTracksObject.getTopTracks(user);

    }
    @Test
    public void TestUserTopTracks(){
        System.out.println(topTracks);
        assertNotNull(topTracks);
    }
    @Test
    public void TestUserAccessRefreshToken() {
        assertNotNull(user.getAccessToken());
        assertNotNull(user.getRefreshToken());
    }
}

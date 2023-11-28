
import dataAccessObjects.APItest;
import dataAccessObjects.UserTopTracksDataAccessObject;
import entities.Users.CommonUserFactory;
import entities.Users.User;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

public class TestTopTracks {
    String accessToken;
    CommonUserFactory commonUserFactory;
    User user;
    @Before
    public void init() throws IOException {
        APItest Apitest = new APItest();
        accessToken = Apitest.getAccessToken();
        commonUserFactory = new CommonUserFactory();
        user = commonUserFactory.createUser("Hello", "bye");

    }

    @Test
    public void testFileUserDataAccessObject() throws IOException, UserTopTracksDataAccessObject.NeedRefreshException {
        System.out.println(accessToken);
        UserTopTracksDataAccessObject a = new UserTopTracksDataAccessObject(user);

        String res = a.getTopTracks();
        System.out.println(res);
    }

}

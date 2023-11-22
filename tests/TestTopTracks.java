
import dataAccessObjects.APItest;
import dataAccessObjects.userTopTracksDataAccessObject;
import dataAccessObjects.UserStorage.FileUserDataAccessObject;
import dataAccessObjects.UserStorage.InMemoryUserDataAccessObject;
import entities.Users.CommonUser;
import entities.Users.CommonUserFactory;
import entities.Users.User;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.*;
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
        user.setAccessToken("BQCtAWjoq0XROmLSrxngG2B343utT8_ftd-TSpAWFuIq5l14A8ko0uKQGu1xMzCwFr_mj_WeXifHcZbJPbut5IqOXzjwdoGuUaRVwMhVJSq2XdEitZ8");
    }

    @Test
    public void testFileUserDataAccessObject() throws IOException {
        System.out.println(user.getAccessToken());
        userTopTracksDataAccessObject a = new userTopTracksDataAccessObject();

        HashMap res = a.getTopTracks(user);
        System.out.println(res);
    }

}

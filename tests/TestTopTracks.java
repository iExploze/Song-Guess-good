
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

        user.setAccessToken("");
    }

    @Test
    public void testFileUserDataAccessObject() throws IOException {
        System.out.println(accessToken);
        userTopTracksDataAccessObject a = new userTopTracksDataAccessObject();

        HashMap res = a.getTopTracks(user);
        System.out.println(res);
    }

}

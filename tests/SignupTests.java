import dataAccessObjects.UserStorage.FileUserDataAccessObject;
import dataAccessObjects.UserStorage.InMemoryUserDataAccessObject;
import dataAccessObjects.UserStorage.UserDataAccessObject;
import entities.Users.CommonUserFactory;
import entities.Users.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class SignupTests {

    private User user;
    private static CommonUserFactory commonUserFactory;
    private static FileUserDataAccessObject fileDataUserObject;

    @BeforeClass
    public static void init() throws IOException {
        commonUserFactory = new CommonUserFactory();
        fileDataUserObject = new FileUserDataAccessObject("users.csv", commonUserFactory);
    }

    @Test
    public void testFileUserDataAccessObject() throws IOException {
        user = commonUserFactory.createUser("Hello", "bye");
        fileDataUserObject.save(user);
        assertTrue(fileDataUserObject.exists("Hello"));
        assertEquals(fileDataUserObject.getUser("Hello"), user);
    }
    @Test
    public void testPersistentUserData() throws IOException {
        user = commonUserFactory.createUser("Hello", "bye");
        HashMap<String, String> a = new HashMap<>();
        a.put("access_token", "a");
        a.put("refresh_token", "b");
        user.setTokenInfo(a);
        fileDataUserObject.setTokenInfo(user);
        fileDataUserObject = new FileUserDataAccessObject("users.csv", commonUserFactory);
        user = fileDataUserObject.getUser("Hello");
        assertEquals(user.getUsername(), "Hello");
        assertEquals(user.getPassword(), "bye");
        assertEquals(user.getAccessToken(), "a");
        assertEquals(user.getRefreshToken(), "b");
    }

    @Test
    public void testAccessTokenChangeRefreshToken() {

        user = commonUserFactory.createUser("Hello", "bye");
        HashMap<String, String> a = new HashMap<>();
        a.put("access_token", "ASDSAXZ");
        a.put("refresh_token", "ZX#!XA2");
        user.setTokenInfo(a);
        fileDataUserObject.save(user);
        User user1 = fileDataUserObject.getUser("Hello");
        assertEquals(user1.getAccessToken(), "ASDSAXZ");
        assertEquals(user1.getRefreshToken(), "ZX#!XA2");
    }

}
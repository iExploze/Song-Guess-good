import dataAccessObjects.UserStorage.FileUserDataAccessObject;
import dataAccessObjects.UserStorage.InMemoryUserDataAccessObject;
import dataAccessObjects.UserStorage.UserDataAccessObject;
import entities.Users.CommonUserFactory;
import entities.Users.User;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;


public class SignupTests {

    private User user;
    private CommonUserFactory commonUserFactory;
    private UserDataAccessObject dataAccessObject;
    private FileUserDataAccessObject fileDataUserObject;

    @Before
    public void init() throws IOException {
        commonUserFactory = new CommonUserFactory();
        dataAccessObject = new InMemoryUserDataAccessObject();
        fileDataUserObject = new FileUserDataAccessObject("users.txt", commonUserFactory);
    }

    @Test
    public void testFileUserDataAccessObject() throws IOException {
        user = commonUserFactory.createUser("Hello", "bye");
        fileDataUserObject.save(user);
        assertEquals(fileDataUserObject.getUser("Hello"), user);
    }
    @Test
    public void testPersistentUserData() throws IOException {
        user = fileDataUserObject.getUser("Hello");
        assertEquals(user.getUsername(), "Hello");
        assertEquals(user.getPassword(), "bye");
    }

    @Test
    public void testAccessTokenChange() {

        user = fileDataUserObject.getUser("Hello");
        //user.setTokenInfo("ADSADWASDADWASDAWD");
        //fileDataUserObject.setAccessToken(user);
        User user1 = fileDataUserObject.getUser("Hello");
        assertEquals(user1.getAccessToken(), "ADSADWASDADWASDAWD");
    }

}
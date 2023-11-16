
import main.java.dataAccessObjects.UserStorage.FileUserDataAccessObject;
import main.java.dataAccessObjects.UserStorage.InMemoryUserDataAccessObject;
import main.java.dataAccessObjects.UserStorage.UserDataAccessObject;
import main.java.entities.Users.CommonUser;
import main.java.entities.Users.CommonUserFactory;
import main.java.entities.Users.User;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;


public class SignupTests {

    private User user;
    private CommonUserFactory commonUserFactory;
    private UserDataAccessObject dataAccessObject;
    private FileUserDataAccessObject fileDataUserObject;

    @Before
    public void init() throws IOException {
        user = commonUserFactory.createUser("Hi", "there");
        dataAccessObject = new InMemoryUserDataAccessObject();
        fileDataUserObject = new FileUserDataAccessObject("users.txt", commonUserFactory);
    }

    @Test
    public void testFileUserDataAccessObject() throws IOException {
        fileDataUserObject.save(user);
        assertEquals(fileDataUserObject.ex);
    }
}
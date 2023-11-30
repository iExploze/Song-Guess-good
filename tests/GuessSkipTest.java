import dataAccessObjects.UserStorage.FileUserDataAccessObject;
import dataAccessObjects.UserStorage.InMemoryUserDataAccessObject;
import dataAccessObjects.UserStorage.UserDataAccessObject;
import entities.Users.CommonUserFactory;
import entities.Users.User;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;


public class GuessSkipTest {

    static boolean proceed2nextSong = false;
    static String message = "";
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
    @org.junit.Test
    public void testGuessRight() {


    }

}
package usecase.Login;

import static org.junit.Assert.*;
import dataAccessObjects.UserStorage.FileUserDataAccessObject;
import dataAccessObjects.UserStorage.InMemoryUserDataAccessObject;
import entities.Player;
import entities.PlaylistQuiz;
import entities.Quiz;
import entities.SinglePlayer;
import entities.Users.CommonUser;
import entities.Users.CommonUserFactory;
import entities.Users.User;

import org.junit.Test;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;

public class LoginInteractorTest {

    @Test
    public void successTest() throws IOException {
        LoginInputData inputData = new LoginInputData("a", "123");
        LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                assertEquals("a", user.getUser());
                assertTrue(userRepository.exists("a"));
            }

            @Override
            public void prepareFailView(String error) {

            }


            @Override
            public void prepareSignUpSwitch() {
            }
        };

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("BQDctzy45_0fiJDvqVztfqV-IURhNLOwkiqzyqP9qj0PGSGzPwTiNU_sfDXwwtCgMWfF9qiqsbp-nkUNKB6M0heHIJgt-QaSHv5AklVYtwVnfCyC5AVXIlLU-U12C1AFz5tAn6XlZdMsPRgE0gWk0UhXptbWZ74Jb37mgMKQwKzwAoRPdZaeuRN6N43MWbvzRdhY2OYBsA",
                "AQAm-YfeZUUVxwb-VUPlZMXsqpi2_rXPk43zLx5BSqeoxv4lUymAdKoUscziIMpV7JPxXYgXLH3ZG-laopbrXtG14Qe29DSeYshj6PDJ_B_yefD62aN-Z9yMnx0PamP5Iy8");
        User user = new CommonUser("a", "123", hashMap);
        Player player = new SinglePlayer(user);
        LoginInputBoundary interactor = new LoginInteractor(userRepository, successPresenter, new PlaylistQuiz(player));
        try {
            interactor.execute(inputData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void failurePasswordMismatchTest() {
        LoginInputData inputData = new LoginInputData("a", "wrong");
        LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();


        // This creates a presenter that tests whether the test case is as we expect.
        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Incorrect password for a.", error);
            }

            @Override
            public void prepareSignUpSwitch() {
            }
        };

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("BQDctzy45_0fiJDvqVztfqV-IURhNLOwkiqzyqP9qj0PGSGzPwTiNU_sfDXwwtCgMWfF9qiqsbp-nkUNKB6M0heHIJgt-QaSHv5AklVYtwVnfCyC5AVXIlLU-U12C1AFz5tAn6XlZdMsPRgE0gWk0UhXptbWZ74Jb37mgMKQwKzwAoRPdZaeuRN6N43MWbvzRdhY2OYBsA",
                "AQAm-YfeZUUVxwb-VUPlZMXsqpi2_rXPk43zLx5BSqeoxv4lUymAdKoUscziIMpV7JPxXYgXLH3ZG-laopbrXtG14Qe29DSeYshj6PDJ_B_yefD62aN-Z9yMnx0PamP5Iy8");
        User user = new CommonUser("a", "123", hashMap);
        userRepository.save(user);
        Player player = new SinglePlayer(user);
        LoginInputBoundary interactor = new LoginInteractor(userRepository, failurePresenter, new PlaylistQuiz(player));
        try {
            interactor.execute(inputData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void failureUserNotExistsTest() {
        LoginInputData inputData = new LoginInputData("b", "123");
        LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("b: Account does not exist.", error);
            }

            @Override
            public void prepareSignUpSwitch() {

            }
        };

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("BQDctzy45_0fiJDvqVztfqV-IURhNLOwkiqzyqP9qj0PGSGzPwTiNU_sfDXwwtCgMWfF9qiqsbp-nkUNKB6M0heHIJgt-QaSHv5AklVYtwVnfCyC5AVXIlLU-U12C1AFz5tAn6XlZdMsPRgE0gWk0UhXptbWZ74Jb37mgMKQwKzwAoRPdZaeuRN6N43MWbvzRdhY2OYBsA",
                "AQAm-YfeZUUVxwb-VUPlZMXsqpi2_rXPk43zLx5BSqeoxv4lUymAdKoUscziIMpV7JPxXYgXLH3ZG-laopbrXtG14Qe29DSeYshj6PDJ_B_yefD62aN-Z9yMnx0PamP5Iy8");
        User user = new CommonUser("a", "123", hashMap);
        userRepository.save(user);
        Player player = new SinglePlayer(user);
        LoginInputBoundary interactor = new LoginInteractor(userRepository, failurePresenter, new PlaylistQuiz(player));
        try {
            interactor.execute(inputData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
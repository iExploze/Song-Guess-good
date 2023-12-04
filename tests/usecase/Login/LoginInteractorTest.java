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

import interface_adapter.login.LoginViewModel;
import org.junit.Test;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;

public class LoginInteractorTest {

    @Test
    public void successTest() throws IOException {
        LoginInputData inputData = new LoginInputData("Flora", "123");
        LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                assertEquals("Flora", user.getUser());
                assertTrue(userRepository.exists("Flora"));
            }

            @Override
            public void prepareFailView(String error) {

            }


            @Override
            public void prepareSignUpSwitch() {
            }
        };

        HashMap<String, String> hashMap = new HashMap<>();
        User user = new CommonUser("Flora", "123", hashMap);
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
        LoginInputData inputData = new LoginInputData("Flora", "wrong");
        LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // This creates a presenter that tests whether the test case is as we expect.
        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Incorrect password for Flora.", error);
            }

            @Override
            public void prepareSignUpSwitch() {
            }
        };

        HashMap<String, String> hashMap = new HashMap<>();
        User user = new CommonUser("Flora", "123", hashMap);
        userRepository.save(user);
        Player player = new SinglePlayer(user);
        LoginInputBoundary interactor = new LoginInteractor(userRepository, failurePresenter, new PlaylistQuiz(player));

        try {
            interactor.execute(inputData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //LoginViewModel loginViewModel = new LoginViewModel();
        //assertEquals("interface_adapter.login.LoginState@45c8e616", loginViewModel.getState());
    }


    @Test
    public void failureUserNotExistsTest() {
        LoginInputData inputData = new LoginInputData("Null", "123");
        LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Null: Account does not exist.", error);
            }

            @Override
            public void prepareSignUpSwitch() {

            }
        };

        HashMap<String, String> hashMap = new HashMap<>();
        User user = new CommonUser("Flora", "123", hashMap);
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
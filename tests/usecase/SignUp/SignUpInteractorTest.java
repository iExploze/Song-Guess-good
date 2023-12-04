package usecase.SignUp;

import static org.junit.Assert.*;
import dataAccessObjects.UserStorage.InMemoryUserDataAccessObject;
import entities.*;
import entities.Users.CommonUserFactory;
import entities.Users.User;
import entities.Users.UserFactory;

import org.junit.Test;
import java.io.IOException;

public class SignUpInteractorTest {
    private Quiz quiz;

    @Test
    public void successTest() throws IOException {
        SignUpInputData inputData = new SignUpInputData("user", "pass", "pass");
        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        SignUpOutputBoundary successPresenter = new SignUpOutputBoundary() {
            @Override
            public void prepareSuccessView(SignUpOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                assertEquals("user", user.getUsername().getUsername());
                assertTrue(userRepository.exists("user"));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchLoginView() {

            }
        };

        SignUpInputBoundary interactor = new SignUpInteractor(new CommonUserFactory(), userRepository, successPresenter, quiz);
        interactor.execute(inputData);
    }

    @Test
    public void failurePasswordMismatchTest() throws IOException {
        SignUpInputData inputData = new SignUpInputData("user", "pass", "wrong");
        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // This creates a presenter that tests whether the test case is as we expect.
        SignUpOutputBoundary failurePresenter = new SignUpOutputBoundary() {
            @Override
            public void prepareSuccessView(SignUpOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Password does not match!", error);
            }

            @Override
            public void switchLoginView(){

            }
        };

        SignUpInputBoundary interactor = new SignUpInteractor(new CommonUserFactory(), userRepository, failurePresenter, quiz);
        interactor.execute(inputData);
    }

    @Test
    public void failureUserExistsTest() throws IOException {
        SignUpInputData inputData = new SignUpInputData("user", "pass", "wrong");
        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // Add Paul to the repo so that when we check later they already exist
        UserFactory factory = new CommonUserFactory();
        User user = factory.createUser("user", "pwd");
        userRepository.save(user);

        // This creates a presenter that tests whether the test case is as we expect.
        SignUpOutputBoundary failurePresenter = new SignUpOutputBoundary() {
            @Override
            public void prepareSuccessView(SignUpOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Username already exists!", error);
            }

            @Override
            public void switchLoginView(){

            }
        };

        SignUpInputBoundary interactor = new SignUpInteractor(new CommonUserFactory(), userRepository, failurePresenter, quiz);
        interactor.execute(inputData);
    }
}
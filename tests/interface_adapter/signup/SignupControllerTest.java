package interface_adapter.signup;

import dataAccessObjects.UserStorage.InMemoryUserDataAccessObject;
import org.junit.Test;
import usecase.SignUp.SignUpInputBoundary;
import usecase.SignUp.SignUpInputData;
import usecase.SignUp.SignupUserDataAccessInterface;

import java.io.IOException;

import static org.junit.Assert.*;

public class SignupControllerTest {
    @Test
    public void successTest() throws IOException {
        SignUpInputData inputData = new SignUpInputData("user", "pass", "pass");

        SignUpInputBoundary successInteractor = new SignUpInputBoundary() {
            @Override
            public void execute(SignUpInputData signUpInputData) throws IOException {
                assertEquals(signUpInputData.getPassword(), "pass");
                assertEquals(signUpInputData.getUsername(), "user");
                assertEquals(signUpInputData.getConfirmPassword(), "pass");
            }

            @Override
            public void switchLoginView() {
            }

        };

        SignupController signUpController = new SignupController(successInteractor);
        signUpController.execute("user", "pass", "pass");
    }
}
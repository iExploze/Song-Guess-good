package interface_adapter.signup;

import usecase.SignUp.SignUpInputBoundary;
import usecase.SignUp.SignUpInputData;

import java.io.IOException;

public class SignupController {

    final SignUpInputBoundary userSignupUseCaseInteractor;
    public SignupController(SignUpInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    public void execute(String username, String password1, String password2) throws IOException {
        SignUpInputData signupInputData = new SignUpInputData(
                username, password1, password2);

        userSignupUseCaseInteractor.execute(signupInputData);
    }

    public void switchLoginView() {
        userSignupUseCaseInteractor.switchLoginView();
    }
}

package interface_adapter.login;


import usecase.Login.LoginInputBoundary;
import usecase.Login.LoginInputData;

import java.io.IOException;

public class LoginController {

    final LoginInputBoundary loginUseCaseInteractor;
    public LoginController(LoginInputBoundary loginUseCaseInteractor) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }


    public void execute(String username, String password) throws IOException {
        LoginInputData loginInputData = new LoginInputData(
                username, password);

        loginUseCaseInteractor.execute(loginInputData);
    }
    public void switchSignUp() {
        loginUseCaseInteractor.switchSignUp();
    }
}

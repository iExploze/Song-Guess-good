package usecase.Login;

import java.io.IOException;

public interface LoginInputBoundary {
    void execute(LoginInputData loginInputData) throws IOException;
    void switchSignUp();
}

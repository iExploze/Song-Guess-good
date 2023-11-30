package usecase.SignUp;

import java.io.IOException;

public interface SignUpInputBoundary {
    void execute(SignUpInputData signUpInputData) throws IOException;

    void switchLoginView();
}

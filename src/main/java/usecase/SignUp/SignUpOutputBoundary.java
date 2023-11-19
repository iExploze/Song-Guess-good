package usecase.SignUp;

public interface SignUpOutputBoundary {
    void prepareSuccessView(SignUpOutputData signUpOutputData);
    void prepareFailView(String error);
}

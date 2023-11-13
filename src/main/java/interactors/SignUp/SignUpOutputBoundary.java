package main.java.interactors.SignUp;

public interface SignUpOutputBoundary {
    void prepareSuccessView(SignUpOutputData signUpOutputData);
    void prepareFailView(String error);
}

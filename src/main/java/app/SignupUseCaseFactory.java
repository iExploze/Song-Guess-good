package app;

import entities.Quiz;
import entities.Users.CommonUser;
import entities.Users.CommonUserFactory;
import interface_adapter.PlayViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;

import usecase.SignUp.SignUpInputBoundary;
import usecase.SignUp.SignUpInteractor;
import usecase.SignUp.SignUpOutputBoundary;
import usecase.SignUp.SignupUserDataAccessInterface;
import view.SignupView;

import javax.swing.*;
import java.io.IOException;

public class SignupUseCaseFactory {

    /** Prevent instantiation. */
    private SignupUseCaseFactory() {}

    public static SignupView create(
            ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel, SignupUserDataAccessInterface userDataAccessObject,
            PlayViewModel playViewModel, Quiz quiz) {

        try {
            SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel, userDataAccessObject, playViewModel, quiz);
            return new SignupView(signupController, signupViewModel, loginViewModel, playViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }


    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel, SignupUserDataAccessInterface userDataAccessObject,
    PlayViewModel playViewModel, Quiz quiz) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SignUpOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel, playViewModel);

        CommonUserFactory userFactory = new CommonUserFactory();

        SignUpInputBoundary userSignupInteractor = new SignUpInteractor(userFactory,
                userDataAccessObject, signupOutputBoundary, quiz);

        return new SignupController(userSignupInteractor);
    }
}

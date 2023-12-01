package interface_adapter.signup;

import interface_adapter.PlayState;
import interface_adapter.PlayViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import usecase.SignUp.SignUpOutputBoundary;
import usecase.SignUp.SignUpOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SignupPresenter implements SignUpOutputBoundary {

    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;
    private PlayViewModel playViewModel;

    public SignupPresenter(ViewManagerModel viewManagerModel,
                           SignupViewModel signupViewModel,
                           LoginViewModel loginViewModel,
                           PlayViewModel playViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
        this.playViewModel = playViewModel;
    }

    @Override
    public void prepareSuccessView(SignUpOutputData response) {
        // On success, switch to the play view.
        //response has a user, we can use it to make a play view
        PlayState playState = playViewModel.getState();
        playState.setSuggestions(response.getQuiz());
        playState.setSong(response.getQuiz().currentPlaying());
        this.playViewModel.setSuggestions(playState);
        this.playViewModel.setState(playState);
        this.playViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(playViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        SignupState signupState = signupViewModel.getState();
        signupState.setUsernameError(error);
        signupViewModel.firePropertyChanged();
    }

    @Override
    public void switchLoginView() {
        loginViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(loginViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}

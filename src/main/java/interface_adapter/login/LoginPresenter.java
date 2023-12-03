package interface_adapter.login;

import interface_adapter.PlayState;
import interface_adapter.PlayViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.signup.SignupViewModel;
import usecase.Login.LoginOutputBoundary;
import usecase.Login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final PlayViewModel playViewModel;
    private ViewManagerModel viewManagerModel;
    private SignupViewModel signupViewModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          PlayViewModel playViewModel,
                          LoginViewModel loginViewModel,
                          SignupViewModel signupViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.playViewModel = playViewModel;
        this.loginViewModel = loginViewModel;
        this.signupViewModel = signupViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.


        // using user change playViewModel, create it, and set to new presenter
        PlayState playState = playViewModel.getState();
        playState.setSuggestions(response.getSuggestions());
        playState.setSong(response.getSong());
        this.playViewModel.setState(playState);
        this.playViewModel.setSuggestions(playState);
        this.playViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(playViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();


    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsernameError(error);
        loginViewModel.firePropertyChanged();
    }
    public void prepareSignUpSwitch() {
        signupViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(signupViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}

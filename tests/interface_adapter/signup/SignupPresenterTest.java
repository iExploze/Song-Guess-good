package interface_adapter.signup;

import entities.Users.CommonUser;
import entities.Users.User;
import interface_adapter.PlayState;
import interface_adapter.PlayViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import org.junit.Before;
import org.junit.Test;
import usecase.SignUp.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class SignupPresenterTest {
    ViewManagerModel viewManagerModel;
    SignupViewModel signupViewModel;
    LoginViewModel loginViewModel;
    PlayViewModel playViewModel;
    SignupPresenter signupPresenter;
    @Before
    public void init(){
        this.viewManagerModel = new ViewManagerModel();
        this.signupViewModel = new SignupViewModel();
        this.loginViewModel = new LoginViewModel();
        this.playViewModel = new PlayViewModel();
        this.signupPresenter = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel, playViewModel);
    }

    @Test
    public void successTest() throws IOException {
        // test data
        List<String> suggestions = Arrays.asList("song", "song2", "song3");
        String song = "song";
        HashMap<String, String> users = new HashMap<>();
        User user = new CommonUser("user", "pass", users);
        SignUpOutputData signUpOutputData = new SignUpOutputData(user, suggestions, song);

        // execute the method
        signupPresenter.prepareSuccessView(signUpOutputData);

        // check for changes in pvm
        PlayState playState = playViewModel.getState();
        assertEquals(signUpOutputData.getSuggestions(), playState.getSuggestions());
        assertEquals(signUpOutputData.getSong(), playState.getSong());

        // check view stays on play view
        assertEquals(viewManagerModel.getActiveView(), playViewModel.getViewName());

    }

    @Test
    public void failTest() throws IOException {
        // error could change depending on type of error
        // view stays the same regardless of error
        String error = "Username already exists!";

        // execute the method
        signupPresenter.prepareFailView(error);

        // check for changes in suvm and vmm
        assertEquals(error, signupViewModel.getState().getUsernameError());
        assertNull(viewManagerModel.getActiveView());
    }

    @Test
    public void switchLoginViewTest() throws IOException {
        signupPresenter.switchLoginView();

        // check the view was changed to login view
        assertEquals(viewManagerModel.getActiveView(), loginViewModel.getViewName());
    }

}
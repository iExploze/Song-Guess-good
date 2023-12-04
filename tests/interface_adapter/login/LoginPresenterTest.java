package interface_adapter.login;

import entities.Player;
import entities.PlaylistQuiz;
import entities.SinglePlayer;
import entities.Users.CommonUser;
import entities.Users.User;
import interface_adapter.PlayState;
import interface_adapter.PlayViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginController;
import interface_adapter.signup.SignupViewModel;
import usecase.Login.*;

import org.junit.Test;
import view.SignupView;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginPresenterTest {

    @Test
    public void successTest() throws IOException {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        PlayViewModel playViewModel = new PlayViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        PlayState playState = new PlayState();

        HashMap<String, String> hashMap = new HashMap<>();
        User user = new CommonUser("Flora", "123", hashMap);
        LoginOutputData loginOutputData = new LoginOutputData(user, null, "", false);

        LoginPresenter loginPresenter = new LoginPresenter(viewManagerModel, playViewModel,
                loginViewModel, signupViewModel);
        loginPresenter.prepareSuccessView(loginOutputData);

        // check whether switched to the play view
        assertEquals("PLAY_VIEW", viewManagerModel.getActiveView());
    }

    @Test
    public void failTest() throws IOException {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        PlayViewModel playViewModel = new PlayViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        PlayState playState = new PlayState();

        User user = new CommonUser("Flora", "123", new HashMap<>());
        LoginOutputData loginOutputData = new LoginOutputData(user, null, "", true);

        LoginPresenter loginPresenter = new LoginPresenter(viewManagerModel, playViewModel,
                loginViewModel, signupViewModel);
        String error = "Incorrect password for Flora.";
        loginPresenter.prepareFailView(error);

        // check whether switched to the play view
        assertEquals(null, viewManagerModel.getActiveView());
    }

}

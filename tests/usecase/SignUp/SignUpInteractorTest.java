package usecase.SignUp;

import static org.junit.Assert.*;
import dataAccessObjects.UserStorage.FileUserDataAccessObject;
import dataAccessObjects.UserStorage.InMemoryUserDataAccessObject;
import dataAccessObjects.spotifyAccessObjects.UserTopTracksObject;
import entities.Player;
import entities.PlaylistQuiz;
import entities.SinglePlayer;
import entities.SpotifyPlaylist;
import entities.Users.CommonUser;
import entities.Users.CommonUserFactory;
import entities.Users.User;
import entities.Users.UserFactory;
import interface_adapter.PlayViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import org.junit.Test;
import usecase.Login.LoginOutputData;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;

public class SignUpInteractorTest {

    @Test
    public void successTest() throws IOException {
        SignUpInputData inputData = new SignUpInputData("Flora", "123", "123");
        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        HashMap<String, String> hashMap = new HashMap<>();
        User user = new CommonUser("Flora", "123", hashMap);
        userRepository.save(user);
        Player player = new SinglePlayer(user);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        PlayViewModel playViewModel = new PlayViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();

        // This creates a successPresenter that tests whether the test case is as we expect.
        SignUpOutputBoundary successPresenter = new SignUpOutputBoundary() {

            @Override
            public void prepareSuccessView(SignUpOutputData user) {
                assertEquals("Flora", user.getUsername());
                assertNotNull(user.getSong()); // any song is fine.
            }

            @Override
            public void prepareFailView(String error) {
            }

            @Override
            public void switchLoginView() {

            }
        };

        SignUpInputBoundary interactor = new SignUpInteractor(new CommonUserFactory(), userRepository, successPresenter,
                new PlaylistQuiz(player), new UserTopTracksObject(), new SpotifyPlaylist());
        interactor.execute(inputData);

        SignUpOutputData signUpOutputData = new SignUpOutputData(user, null, "");
        SignupPresenter signupPresenter = new SignupPresenter(viewManagerModel, signupViewModel,
                loginViewModel, playViewModel);
        signupPresenter.prepareSuccessView(signUpOutputData);

        // check whether switched to the play view
        assertEquals("PLAY_VIEW", viewManagerModel.getActiveView());
        assertNotNull(signUpOutputData.getUsername());
    }
}
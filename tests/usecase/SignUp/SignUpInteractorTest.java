package usecase.SignUp;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import dataAccessObjects.UserStorage.FileUserDataAccessObject;
import dataAccessObjects.UserStorage.InMemoryUserDataAccessObject;
import dataAccessObjects.getTop200SongNames;
import dataAccessObjects.spotifyAccessObjects.UserTopTracks;
import dataAccessObjects.spotifyAccessObjects.UserTopTracksObject;
import entities.*;
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
import usecase.guess.GuessOutputBoundary;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
                assertEquals("Flora", user.getUsername().getUsername());
                assertNotNull(user.getSong()); // any song is fine.
                assertTrue(userRepository.exists("Flora"));
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

    @Test
    public void successTestSignup() throws IOException{
        // mock objects
        UserFactory userFactory = mock(UserFactory.class);
        User user = mock(User.class);
        when(user.getUsername()).thenReturn("username");
        when(user.getPassword()).thenReturn("pwd");
        when(userFactory.createUser("username", "pwd")).thenReturn(user);SignupUserDataAccessInterface userRepository = mock(SignupUserDataAccessInterface.class);

        UserTopTracks userTopTracks = mock(UserTopTracks.class);
        when(userTopTracks.getTopTracks(user)).thenReturn(new ArrayList<>());

        Playlist playlist = mock(Playlist.class);
        when(playlist.getSuggestions()).thenReturn(new ArrayList<>());

        getTop200SongNames g = mock(getTop200SongNames.class);
        when(g.top200("path.csv")).thenReturn(new ArrayList<>());

        Quiz quiz = mock(Quiz.class);
        when(quiz.currentPlaying()).thenReturn(new SongData("song", "url"));

        // initialize the interactor
        SignUpOutputBoundary signUpPresenter = mock(SignUpOutputBoundary.class);
        SignUpInputData signUpInputData = new SignUpInputData("username", "pwd", "pwd");
        SignUpInteractor signUpInteractor = new SignUpInteractor(userFactory, userRepository, signUpPresenter, quiz, userTopTracks, playlist);
        signUpInteractor.execute(signUpInputData);

        // check that the user was saved once and that prepareSuccessView was called
        verify(userRepository, times(1)).save(user);
        verify(signUpPresenter, times(1)).prepareSuccessView(any(SignUpOutputData.class));

    }

    @Test
    public void failurePasswordMismatchTest() throws IOException {
        SignUpInputData inputData = new SignUpInputData("user", "pass", "wrong");
        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        HashMap<String, String> hashMap = new HashMap<>();
        User user = new CommonUser("Flora", "123", hashMap);
        userRepository.save(user);
        Player player = new SinglePlayer(user);

        // This creates a presenter that tests whether the test case is as we expect.
        SignUpOutputBoundary failurePresenter = new SignUpOutputBoundary() {
            @Override
            public void prepareSuccessView(SignUpOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Password does not match!", error);
            }

            @Override
            public void switchLoginView(){

            }
        };

        SignUpInputBoundary interactor = new SignUpInteractor(new CommonUserFactory(), userRepository, failurePresenter,
                new PlaylistQuiz(player), new UserTopTracksObject(), new SpotifyPlaylist());
        interactor.execute(inputData);
    }

    @Test
    public void failureUserExistsTest() throws IOException {
        SignUpInputData inputData = new SignUpInputData("user", "pass", "wrong");
        SignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // Add Paul to the repo so that when we check later they already exist
        UserFactory factory = new CommonUserFactory();
        User user = factory.createUser("user", "pwd");
        userRepository.save(user);
        Player player = new SinglePlayer(user);

        // This creates a presenter that tests whether the test case is as we expect.
        SignUpOutputBoundary failurePresenter = new SignUpOutputBoundary() {
            @Override
            public void prepareSuccessView(SignUpOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Username already exists!", error);
            }

            @Override
            public void switchLoginView(){

            }
        };

        SignUpInputBoundary interactor = new SignUpInteractor(new CommonUserFactory(), userRepository, failurePresenter,
                new PlaylistQuiz(player), new UserTopTracksObject(), new SpotifyPlaylist());
        interactor.execute(inputData);
    }
}
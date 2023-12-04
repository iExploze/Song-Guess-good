package usecase.Login;

import static org.junit.Assert.*;
import dataAccessObjects.UserStorage.FileUserDataAccessObject;
import dataAccessObjects.UserStorage.InMemoryUserDataAccessObject;
import dataAccessObjects.UserStorage.UserDataAccessObject;
import dataAccessObjects.getTop200SongNames;
import dataAccessObjects.spotifyAccessObjects.UserTopTracks;
import dataAccessObjects.spotifyAccessObjects.UserTopTracksObject;
import entities.*;
import entities.Users.CommonUser;
import entities.Users.CommonUserFactory;
import entities.Users.User;

import entities.Users.UserFactory;
import interface_adapter.login.LoginViewModel;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import org.mockito.Mockito;
import usecase.SignUp.SignupUserDataAccessInterface;
import usecase.guess.GuessInteractor;
import usecase.guess.GuessOutputBoundary;

import static org.mockito.Mockito.*;

public class LoginInteractorTest {

    @Test
    public void successTest() throws IOException {
        LoginInputData inputData = new LoginInputData("Flora", "123");
        LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        HashMap<String, String> hashMap = new HashMap<>();
        User user = new CommonUser("Flora", "123", hashMap);
        Player player = new SinglePlayer(user);
        LoginOutputData loginOutputData = new LoginOutputData(user, null, null, false);

        // This creates a successPresenter that tests whether the test case is as we expect.
        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                assertEquals("Flora", user.getUser());
                assertTrue(userRepository.exists("Flora"));

            }

            @Override
            public void prepareFailView(String error) {

            }


            @Override
            public void prepareSignUpSwitch() {
            }
        };

        assertEquals("Flora", loginOutputData.getUser());

        LoginInputBoundary interactor = new LoginInteractor(new CommonUserFactory(), userRepository, successPresenter, new PlaylistQuiz(player),
                new UserTopTracksObject(), new SpotifyPlaylist());
        try {
            interactor.execute(inputData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void successTestLogin() throws IOException { //apply mockito to test
        UserFactory userFactory = mock(UserFactory.class);
        User user = mock(User.class);
        when(user.getUsername()).thenReturn("User");
        when(user.getPassword()).thenReturn("123");

        HashMap<String, String> hashMap = new HashMap<>();
        LoginUserDataAccessInterface userRepository = mock(LoginUserDataAccessInterface.class);
        when(userRepository.exists("User")).thenReturn(true);
        when(userRepository.getUser("User")).thenReturn(user);

        UserTopTracks userTopTracks = mock(UserTopTracks.class);
        when(userTopTracks.getTopTracks(user)).thenReturn(new ArrayList<>());

        Playlist playlist = mock(Playlist.class);
        when(playlist.getSuggestions()).thenReturn(new ArrayList<>());

        getTop200SongNames g = mock(getTop200SongNames.class);
        when(g.top200("path.csv")).thenReturn(new ArrayList<>());

        Quiz quiz = mock(Quiz.class);
        when(quiz.currentPlaying()).thenReturn(new SongData("song", "url"));


        // initialize the interactor
        LoginOutputBoundary loginPresenter = mock(LoginOutputBoundary.class);
        LoginInputData loginInputData = new LoginInputData("User", "123");
        LoginInteractor loginInteractor = new LoginInteractor(userFactory, userRepository, loginPresenter, quiz,
                userTopTracks, playlist);
        loginInteractor.execute(loginInputData);

        // check that the user was saved once and that prepareSuccessView was called
        verify(userRepository, times(1)).exists("User");
        verify(loginPresenter, times(1)).prepareSuccessView(any(LoginOutputData.class));

    }
    @Test
    public void failurePasswordMismatchTest() {
        LoginInputData inputData = new LoginInputData("Flora", "wrong");
        LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // This creates a presenter that tests whether the test case is as we expect.
        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Incorrect password for Flora.", error);
            }

            @Override
            public void prepareSignUpSwitch() {
            }
        };

        HashMap<String, String> hashMap = new HashMap<>();
        User user = new CommonUser("Flora", "123", hashMap);
        userRepository.save(user);
        Player player = new SinglePlayer(user);
        LoginInputBoundary interactor = new LoginInteractor(new CommonUserFactory(), userRepository, failurePresenter, new PlaylistQuiz(player),
                new UserTopTracksObject(), new SpotifyPlaylist());

        try {
            interactor.execute(inputData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @Test
    public void failureUserNotExistsTest() {
        LoginInputData inputData = new LoginInputData("Null", "123");
        LoginUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Null: Account does not exist.", error);
            }

            @Override
            public void prepareSignUpSwitch() {

            }
        };

        HashMap<String, String> hashMap = new HashMap<>();
        User user = new CommonUser("Flora", "123", hashMap);
        userRepository.save(user);
        Player player = new SinglePlayer(user);
        LoginInputBoundary interactor = new LoginInteractor(new CommonUserFactory(), userRepository, failurePresenter, new PlaylistQuiz(player),
                new UserTopTracksObject(), new SpotifyPlaylist());
        try {
            interactor.execute(inputData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
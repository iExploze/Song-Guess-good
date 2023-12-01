package usecase.Login;

import dataAccessObjects.spotifyAccessObjects.UserTopTracks;
import dataAccessObjects.spotifyAccessObjects.UserTopTracksObject;
import entities.*;
import entities.Users.User;

import java.io.IOException;


public class LoginInteractor implements LoginInputBoundary {
    final LoginUserDataAccessInterface userDataAccessObject;
    final LoginOutputBoundary loginPresenter;

    final Quiz quiz;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary, Quiz quiz) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
        this.quiz = quiz;
    }


    public void execute(LoginInputData loginInputData) throws IOException {
        String username = loginInputData.getUsername();
        String password = loginInputData.getPassword();
        if (!userDataAccessObject.exists(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        } else {
            String pwd = userDataAccessObject.getUser(username).getPassword();
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Incorrect password for " + username + ".");
            } else {

                User user = userDataAccessObject.getUser(loginInputData.getUsername());
                UserTopTracks userTopTracksObject = new UserTopTracksObject();
                SpotifyPlaylist spotifyPlaylist = new SpotifyPlaylist(userTopTracksObject.getTopTracks(user));
                quiz.setQuiz(spotifyPlaylist);
                LoginOutputData loginOutputData = new LoginOutputData(user, false);
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }

    @Override
    public void switchSignUp() {
        loginPresenter.prepareSignUpSwitch();
    }
}
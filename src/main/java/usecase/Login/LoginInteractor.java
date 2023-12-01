package usecase.Login;

import dataAccessObjects.getTop200SongNames;
import dataAccessObjects.spotifyAccessObjects.UserTopTracks;
import dataAccessObjects.spotifyAccessObjects.UserTopTracksObject;
import entities.*;
import entities.Users.User;
import usecase.Suggestions.SuggestionOutputBoundary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
                // Gets top 200 songs and then adds current songs from playlist to them. hashset ensures uniqueness.
                getTop200SongNames g = new getTop200SongNames();
                List topSongs =  g.top200("./top200SongsWeekly.csv");
                Set<String> topSongsSet = new HashSet<>(topSongs);
                topSongsSet.addAll(spotifyPlaylist.getSuggestions());
                List<String> allSuggestions = new ArrayList<>(topSongsSet);

                quiz.setSuggestions(allSuggestions);

                LoginOutputData loginOutputData = new LoginOutputData(user, quiz, false);
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }

    @Override
    public void switchSignUp() {
        loginPresenter.prepareSignUpSwitch();
    }
}
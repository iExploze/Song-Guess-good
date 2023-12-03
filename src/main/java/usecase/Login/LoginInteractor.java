package usecase.Login;

import dataAccessObjects.getTop200SongNames;
import dataAccessObjects.spotifyAccessObjects.UserTopTracks;
import dataAccessObjects.spotifyAccessObjects.UserTopTracksObject;
import entities.Playlist;
import entities.Quiz;
import entities.Song;
import entities.SpotifyPlaylist;
import entities.Users.User;

import java.io.IOException;
import java.util.*;


public class LoginInteractor implements LoginInputBoundary {
    final LoginUserDataAccessInterface userDataAccessObject;
    final LoginOutputBoundary loginPresenter;
    final Quiz quiz;
    final UserTopTracks userTopTracks;
    final Playlist playlist;


    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary, Quiz quiz, UserTopTracks userTopTracks, Playlist playlist) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
        this.quiz = quiz;
        this.userTopTracks = userTopTracks;
        this.playlist = playlist;
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
                List<HashMap<String, String>> topTracks = userTopTracks.getTopTracks(user);
                playlist.setSongsList(topTracks);
                quiz.setQuiz(playlist);
                // Gets top 200 songs and then adds current songs from playlist to them. hashset ensures uniqueness.
                getTop200SongNames g = new getTop200SongNames();
                List topSongs =  g.top200("./top200SongsWeekly.csv");
                Set<String> topSongsSet = new HashSet<>(topSongs);
                topSongsSet.addAll(playlist.getSuggestions());
                List<String> allSuggestions = new ArrayList<>(topSongsSet);


                quiz.setSuggestions(allSuggestions);
                Song song = quiz.currentPlaying();
                LoginOutputData loginOutputData = new LoginOutputData(user, quiz.getSuggestions(), song.getURL(), false);
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }

    @Override
    public void switchSignUp() {
        loginPresenter.prepareSignUpSwitch();
    }
}
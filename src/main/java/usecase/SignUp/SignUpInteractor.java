package usecase.SignUp;

import dataAccessObjects.getTop200SongNames;
import dataAccessObjects.spotifyAccessObjects.UserAuthenticationBuilder;
import dataAccessObjects.spotifyAccessObjects.UserTopTracks;
import dataAccessObjects.spotifyAccessObjects.UserTopTracksObject;
import entities.Playlist;
import entities.Quiz;
import entities.SpotifyPlaylist;
import entities.Users.User;
import entities.Users.UserFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SignUpInteractor implements SignUpInputBoundary {

    // we want this to pass to check the user details, see if it infringes on anything and if not create the user using
    // input data and saving it to our SignUpFactory database?
    UserFactory userFactory;
    SignupUserDataAccessInterface userDataAccessObject;
    SignUpOutputBoundary userPresenter;
    Quiz quiz;
    UserTopTracks userTopTracks;
    Playlist playlist;

    public SignUpInteractor(UserFactory userFactory, SignupUserDataAccessInterface userDataAccessObject,
                            SignUpOutputBoundary userPresenter, Quiz quiz, UserTopTracks userTopTracks, Playlist playlist) {
        this.userFactory = userFactory;
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
        this.quiz = quiz;
        this.userTopTracks = userTopTracks;
        this.playlist = playlist;
    }
    public void execute(SignUpInputData signUpInputData) throws IOException {
        String username = signUpInputData.getUsername();
        String password = signUpInputData.getPassword();
        String confirmPassword = signUpInputData.getConfirmPassword();

        if (userDataAccessObject.exists(username)) {
            userPresenter.prepareFailView("Username already exists!");
        }
        else if (!password.equals(confirmPassword)) {
            userPresenter.prepareFailView("Password does not match!");
        }
        else {
            User user = userFactory.createUser(username, password);

            UserTopTracks userTopTracksObject = new UserTopTracksObject(); // Here we don't actually care about top tracks
            // but it still does authentication for the user
            playlist.setSongsList(userTopTracksObject.getTopTracks(user));
            quiz.setQuiz(playlist);
            getTop200SongNames g = new getTop200SongNames();
            List topSongs =  g.top200("./top200SongsWeekly.csv");
            Set<String> topSongsSet = new HashSet<>(topSongs);
            topSongsSet.addAll(playlist.getSuggestions());
            List<String> allSuggestions = new ArrayList<>(topSongsSet);
            quiz.setSuggestions(allSuggestions);
            userDataAccessObject.save(user);
            SignUpOutputData signUpOutputData = new SignUpOutputData(user, quiz.getSuggestions(), quiz.currentPlaying().getURL());
            userPresenter.prepareSuccessView(signUpOutputData);
        }

    }

    @Override
    public void switchLoginView() {
        userPresenter.switchLoginView();
    }
}

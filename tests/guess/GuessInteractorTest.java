package guess;

import entities.*;
import interface_adapter.PlayViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.guess.GuessPresenter;
import usecase.guess.*;
import entities.Users.CommonUserFactory;
import entities.Users.User;
import org.junit.Before;
import org.junit.Test;

import javax.swing.text.View;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class GuessInteractorTest {

    private Quiz quiz;
    private GuessInteractor guessInteractor;

    @Before
    public void init() throws IOException {
        // initialize the quiz
        CommonUserFactory commonUserFactory = new CommonUserFactory();
        User user = commonUserFactory.createUser("name", "password");
        Player player = new SinglePlayer(user);

        List<HashMap<String, String>> tracks = new ArrayList<>();
        HashMap<String, String> songs = new HashMap<>();
        songs.put("song1", "url1");
        songs.put("song2", "url2");
        tracks.add(songs);

        Playlist playlist = new SpotifyPlaylist(tracks);
        this.quiz = new PlaylistQuiz(player);
        this.quiz.setQuiz(playlist);

        // initialize the interactor
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        PlayViewModel playViewModel = new PlayViewModel();
        GuessOutputBoundary guessPresenter = new GuessPresenter(viewManagerModel, playViewModel);
        this.guessInteractor = new GuessInteractor(guessPresenter, quiz);
    }

    @Test
    public void testExecuteCorrectGuess() {
        // define test data
        Song curr = quiz.currentPlaying();
        GuessInputData guessInputData = new GuessInputData("song1");

        // execute method
        guessInteractor.execute(guessInputData);

        // check output data
        assertEquals("song1", guessInputData.getGuess());
        assertEquals("song1", curr.getSongName());
        assertEquals("url1", curr.getURL());
        assertEquals(0, quiz.getPoints());
    }


    @Test
    public void testExecuteIncorrectGuess() {
        // define test data
        Song curr = quiz.currentPlaying();
        GuessInputData guessInputData = new GuessInputData("song3");

        // execute method
        guessInteractor.execute(guessInputData);

        // check output data
        assertEquals("song1", curr.getSongName());
        assertEquals("url1", curr.getURL());
        assertEquals(0, quiz.getPoints());
    }


}

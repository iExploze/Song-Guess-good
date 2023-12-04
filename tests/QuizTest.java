import entities.*;
import entities.Users.CommonUserFactory;
import entities.Users.User;
import org.junit.Before;
import org.junit.Test;
import usecase.timer.TimeInteractor;
import usecase.timer.TimeOutputData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class QuizTest {
    private Quiz quiz;
    private Player player;
    private User user;
    private List<HashMap<String, String>> songs;
    private Playlist playlist;
    @Before
    public void init() throws IOException {
        CommonUserFactory commonUserFactory = new CommonUserFactory();
        this.user = commonUserFactory.createUser("a","b");
        this.player = new SinglePlayer(user);
        this.quiz = new PlaylistQuiz(player);
        songs = new ArrayList<>();
        char songName = 'a';
        for (int i = 1; i <= 5; i++) {
            HashMap<String, String> track = new HashMap<>();
            track.put("name", String.valueOf(songName));
            track.put("preview_url", String.valueOf(i));
            songs.add(track);
            songName++;
        }
        playlist = new SpotifyPlaylist();
        playlist.setSongsList(songs);
    }

    @Test
    public void testSetQuiz() throws IOException {
        this.quiz.setQuiz(this.playlist);
        assert this.quiz.currentPlaying() == this.playlist.getSong(0);
    }

    @Test
    public void testSuggestions() throws IOException {
        List<String> str = new ArrayList<>();
        str.add("a");
        str.add("b");
        str.add("c");
        this.quiz.setSuggestions(str);
        assert quiz.getSuggestions() == str;
    }

    @Test
    public void testGoNext() throws IOException {
        this.quiz.setQuiz(this.playlist);
        assert this.quiz.currentPlaying() == this.playlist.getSong(0);
        this.quiz.goNext();
        assert this.quiz.currentPlaying() == this.playlist.getSong(1);
    }
    @Test
    public void testPoints() throws IOException {
        assert this.quiz.getPoints() == 0;
        this.quiz.addPoints();
        assert this.quiz.getPoints() == 1;
    }
    @Test
    public void testTime() throws IOException {
        this.quiz.setTime(120);
        assert this.quiz.getTimeLeft() == 120;
        this.quiz.decreaseTime();
        assert this.quiz.getTimeLeft() == 119;
    }

}

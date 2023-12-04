import entities.*;
import entities.Users.CommonUserFactory;
import interface_adapter.PlayViewModel;
import interface_adapter.timer.TimerPresenter;
import org.junit.Before;
import org.junit.Test;
import usecase.timer.TimeInputData;
import usecase.timer.TimeInteractor;
import usecase.timer.TimeOutputData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class PlaylistTest {
    private Playlist playlist;

    private List<HashMap<String, String>> songs;
    @Before
    public void init() throws IOException {
        songs = new ArrayList<>();
        char songName = 'a';
        for (int i = 1; i <= 5; i++) {
            HashMap<String, String> track = new HashMap<>();
            track.put("name", String.valueOf(songName));
            track.put("preview_url", String.valueOf(i));
            songs.add(track);
            songName++;
        }
        playlist = new SpotifyPlaylist(songs);
    }

    @Test
    public void testGetSong() throws IOException {
        if(Objects.equals(this.playlist.getSong(1).getSongName(), "a"))
            assert Objects.equals(this.playlist.getSong(1).getURL(), "1");
        else if (Objects.equals(this.playlist.getSong(1).getSongName(), "b"))
            assert Objects.equals(this.playlist.getSong(1).getURL(), "2");
        else if (Objects.equals(this.playlist.getSong(1).getSongName(), "c"))
            assert Objects.equals(this.playlist.getSong(1).getURL(), "3");
        else if (Objects.equals(this.playlist.getSong(1).getSongName(), "d"))
            assert Objects.equals(this.playlist.getSong(1).getURL(), "4");
        else
            assert Objects.equals(this.playlist.getSong(1).getURL(), "5");
    }

    @Test
    public void testGetByName() throws IOException {
        assert Objects.equals(this.playlist.getByName("a").getURL(), "1");
        assert Objects.equals(this.playlist.getByName("b").getURL(), "2");
        assert Objects.equals(this.playlist.getByName("c").getURL(), "3");
        assert Objects.equals(this.playlist.getByName("d").getURL(), "4");
        assert Objects.equals(this.playlist.getByName("e").getURL(), "5");
    }

    @Test
    public void testGetSongList() throws IOException {
        assert this.playlist.getSongsList().size() == 5;
    }

    @Test
    public void testGetSuggestions() throws IOException {
        assert this.playlist.getSuggestions().size() == 5;
    }
}

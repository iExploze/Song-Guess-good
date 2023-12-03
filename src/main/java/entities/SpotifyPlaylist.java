package entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class SpotifyPlaylist implements Playlist {

    private final ArrayList<Song> songsList;

    public SpotifyPlaylist(List<HashMap<String, String>> tracks)
    {
        // <Song name, URL>
        this.songsList = new ArrayList<>();
        for (HashMap<String, String> track : tracks) {
            // Assuming each map contains keys "name" and "url"
            String name = track.get("name");
            String url = track.get("preview_url");
            songsList.add(new SongData(name, url)); // Assuming SongData is a concrete implementation of Song
        }
        Collections.shuffle(this.songsList);
    }
    @Override
    public ArrayList<Song> getSongsList() {
        return songsList;
    }

    @Override
    public Song getSong(int index) {
        if (index >= 0 && index < songsList.size()) {
            return songsList.get(index);
        }
        return null;
    }

    @Override
    public Song getByName(String name) {
        for (Song song : songsList) {
            if (song.getSongName().equals(name)) {
                return song;
            }
        }
        return null;
    }
    public List<String> getSuggestions() {
        List<String> res = new ArrayList();
        for (Song song : songsList) {
            res.add(song.getSongName());
        }
        return res;
    }
}

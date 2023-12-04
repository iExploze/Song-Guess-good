package entities;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface Playlist {
    List<Song> getSongsList();

    Song getSong(int index);

    Song getByName(String name);
    void setSongsList(ArrayList<Song> songsList);
    List<String> getSuggestions();
    void setSongsList(List<HashMap<String, String>> tracks);
}

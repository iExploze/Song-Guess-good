package entities;

import java.util.ArrayList;

public interface Playlist {
    ArrayList<Song> songsList();

    Song getSong(int index);

    Song getByName(String name);
}

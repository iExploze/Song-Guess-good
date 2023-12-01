package entities;


import java.util.List;

public interface Playlist {
    List<Song> getSongsList();

    Song getSong(int index);

    Song getByName(String name);
}

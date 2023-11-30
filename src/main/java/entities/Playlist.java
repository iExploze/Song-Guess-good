package entities;

import java.util.ArrayList;

public interface Playlist {
    ArrayList<Song> songs();

    Song getSong(int index);

    Song getByName(String name);

    Song getByAuthor(String author);

    void addSong(Song song);

    void addSong(Song[] songs);
}

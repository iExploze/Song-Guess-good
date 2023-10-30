package main.java.entities;

import java.util.ArrayList;

public interface Playlist {
    ArrayList<Song> songs();

    Song[] getSongs();

    Song getByName(String name);

    Song getByAuthor(String author);

    void addSong(Song song);

    void addSong(Song[] songs);
}

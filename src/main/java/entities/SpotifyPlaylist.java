package entities;

import entities.Playlist;
import entities.Song;

import java.util.ArrayList;

public class SpotifyPlaylist implements Playlist {
    @Override
    public ArrayList<Song> songs() {
        return null;
    }

    @Override
    public Song[] getSongs() {
        return new Song[0];
    }

    @Override
    public Song getByName(String name) {
        return null;
    }

    @Override
    public Song getByAuthor(String author) {
        return null;
    }

    @Override
    public void addSong(Song song) {

    }

    @Override
    public void addSong(Song[] songs) {

    }
}

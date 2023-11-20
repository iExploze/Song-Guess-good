package entities;

import entities.Playlist;
import entities.Song;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public class SpotifyPlaylist implements Playlist {
    public SpotifyPlaylist()
    {

    }
    @Override
    public ArrayList<Song> songs() {
        return null;
    }

    @Override
    public Song getSong(int index) {
        int length = 5;
        Random random = new SecureRandom();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            sb.append(characters.charAt(randomIndex));
        }
        String randomName = sb.toString();

        return new SongData(randomName);
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

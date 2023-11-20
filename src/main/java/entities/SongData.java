package entities;

import entities.Song;

import java.security.SecureRandom;
import java.util.Random;

public class SongData implements Song {
    String name;
    public SongData(String name)
    {
        this.name = name;
    }
    @Override
    public String getArtistName() {
        return null;
    }

    @Override
    public String getSongName() {
        int length = 5;
        Random random = new SecureRandom();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            sb.append(characters.charAt(randomIndex));
        }

        return sb.toString();
    }

    @Override
    public String getSongGenre() {
        return null;
    }

    @Override
    public String getNumStreams() {
        return null;
    }

    @Override
    public void playSong() {

    }
}

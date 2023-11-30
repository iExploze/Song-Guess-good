package entities;

import entities.Song;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.Random;

public class SongData implements Song {
    String name;
    String url;

    public SongData(String name)
    {
        this.name = name;
        this.url = getURL();
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
    public String getURL() {
        return null;
    }

    /**
     * Plays an MP3 audio file from a given URL
     *
     * This method accesses the audio stream from a given URL, converts it to a usable format,
     * and plays it through the default audio format
     *
     * Referenced from https://docs.oracle.com/javase/tutorial/sound/converters.html
     * and https://docs.oracle.com/javase/8/docs/technotes/guides/sound/programmer_guide/chapter4.html
     *
     *
     */
    @Override
    public void playSong() {
    }
}

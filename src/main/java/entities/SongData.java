package entities;

import entities.Song;

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
        return null;
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

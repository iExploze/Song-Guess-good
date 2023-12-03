package entities;

public class SongData implements Song {
    private final String name;
    private final String url;

    public SongData(String name, String url)
    {
        this.name = name;
        this.url = url;
    }
    @Override
    public String getArtistName() {
        return null;
    }

    @Override
    public String getSongName() {
        return this.name;
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
        return this.url;
    }

    /**
     * Plays an MP3 audio file from a given URL
     * <p>
     * This method accesses the audio stream from a given URL, converts it to a usable format,
     * and plays it through the default audio format
     * <p>
     * Referenced from https://docs.oracle.com/javase/tutorial/sound/converters.html
     * and https://docs.oracle.com/javase/8/docs/technotes/guides/sound/programmer_guide/chapter4.html
     */
    @Override
    public String playSong() {
        return this.url;
    }

    @Override
    public void stopSong() {

    }
}

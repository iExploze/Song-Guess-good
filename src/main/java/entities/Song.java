package entities;

public interface Song {

    String getSongName();

    String getURL();

    boolean existSong();

    void playSong();
    // gets the mp3 file and plays the song
}
